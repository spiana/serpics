package com.serpics.scheduler.service;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.serpics.base.data.model.Store;
import com.serpics.catalog.data.model.Catalog;
import com.serpics.scheduler.exception.SerpicsSchedulerException;
import com.serpics.scheduler.job.AbstractSerpicsJob;
import com.serpics.scheduler.model.AbstractSchedulerSerpicsJob;
import com.serpics.scheduler.model.CronJob;
import com.serpics.scheduler.model.SerpicsJobDetails;
import com.serpics.scheduler.model.TriggerJob;

public interface JobService {

	SerpicsJobDetails createJobDetail(Class<? extends AbstractSerpicsJob> jobToCreate, Store store, Catalog catalog)
			throws SerpicsSchedulerException;

	AbstractSchedulerSerpicsJob createTrigger(TriggerJob trigger, SerpicsJobDetails jobToExecute) throws SerpicsSchedulerException;

	AbstractSchedulerSerpicsJob createCronJob(CronJob cronJob, SerpicsJobDetails jobToExecute) throws SerpicsSchedulerException;

	SerpicsJobDetails createJobDetail(Class<? extends AbstractSerpicsJob> jobToCreate, SerpicsJobDetails jobDetails)
			throws SerpicsSchedulerException;

	void pauseJob(SerpicsJobDetails jobToPaused);

	void resumeJob(SerpicsJobDetails jobToResume);

	void manageJobToStart(JobExecutionContext context);

	void manageJobFinished(JobExecutionContext context, JobExecutionException jobException);

}
