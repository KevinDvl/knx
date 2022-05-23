package com.knx;

import java.time.Duration;
import java.util.concurrent.ExecutionException;

import tuwien.auto.calimero.knxnetip.Discoverer;

/**
 * This example shows how to discover active KNXnet/IP servers in an IP network. Minimum requirements are Calimero
 * version 2.6-SNAPSHOT and Java SE 11 (java.base).
 * <p>
 * You can safely run this example, no KNX messages are sent to the KNX network.
 */
public class DiscoverKnxServers {
	public static void main(final String[] args) {
		System.out.println("This example discovers all active KNXnet/IP servers in your IP network");

		try {
			// set true to be aware of Network Address Translation (NAT) during discovery
			final boolean useNAT = false;
			Discoverer.udp(useNAT).timeout(Duration.ofSeconds(3)).search().get().forEach(r ->
					System.out.format("%s %s <=> %s%n",
							r.getNetworkInterface().getName(),
							r.localEndpoint(),
							r.getResponse().toString().replace(", ", "\n\t")));
		}
		catch (InterruptedException | ExecutionException e) {
			System.err.println("Error during KNXnet/IP discovery: " + e);
		}
	}
}
