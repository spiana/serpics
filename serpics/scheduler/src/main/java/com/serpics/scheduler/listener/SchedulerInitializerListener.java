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
package com.serpics.scheduler.listener;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.serpics.scheduler.exception.JobSchedulerException;
import com.serpics.scheduler.job.AbstractJob;
import com.serpics.scheduler.model.CronJob;
import com.serpics.scheduler.model.JobDetails;
import com.serpics.scheduler.model.TriggerJob;
import com.serpics.scheduler.repositories.CronJobRepository;
import com.serpics.scheduler.repositories.StoreJobDetailsRepository;
import com.serpics.scheduler.repositories.SystemJobDetailsRepository;
import com.serpics.scheduler.repositories.TriggerJobRepository;
import com.serpics.scheduler.service.SchedulerQuartzService;
import com.vaadin.external.org.slf4j.Logger;
import com.vaadin.external.org.slf4j.LoggerFactory;

/**
 * @author spiana
 *
 */
public class SchedulerInitializerListener implements ApplicationListener<ApplicationEvent>{
	private boolean initialized = false;
	private static Logger LOG = LoggerFactory.getLogger(SchedulerInitializerListener.class);
	
	@Autowired
	SystemJobDetailsRepository systemJobDetailsRepository;
	
	@Autowired
	StoreJobDetailsRepository storeJobDetailsRepository;
	
	@Autowired
	CronJobRepository cronJobRepository;

	@Autowired
	TriggerJobRepository triggerJobRepository;
	
	@Autowired
	SchedulerQuartzService schedulerQuartzService;
	
	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof ContextRefreshedEvent){
			if (!initialized){
				initializeCronJob();
				initializeTriggerJob();
				initialized = true;
			}
		}
	}

	@SuppressWarnings("unchecked")
	protected void initializeTriggerJob(){
		List<TriggerJob> cronJobs = triggerJobRepository.findAll();
		for (TriggerJob triggerJob : cronJobs) {
			JobDetails job = triggerJob.getJobDetail();
				
			Class<? extends AbstractJob> jobToCreate;
			try {
				jobToCreate = (Class<? extends AbstractJob>) Class.forName(job.getNameClassJob());
				schedulerQuartzService.saveJob(jobToCreate, job, true);
				LOG.info("schedule JOB [{}] " , job.getNameClassJob());
				Integer toIterate = triggerJob.getNumberOfIteration() - triggerJob.getItereted() ;
				if (toIterate > 0 ){
					triggerJob.setNumberOfIteration(toIterate);
					triggerJob.setItereted(0);
					triggerJobRepository.saveAndFlush(triggerJob);
					
					LOG.info("triggers JOB [{}] with {} iterations !" ,job.getNameClassJob() , toIterate );
				}
				schedulerQuartzService.saveSimpleTrigger(triggerJob, job, true);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JobSchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	@SuppressWarnings("unchecked")
	protected void initializeCronJob(){
		List<CronJob> cronJobs = cronJobRepository.findAll();
		for (CronJob cronJob : cronJobs) {
			JobDetails job = cronJob.getJobDetail();
			Class<? extends AbstractJob> jobToCreate;
			try {
				jobToCreate = (Class<? extends AbstractJob>) Class.forName(job.getNameClassJob());
				LOG.info("schedule JOB [{}] " , job.getNameClassJob());
				schedulerQuartzService.saveJob(jobToCreate, job, true);
				schedulerQuartzService.saveCronTrigger(cronJob, job, true);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JobSchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
