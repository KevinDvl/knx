package com.knx;

import tuwien.auto.calimero.GroupAddress;
import tuwien.auto.calimero.KNXFormatException;
import tuwien.auto.calimero.KNXTimeoutException;
import tuwien.auto.calimero.link.KNXLinkClosedException;
import tuwien.auto.calimero.process.ProcessCommunicator;

/**
 * Hello world!
 *
 */


public class Chenillard0 extends Thread implements Chenillard{
	
	private ProcessCommunicator pc;
	private int tempsFin;
	private Boolean start;
	private Boolean sens;
	
	public Chenillard0(ProcessCommunicator pc, int tempsFin, Boolean start, Boolean sens){
		this.pc = pc;
		this.tempsFin = tempsFin;
		this.start = start;
		this.sens = sens;
		
	}
	
	public void setTempsFin(int newT) {
		this.tempsFin= newT;
	}
	
	public void setSens(Boolean sens) {
		this.sens= sens;
	}
	
	public void setStart(Boolean start) {
		this.start= start;
	}
	
	@Override
	public void run() {
		start = true;
    	int i = 0;
    	System.out.println("chenillard0 started.. " + this.tempsFin);
    	try {
			pc.write(new GroupAddress("0/0/1"), false);
			pc.write(new GroupAddress("0/0/2"), false);
			pc.write(new GroupAddress("0/0/3"), false);
			pc.write(new GroupAddress("0/0/4"), false);
		} catch (KNXTimeoutException | KNXLinkClosedException | KNXFormatException e10) {
			// TODO Auto-generated catch block
			e10.printStackTrace();
		}
    	
    	while(start) {
    		try {
				pc.write(new GroupAddress("0/0/4"), false);
				pc.write(new GroupAddress("0/0/1"), true);
			
				Thread.sleep (tempsFin);

				pc.write(new GroupAddress("0/0/1"), false);
				pc.write(new GroupAddress("0/0/2"), true);

				Thread.sleep(tempsFin);

				pc.write(new GroupAddress("0/0/2"), false);
				pc.write(new GroupAddress("0/0/3"), true);

				Thread.sleep(tempsFin);

				pc.write(new GroupAddress("0/0/3"), false);
				pc.write(new GroupAddress("0/0/4"), true);
				Thread.sleep(tempsFin);
			} catch (KNXTimeoutException | KNXLinkClosedException | KNXFormatException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
    		}
    	System.out.println("Vraiment stop!");
		
	}
	
}
    