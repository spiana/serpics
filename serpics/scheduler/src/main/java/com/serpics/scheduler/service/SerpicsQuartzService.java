package com.serpics.scheduler.service;

import org.quartz.SchedulerException;

import com.serpics.scheduler.exception.SerpicsSchedulerException;
import com.serpics.scheduler.job.AbstractSerpicsJob;
import com.serpics.scheduler.model.CronJob;
import com.serpics.scheduler.model.SerpicsJobDetails;
import com.serpics.scheduler.model.TriggerJob;

public interface SerpicsQuartzService {

	void addJob(Class<? extends AbstractSerpicsJob> jobToCreate, SerpicsJobDetails jobDetails)
			throws SerpicsSchedulerException;

	void addSimpleTrigger(TriggerJob triggerJob, SerpicsJobDetails jobToExecute) throws SerpicsSchedulerException;

	void addCronTrigger(CronJob cronJob, SerpicsJobDetails jobToExecute) throws SerpicsSchedulerException;

	void pauseJob(SerpicsJobDetails jobToPaused) throws SchedulerException;
	
	void resumeJob(SerpicsJobDetails jobToResume) throws SchedulerException;
	
}
