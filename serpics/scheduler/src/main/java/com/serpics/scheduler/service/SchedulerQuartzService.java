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
