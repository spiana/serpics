package com.serpics.scheduler.listener;

import java.util.Date;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;

import com.serpics.scheduler.model.JobLog;
import com.serpics.scheduler.model.JobState;
import com.serpics.scheduler.service.JobLogService;

public class SchedulerJobListener extends JobListenerSupport {

	@Resource
	private JobLogService jobLogService;
	
	@Override
	public String getName() {
		return "SerpicsJobListener";
	}
	
	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		super.jobWasExecuted(context, jobException);
		
		if(jobException!=null){
			JobLog jLog = new JobLog();
			jLog.setDateLog(new Date());
			jLog.setMessage(jobException.getMessage());
			jLog.setState(JobState.ERROR.name());
			jobLogService.addJobLog(context.getTrigger().getKey().getName(), jLog);
		}
	}
	

}
