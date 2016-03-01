package com.serpics.scheduler.exception;

import org.quartz.SchedulerException;

public class JobSchedulerException extends Exception {

	public JobSchedulerException(String msg){
		super(msg);
	}
	
	public JobSchedulerException(String string, SchedulerException e) {
		super(string, e);
	}

	private static final long serialVersionUID = -8502090214145725567L;

}
