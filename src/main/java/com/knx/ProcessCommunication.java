package com.knx;

import java.net.InetSocketAddress;

import tuwien.auto.calimero.GroupAddress;
import tuwien.auto.calimero.KNXException;
import tuwien.auto.calimero.link.KNXNetworkLink;
import tuwien.auto.calimero.link.KNXNetworkLinkIP;
import tuwien.auto.calimero.link.medium.TPSettings;
import tuwien.auto.calimero.process.ProcessCommunicator;
import tuwien.auto.calimero.process.ProcessCommunicatorImpl;

/**
 * Example of Calimero process communication, we read (and write) a boolean datapoint in the KNX network. By default,
 * this example will not change any datapoint value in the network.
 */
public class ProcessCommunication
{
	// Address of your KNXnet/IP server. Replace the IP host or address as necessary.
	private static final String remoteHost = "192.168.0.201";

	// We will read a boolean from the KNX datapoint with this group address, replace the address as necessary.
	// Make sure this datapoint exists, otherwise you will get a read timeout!
	private static final String group = "0/0/1";

	public static void main(final String[] args)
	{
		final InetSocketAddress remote = new InetSocketAddress(remoteHost, 3671);
		// Create our network link, and pass it to a process communicator
		try (KNXNetworkLink knxLink = KNXNetworkLinkIP.newTunnelingLink(null, remote, false, new TPSettings());
				ProcessCommunicator pc = new ProcessCommunicatorImpl(knxLink)) {

			System.out.println("read boolean value from datapoint " + group);
			final boolean value = pc.readBool(new GroupAddress(group));
			System.out.println("datapoint " + group + " value = " + value);

			// Uncomment the next line, if you want to write back the same value to the KNX network
			pc.write(new GroupAddress(group), !value);
			
			//startChenillard(pc, 2);
		}
		catch (KNXException | InterruptedException e) {
			System.out.println("Error accessing KNX datapoint: " + e.getMessage());
		}
	}

	private static void startChenillard(ProcessCommunicator pc, int i) {
		
	}
}
