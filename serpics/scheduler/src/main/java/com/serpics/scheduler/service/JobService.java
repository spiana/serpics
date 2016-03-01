package com.serpics.scheduler.service;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.serpics.base.data.model.Store;
import com.serpics.catalog.data.model.Catalog;
import com.serpics.scheduler.exception.JobSchedulerException;
import com.serpics.scheduler.job.AbstractJob;
import com.serpics.scheduler.model.AbstractSchedulerJob;
import com.serpics.scheduler.model.CronJob;
import com.serpics.scheduler.model.JobDetails;
import com.serpics.scheduler.model.TriggerJob;

public interface JobService {

	JobDetails createJobDetail(Class<? extends AbstractJob> jobToCreate, Store store, Catalog catalog)
			throws JobSchedulerException;

	AbstractSchedulerJob createTrigger(TriggerJob trigger, JobDetails jobToExecute) throws JobSchedulerException;

	AbstractSchedulerJob createCronJob(CronJob cronJob, JobDetails jobToExecute) throws JobSchedulerException;

	JobDetails createJobDetail(Class<? extends AbstractJob> jobToCreate, JobDetails jobDetails)
			throws JobSchedulerException;

	void pauseJob(JobDetails jobToPaused);

	void resumeJob(JobDetails jobToResume);

	void manageJobToStart(JobExecutionContext context);

	void manageJobFinished(JobExecutionContext context, JobExecutionException jobException);

}
