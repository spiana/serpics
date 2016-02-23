package com.serpics.scheduler.exception;

import org.quartz.SchedulerException;

public class SerpicsSchedulerException extends Exception {

	public SerpicsSchedulerException(String msg){
		super(msg);
	}
	
	public SerpicsSchedulerException(String string, SchedulerException e) {
		super(string, e);
	}

	private static final long serialVersionUID = -8502090214145725567L;

}
