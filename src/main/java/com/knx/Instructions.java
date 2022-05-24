package com.knx;

import javax.servlet.http.HttpServletRequest;

import tuwien.auto.calimero.GroupAddress;
import tuwien.auto.calimero.KNXFormatException;
import tuwien.auto.calimero.KNXTimeoutException;
import tuwien.auto.calimero.link.KNXLinkClosedException;
import tuwien.auto.calimero.process.ProcessCommunicator;

public class Instructions 
{
    private boolean led1;
    private boolean led2;
    private boolean led3;
    private boolean led4;
    private Chenillard1 chenillard1;
    private Chenillard2 chenillard2;
    private ProcessCommunicator pc;
    
    public Instructions() {
    	//this.pc = GroupMonitor.pc;
    	led1 = false;
    	led2 = false;
    	led3 = false;
    	led4 = false;
    	//this.chenillard1 = new Chenillard1(pc,GroupMonitor.tf,true,true);
    	//this.chenillard2 = new Chenillard2(pc,GroupMonitor.tf,true,true);
    }
    
    public void lancerInstruction(HttpServletRequest request) {
    	String instruction = request.getParameter("instruction");
    	System.out.println("Instruction lancée : "+instruction);
    	
    	if(instruction.equals("startChenillard1")) {
    		/*chenillard1 = new Chenillard1(pc,GroupMonitor.tf,true,true);
			chenillard1.start();*/
    	}
    	
    	else if(instruction.equals("startChenillard2")) {
    		/*chenillard2 = new Chenillard2(pc,GroupMonitor.tf,true,true);
			chenillard2.start();*/
    		System.out.println("lance le chenillard 2");    	}
    	
    	else if(instruction.equals("stopChenillard")) {
    		if(chenillard1.getStart()) {
    			//chenillard1.setStart(false);
    		}
    		else if(chenillard2.getStart()) {
    			//chenillard2.setStart(false);
    		}
    	}
    	
    	else if(instruction.equals("allumeL1") | instruction.equals("eteinsL1")) {
    		/*try {
    			led1 = !led1;
    			pc.write(new GroupAddress("0/0/1"), led1);
    		} catch (KNXTimeoutException | KNXLinkClosedException | KNXFormatException e10) {
    			e10.printStackTrace();
    		}*/
    	}
    	
		else if(instruction.equals("allumeL2") | instruction.equals("eteinsL2")) {
			/*try {
    			led2 = !led2;
    			pc.write(new GroupAddress("0/0/2"), led2);
    		} catch (KNXTimeoutException | KNXLinkClosedException | KNXFormatException e10) {
    			e10.printStackTrace();
    		}*/
		}
    	
		else if(instruction.equals("allumeL3") | instruction.equals("eteinsL3")) {
			/*try {
    			led3 = !led3;
    			pc.write(new GroupAddress("0/0/3"), led3);
    		} catch (KNXTimeoutException | KNXLinkClosedException | KNXFormatException e10) {
    			e10.printStackTrace();
    		}*/
		}
    	
		else if(instruction.equals("allumeL4") | instruction.equals("eteinsL4")) {
			/*try {
    			led4 = !led4;
    			pc.write(new GroupAddress("0/0/4"), led4);
    		} catch (KNXTimeoutException | KNXLinkClosedException | KNXFormatException e10) {
    			e10.printStackTrace();
    		}*/
		}

    }
}
