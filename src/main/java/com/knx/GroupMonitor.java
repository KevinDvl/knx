package com.knx;

import java.net.InetSocketAddress;
import java.time.LocalTime;

//import javax.websocket.Session;

import tuwien.auto.calimero.DataUnitBuilder;
import tuwien.auto.calimero.DetachEvent;
import tuwien.auto.calimero.KNXException;
import tuwien.auto.calimero.link.KNXNetworkLink;
import tuwien.auto.calimero.link.KNXNetworkLinkIP;
import tuwien.auto.calimero.link.medium.TPSettings;
import tuwien.auto.calimero.process.ProcessCommunicator;
import tuwien.auto.calimero.process.ProcessCommunicatorImpl;
import tuwien.auto.calimero.process.ProcessEvent;
import tuwien.auto.calimero.process.ProcessListener;

/**
 * Example code showing how to use KNX process communication for group monitoring on a KNX Twisted Pair 1 (TP1) network.
 * On receiving group notifications, the KNX source and destination address are printed to System.out, as well as any
 * data part of the application service data unit (ASDU) in hexadecimal format.
 * <p>
 * Note that this example does not exit, i.e., it monitors forever or until the KNX network link connection got
 * closed. Hence, with KNX servers that have a limit on active tunneling connections (usually 1 or 4), if the group
 * monitor in connected state is terminated by the client (you), the pending state of the open tunnel on the KNX server
 * might temporarily cause an error on subsequent connection attempts.
 *
 * @author B. Malinowsky
 */
public class GroupMonitor implements ProcessListener {
	/**
	 * Address of your KNXnet/IP server. Replace the host or IP address as necessary.
	 */
	private static final String remoteHost = "192.168.0.202";
	public static int tempsFin = 500;
	public static int tf = 500;
	public static KNXNetworkLink knxLink;
	public static ProcessCommunicator pc;
	public static int i;
	public static boolean first = true;
	public static Chenillard0 chenillard0;
	public static Chenillard1 chenillard1;
	public static Chenillard2 chenillard2;
	public static boolean start = true;
	public static boolean launched = false;
	//public static WebSocket ws;
	//public static Session session;
	public static Chenillard currentChenillard;
	public static Integer[] tempsFins=  {100,300,500,700,900};
	public static Boolean ralentir = true;
	public static Boolean firstStart;
	
	
	
	public static void main(final String[] args) {
		new GroupMonitor().run();
		tf = 500;
    	GroupMonitor.tempsFin = 500;
    	i=2;
    	}

	public void run() {
		final InetSocketAddress remote = new InetSocketAddress(remoteHost, 3671);
		try {
			knxLink = KNXNetworkLinkIP.newTunnelingLink(null, remote, false, new TPSettings());
		    pc = new ProcessCommunicatorImpl(knxLink);
			// start listening to group notifications using a process listener
			pc.addProcessListener(this);
			System.out.println("Monitoring KNX network using KNXnet/IP server " + remoteHost + " ...");
			while (knxLink.isOpen()) Thread.sleep(1000);
		}
		catch (final KNXException | InterruptedException | RuntimeException e) {
			System.err.println(e);
		}
		if (pc != null)	pc.close();
	}

	@Override
	public void groupWrite(final ProcessEvent e) { 
		print("write.ind", e);
		
		if(e.getDestination().toString().equals("1/0/1")&& DataUnitBuilder.toHex(e.getASDU(), "").equals("01")) {
			if(!launched) {
				System.out.println("Demarrage du chenillard 1 !");
				first=true;
				chenillard1 = new Chenillard1(pc,GroupMonitor.tf,true,true);
				chenillard1.start();
				launched=true;
			}
			
		}
		
		if(e.getDestination().toString().equals("1/0/2")&& DataUnitBuilder.toHex(e.getASDU(), "").equals("01")) {
			if(!launched) {
				System.out.println("Demarrage du chenillard 2 !");
				first=false;
				chenillard2 = new Chenillard2(pc,GroupMonitor.tf,true,true);
				chenillard2.start();
				launched=true;
			}
		}
		
		if(e.getDestination().toString().equals("1/0/3")&& DataUnitBuilder.toHex(e.getASDU(), "").equals("01")) {
				if(ralentir) {
					System.out.println("ralentissement mode");
					if(tempsFins[i]==900) {
						ralentir=false;
						i--;
						System.out.println("acceleration: "+tempsFins[i]);
					}
					else if(i<tempsFins.length) {
						i++;
						System.out.println("ralentissement : "+tempsFins[i]);
					}
					
				}
				else {
					System.out.println("alcceleration mode");
					if(tempsFins[i]==100) {
						ralentir=true;
						i++;
						System.out.println("ralentissement : "+tempsFins[i]);
					}
					else if(i>0) {
						i--;
						System.out.println("acceleration: "+tempsFins[i]);
					}
					
				}
				tf = tempsFins[i];
				
				if(first) {chenillard1.setTempsFin(tf);}
				else {chenillard2.setTempsFin(tf);}
			}
			
		if(e.getDestination().toString().equals("1/0/4")&& DataUnitBuilder.toHex(e.getASDU(), "").equals("01")) {
			
			
			if(first) {
				if(chenillard1.getStart()) {
					chenillard1.setStart(false);
					System.out.println("ARRET du chenillard 1");
					launched=false;
				}
				else {
					chenillard1.setFirstStart(false);
					chenillard1 = new Chenillard1(pc,GroupMonitor.tf,true,false);
					chenillard1.start();
					System.out.println("REPRISE du chenillard 1");
					launched=true;
				}
				
			}
			else {
				if(chenillard2.getStart()) {
					chenillard2.setStart(false);
					System.out.println("ARRET du chenillard 2");
					launched=false;
				}
				else {
					chenillard2 = new Chenillard2(pc,GroupMonitor.tf,true,false);
					chenillard2.start();
					System.out.println("REPRISE du chenillard 2");
					launched=true;
				}
			}
			
		}
		}
		
	
	@Override
	public void groupReadRequest(final ProcessEvent e) { print("read.req", e); }
	@Override
	public void groupReadResponse(final ProcessEvent e) { print("read.res", e); }
	@Override
	public void detached(final DetachEvent e) {}

	// Called on every group notification issued by a datapoint on the KNX network. It prints the service primitive,
	// KNX source and destination address, and Application Service Data Unit (ASDU) to System.out.
	private static void print(final String svc, final ProcessEvent e) {
		try {
			System.out.println(LocalTime.now() + " " + e.getSourceAddr() + "->" + e.getDestination() + " " + svc
					+ ": " + DataUnitBuilder.toHex(e.getASDU(), ""));
		}
		catch (final RuntimeException ex) {
			System.err.println(ex);
		}
	}
	
	
}
