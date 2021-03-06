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
package com.serpics.scheduler.service.impl;

import java.util.Calendar;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.scheduler.exception.JobSchedulerException;
import com.serpics.scheduler.job.AbstractJob;
import com.serpics.scheduler.model.CronJob;
import com.serpics.scheduler.model.JobDetails;
import com.serpics.scheduler.model.TriggerJob;
import com.serpics.scheduler.service.SchedulerQuartzService;

@Service("schedulerQuartzService")
public class SchedulerQuartzServiceImpl implements SchedulerQuartzService {

	Logger LOG = LoggerFactory.getLogger(SchedulerQuartzServiceImpl.class);

	private final String KEY_GROUP_TRIGGER = "DEFAULT_CUSTOM_GROUP_TRIGGER";
	private final String KEY_GROUP_CRONJOB = "DEFAULT_CUSTOM_GROUP_CRONJOB";
	private final String KEY_GROUP_JOB = "DEFAULT_CUSTOM_GROUP_JOB";

	@Autowired
	private Scheduler scheduler;

	@Override
	@Transactional
	public void saveJob(Class<? extends AbstractJob> jobToCreate, JobDetails jobDetails,boolean replace) throws JobSchedulerException {
		
		Assert.notNull(jobDetails, "Job Details cannot be null");
		LOG.debug("Try to save job in quartz with identity key [ name: {} , group: {} ]",jobDetails.getUuid(), KEY_GROUP_JOB);
		
		JobDetail jobInQuartz;
		try {
				JobKey jobKey = new JobKey(jobDetails.getUuid(), KEY_GROUP_JOB);
				jobInQuartz = findJobInQuartz(jobKey);
				
				if (!replace && jobInQuartz != null) {
					throw new JobSchedulerException("Job with same key just exist in quartz");
				}
				
				jobInQuartz = JobBuilder.newJob(jobToCreate).withIdentity(jobKey)
						.usingJobData(createJobDataMap(jobDetails))
						.storeDurably(true)
						.build();
				
				scheduler.addJob(jobInQuartz, true);
				LOG.info("Add job in scheduler with identity key [ {} ]", jobKey);
			} catch (SchedulerException e) {
				LOG.error("Error to add job in scheduler", e);
				throw new JobSchedulerException("Error to add Job " + jobDetails.getUuid(), e);
			}
	}
	
	private JobDataMap createJobDataMap(JobDetails jobdetails){
		
		JobDataMap dataMap = new JobDataMap();
		dataMap.put("realmStore", jobdetails.getStore()!=null?jobdetails.getStore().getName():"");
		dataMap.put("catalog", jobdetails.getCatalog()!=null?jobdetails.getCatalog().getCode():"");
		
		String parameters = jobdetails.getJobParameters();
		if (parameters !=  null){
			mergeJobarameters(dataMap, parameters.split(","));
		}
		
		return dataMap;
	}
	
	private void mergeJobarameters(JobDataMap dataMap , String[] parameters){
		for (String param : parameters) {
			String[] _p = param.split("=");
			if (_p.length == 2){
				dataMap.put(_p[0], _p[1]);
				LOG.debug("add job parameter [{}] witb value[{}]" , _p[0], _p[1]);
			}
		}
	}
	
	private JobDetail findJobInQuartz(JobKey jobkey) throws SchedulerException {
		JobDetail jobToReturn = null;
		jobToReturn = scheduler.getJobDetail(jobkey);
		return jobToReturn;
	}
	
	@Override
	@Transactional
	public void saveSimpleTrigger(TriggerJob triggerJob,JobDetails jobToExecute,boolean replace) throws JobSchedulerException{
		try {
			JobKey jobKey = new JobKey(jobToExecute.getUuid(),KEY_GROUP_JOB);
			TriggerKey triggerKey = new TriggerKey(triggerJob.getUuid(),KEY_GROUP_TRIGGER);
			
			JobDetail jobDetailsQuartz = findJobInQuartz(jobKey);
			
			if(jobDetailsQuartz==null){
				throw new JobSchedulerException("Impossible to create a Trigger if job does not create. Create it first!");
			}
			
			TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
			LOG.debug("Set trigger identity new Key({})",triggerKey);
			triggerBuilder.withIdentity(triggerKey);
			
			LOG.debug("Set trigger job new JobKey({}) == {}",jobKey,jobDetailsQuartz.getKey());
			triggerBuilder.forJob(jobDetailsQuartz);
			
			LOG.debug("Set trigger date start {}",triggerJob.getWhenStart());
			
			if(triggerJob.getWhenStart().after(Calendar.getInstance().getTime())){
				triggerBuilder.startAt(triggerJob.getWhenStart());
			}else{
				triggerBuilder.startNow();
			}
			
			
			if(triggerJob.getWhenEnd()!=null){
				LOG.debug("Set trigger date end {}",triggerJob.getWhenEnd());
				triggerBuilder.endAt(triggerJob.getWhenEnd());
			}
			
			LOG.debug("Create simple Scheduler for trigger {}",triggerKey);
			SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
			
			if(triggerJob.getNumberOfIteration()!=null && triggerJob.getNumberOfIteration()>0){
				LOG.debug("Set trigger repeat count at {}",triggerJob.getNumberOfIteration());
				simpleScheduleBuilder.withRepeatCount(triggerJob.getNumberOfIteration());
			}else{
				LOG.debug("Set trigger repeat Forever");
				simpleScheduleBuilder.repeatForever();
			}
			
			if(triggerJob.getSecondsInterval()!=null){
				LOG.debug("Set trigger interval {}",triggerJob.getSecondsInterval());
				simpleScheduleBuilder.withIntervalInSeconds(triggerJob.getSecondsInterval());
			}
			
			triggerBuilder.withSchedule(simpleScheduleBuilder);
			saveTrigger(triggerBuilder.build(),replace);
			
		}catch(SchedulerException e){
			LOG.error("Error to create trigger for job in scheduler", e);
			throw new JobSchedulerException("Error to add trigger " + triggerJob.getUuid(), e);
		}
	}
	
	@Override
	@Transactional
	public void saveCronTrigger(CronJob cronJob,JobDetails jobToExecute,boolean replace) throws JobSchedulerException{
		try {
			JobKey jobKey = new JobKey(jobToExecute.getUuid(),KEY_GROUP_JOB);
			TriggerKey triggerKey = new TriggerKey(cronJob.getUuid(),KEY_GROUP_CRONJOB);
			
			JobDetail jobDetailsQuartz = findJobInQuartz(jobKey);
			if(jobDetailsQuartz==null){
				throw new JobSchedulerException("Impossible to create a Trigger if job does not create. Create it first!");
			}
			
			TriggerBuilder<Trigger> cronTriggerBuilder = TriggerBuilder.newTrigger();
			LOG.debug("Set crontrigger identity new Key({})",triggerKey);
			cronTriggerBuilder.withIdentity(triggerKey);
			
			LOG.debug("Set crontrigger job new JobKey({})",jobKey);
			cronTriggerBuilder.forJob(jobKey);
			
			LOG.debug("Create simple Scheduler for trigger {}",cronJob.getUuid());
			CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronJob.getCronExpression());
			
			cronTriggerBuilder.withSchedule(cronScheduleBuilder);
	
			saveTrigger(cronTriggerBuilder.build(),replace);
			
		}catch(SchedulerException e){
			LOG.error("Error to create cron trigger for job in scheduler", e);
			throw new JobSchedulerException("Error to add cron trigger " + cronJob.getUuid(), e);
		}
	}
	
	@Transactional
	public void saveTrigger(Trigger triggerScheduler,boolean replace) throws SchedulerException, JobSchedulerException{

		LOG.info("Try to add in quartz the trigger [{}] with key {} ",triggerScheduler.getClass().getName(),triggerScheduler.getKey());
		
		if(scheduler.checkExists(triggerScheduler.getKey())){
			
			LOG.debug("The trigger with key {} exist in quartz.",triggerScheduler.getKey());
			
			if(replace){
				LOG.info("Reschedule in quartz the trigger with key {} ",triggerScheduler.getKey());
				scheduler.rescheduleJob(triggerScheduler.getKey(), triggerScheduler);
			}else{
				throw new JobSchedulerException("Trigger exist in quartz, cannot create ttrigger with the same key");
			}
			
		}else{
			scheduler.scheduleJob(triggerScheduler);
		}
		LOG.info("Added in quartz the trigger with key {} ",triggerScheduler.getKey());
		
	}

	@Override
	public void pauseJob(JobDetails jobToPaused) throws SchedulerException {
		
		scheduler.pauseJob(new JobKey(jobToPaused.getUuid(),KEY_GROUP_JOB));
	}

	@Override
	public void resumeJob(JobDetails jobToResume) throws SchedulerException {
		
		scheduler.resumeJob(new JobKey(jobToResume.getUuid(),KEY_GROUP_JOB));
		
	}
}
