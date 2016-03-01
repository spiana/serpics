package com.serpics.scheduler.listener;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;

import com.serpics.scheduler.service.JobService;

public class SchedulerJobListener extends JobListenerSupport {

	
	
	@Resource
	private JobService jobService;
	
	
	@Override
	public String getName() {
		return "SerpicsJobListener";
	}
	
	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		super.jobToBeExecuted(context);
		
		jobService.manageJobToStart(context);
		
	}
	
	
	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		super.jobWasExecuted(context, jobException);
		
		jobService.manageJobFinished(context, jobException);	
	}
	

}
