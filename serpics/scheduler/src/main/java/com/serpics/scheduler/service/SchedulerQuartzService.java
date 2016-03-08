package com.serpics.scheduler.service;

import org.quartz.SchedulerException;

import com.serpics.scheduler.exception.JobSchedulerException;
import com.serpics.scheduler.job.AbstractJob;
import com.serpics.scheduler.model.CronJob;
import com.serpics.scheduler.model.JobDetails;
import com.serpics.scheduler.model.TriggerJob;

public interface SchedulerQuartzService {

	void pauseJob(JobDetails jobToPaused) throws SchedulerException;
	
	void resumeJob(JobDetails jobToResume) throws SchedulerException;

	void saveJob(Class<? extends AbstractJob> jobToCreate, JobDetails jobDetails, boolean replace)
			throws JobSchedulerException;

	void saveSimpleTrigger(TriggerJob triggerJob, JobDetails jobToExecute, boolean replace)
			throws JobSchedulerException;

	void saveCronTrigger(CronJob cronJob, JobDetails jobToExecute, boolean replace) throws JobSchedulerException;
	
}
