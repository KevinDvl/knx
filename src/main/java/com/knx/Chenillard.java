package com.knx;

import java.net.InetSocketAddress;
import java.util.List;

import tuwien.auto.calimero.GroupAddress;
import tuwien.auto.calimero.KNXException;
import tuwien.auto.calimero.link.KNXNetworkLink;
import tuwien.auto.calimero.link.KNXNetworkLinkIP;
import tuwien.auto.calimero.link.medium.TPSettings;
import tuwien.auto.calimero.process.ProcessCommunicator;
import tuwien.auto.calimero.process.ProcessCommunicatorImpl;

/**
 * Hello world!
 *
 */
public interface Chenillard { 

	static void setTempsFin(int tempsFin) {}
	
	static void setStart(Boolean start) {}
	
}
