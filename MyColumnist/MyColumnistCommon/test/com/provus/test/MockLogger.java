package com.provus.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class MockLogger extends Logger {

    private List<Object> errorList = new ArrayList<Object>(); 
	
    private List<Object> debugList = new ArrayList<Object>(); 
    
	public List<Object> getErrorList() {
		return errorList;
	}
	
	public List<Object> getDebugList() {
		return debugList;
	}

	public MockLogger(String name) {
		super(name);
	}

	@Override
	public void error(Object message) {
	     errorList.add(message);
	}
	
	@Override
	public void debug(Object message) {
		debugList.add(message);
	}
}
