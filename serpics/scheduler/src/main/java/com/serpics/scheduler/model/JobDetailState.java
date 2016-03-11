package com.serpics.scheduler.model;

/**
 * Indicate state of Job Details
 * 
 * ERROR --> JOB in error
 * SUCCESFULL --> last execution of job is succesfull
 * PAUSED --> JOB is stopping
 * RESUMING --> JOB to restart
 * 
 * @author alessandro.marasco@tinvention.net
 *
 */

public enum JobDetailState {
	CREATED,MODIFIED,ERROR,SUCCESFULL,PAUSED,RESUMING, RUNNING;
	
}
