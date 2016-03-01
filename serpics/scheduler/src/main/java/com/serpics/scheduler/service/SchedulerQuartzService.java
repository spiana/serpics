package com.serpics.scheduler.service;

import org.quartz.SchedulerException;

import com.serpics.scheduler.exception.JobSchedulerException;
import com.serpics.scheduler.job.AbstractJob;
import com.serpics.scheduler.model.CronJob;
import com.serpics.scheduler.model.JobDetails;
import com.serpics.scheduler.model.TriggerJob;

public interface SchedulerQuartzService {

	void addJob(Class<? extends AbstractJob> jobToCreate, JobDetails jobDetails)
			throws JobSchedulerException;

	void addSimpleTrigger(TriggerJob triggerJob, JobDetails jobToExecute) throws JobSchedulerException;

	void addCronTrigger(CronJob cronJob, JobDetails jobToExecute) throws JobSchedulerException;

	void pauseJob(JobDetails jobToPaused) throws SchedulerException;
	
	void resumeJob(JobDetails jobToResume) throws SchedulerException;
	
}
