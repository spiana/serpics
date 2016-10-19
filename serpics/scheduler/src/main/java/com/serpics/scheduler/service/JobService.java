/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
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

	JobDetails modifyJobDetail(Class<? extends AbstractJob> jobToCreate, JobDetails jobDetails)
			throws JobSchedulerException;

	AbstractSchedulerJob modifyTrigger(TriggerJob trigger, JobDetails jobToExecute) throws JobSchedulerException;

	AbstractSchedulerJob modifyCronJob(CronJob cronJob, JobDetails jobToExecute) throws JobSchedulerException;

}
