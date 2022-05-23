package com.knx;

import java.net.InetSocketAddress;
import java.util.List;

import tuwien.auto.calimero.GroupAddress;
import tuwien.auto.calimero.KNXException;
import tuwien.auto.calimero.KNXFormatException;
import tuwien.auto.calimero.KNXTimeoutException;
import tuwien.auto.calimero.link.KNXLinkClosedException;
import tuwien.auto.calimero.link.KNXNetworkLink;
import tuwien.auto.calimero.link.KNXNetworkLinkIP;
import tuwien.auto.calimero.link.medium.TPSettings;
import tuwien.auto.calimero.process.ProcessCommunicator;
import tuwien.auto.calimero.process.ProcessCommunicatorImpl;

/**
 * Hello world!
 *
 */

public class Chenillard1 extends Thread implements Chenillard{
	
	private ProcessCommunicator pc;
	private int tempsFin;
	private Boolean start;
	private Boolean firstStart;
	
	public Chenillard1(ProcessCommunicator pc, int tempsFin, Boolean start, Boolean firstStart){
		this.pc = pc;
		this.tempsFin = tempsFin;
		this.start = start;
		this.firstStart = firstStart;
		
	}
	
	public void setTempsFin(int newT) {
		this.tempsFin= newT;
	}
	
	
	public void setStart(Boolean start) {
		this.start= start;
	}
	
	public void setFirstStart(Boolean firstStart) {
		this.firstStart= firstStart;
	}
	
	public Boolean getStart() {
		return this.start;
	}
	
	public void run() {
		start = true;
    	try {
    		if(firstStart) {
    			pc.write(new GroupAddress("0/0/4"), true);
    		}
			
		} catch (KNXTimeoutException | KNXLinkClosedException | KNXFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	System.out.println("chenillard1 started.. " + pc);
    	
    	while(start) {
    	
			try {
				if(start && pc.readBool(new GroupAddress("0/0/4"))) {
					
					pc.write(new GroupAddress("0/0/3"), false);
					pc.write(new GroupAddress("0/0/1"), true);
					pc.write(new GroupAddress("0/0/4"), false);
					
					Thread.sleep(tempsFin);
				}
				
				if(start && pc.readBool(new GroupAddress("0/0/1"))) {
					
					pc.write(new GroupAddress("0/0/2"), true);
					
					Thread.sleep(tempsFin);
				}
				
				if(start&&pc.readBool(new GroupAddress("0/0/2"))) {
					
					pc.write(new GroupAddress("0/0/1"), false);
					pc.write(new GroupAddress("0/0/3"), true);
					
					Thread.sleep(tempsFin);
				}

				
				if(start&&pc.readBool(new GroupAddress("0/0/3"))) {
					
					pc.write(new GroupAddress("0/0/1"), false);
					pc.write(new GroupAddress("0/0/2"), false);
					pc.write(new GroupAddress("0/0/4"), true);
					
					Thread.sleep(tempsFin);
				}				
				
			} catch (KNXException| InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
    		}	
	}
	
}
    