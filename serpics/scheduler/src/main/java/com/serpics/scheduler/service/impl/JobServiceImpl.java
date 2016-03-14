package com.serpics.scheduler.service.impl;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.base.data.model.Store;
import com.serpics.catalog.data.model.Catalog;
import com.serpics.scheduler.exception.JobSchedulerException;
import com.serpics.scheduler.job.AbstractJob;
import com.serpics.scheduler.model.AbstractSchedulerJob;
import com.serpics.scheduler.model.CronJob;
import com.serpics.scheduler.model.JobDetailState;
import com.serpics.scheduler.model.JobDetails;
import com.serpics.scheduler.model.JobLog;
import com.serpics.scheduler.model.JobLogState;
import com.serpics.scheduler.model.TriggerJob;
import com.serpics.scheduler.repositories.CronJobRepository;
import com.serpics.scheduler.repositories.JobDetailsRepository;
import com.serpics.scheduler.repositories.TriggerJobRepository;
import com.serpics.scheduler.service.JobLogService;
import com.serpics.scheduler.service.JobService;
import com.serpics.scheduler.service.SchedulerQuartzService;

@Service("jobService")
public class JobServiceImpl implements JobService {

	Logger LOG = LoggerFactory.getLogger(JobServiceImpl.class);
	
	@Autowired
	private SchedulerQuartzService schedulerQuartzService;
	
	@Resource
	private JobDetailsRepository jobDetailsRepository;
	
	@Resource
	private TriggerJobRepository triggerJobRepository;
	
	@Resource
	private CronJobRepository cronJobRepository;
	
	@Resource
	private JobLogService jobLogService;
	
	@Override
	@Transactional
	public JobDetails createJobDetail(Class<? extends AbstractJob> jobToCreate,JobDetails jobDetails)throws JobSchedulerException{
		
		jobDetails.setStateOfJob(JobDetailState.CREATED);
		
		jobDetails = jobDetailsRepository.saveAndFlush(jobDetails);
		LOG.debug("Saved JobDetails : [uuid: {} ] ",jobDetails.getUuid());
		
		LOG.debug("Create job in quartz");
		schedulerQuartzService.saveJob(jobToCreate, jobDetails,false);
		return jobDetails;
	}
	
	@Override
	@Transactional
	public JobDetails createJobDetail(Class<? extends AbstractJob> jobToCreate,Store store,Catalog catalog) throws JobSchedulerException{
		
		Assert.notNull(jobToCreate, "If you want to create a job, please provide an job object.");
		Assert.notNull(store,"Indicate the Store where the perform job");
		
		LOG.info("Create Job Details for job  [{}] with params store [ {} ] , catalog [ {} ]",jobToCreate.getCanonicalName(),store.getName(),catalog!=null?catalog.getCode():"No Catalog");
		
		JobDetails jobDetails = new JobDetails();
		jobDetails.setStore(store);
		jobDetails.setCatalog(catalog);
		jobDetails.setNameClassJob(jobToCreate.getCanonicalName());
		jobDetails.setStopOnFail(false);
		return createJobDetail(jobToCreate, jobDetails);
	}
	
	@Override
	@Transactional
	public JobDetails modifyJobDetail(Class<? extends AbstractJob> jobToCreate,JobDetails jobDetails)throws JobSchedulerException{
		
		jobDetails.setStateOfJob(JobDetailState.MODIFIED);
		
		jobDetails = jobDetailsRepository.saveAndFlush(jobDetails);
		LOG.debug("Saved JobDetails : [uuid: {} ] ",jobDetails.getUuid());
		
		LOG.debug("Create job in quartz");
		schedulerQuartzService.saveJob(jobToCreate, jobDetails,true);
		return jobDetails;
	}
	
	@Override
	@Transactional
	public AbstractSchedulerJob createTrigger(TriggerJob trigger,JobDetails jobToExecute) throws JobSchedulerException{
		
		LOG.debug("Try to save trigger : {}",trigger.getStringDetail());
		
		Assert.notNull(trigger, "If you want create trigger, please provide an trigger object.");
		Assert.notNull(jobToExecute,"Indicate what the Job perform.");
		
		LOG.info("Start to schedule job by trigger");
		
		
		if(trigger.getWhenStart()==null){
			trigger.setWhenStart(Calendar.getInstance().getTime());
		}
		trigger.setItereted(0);
		trigger.setJobDetail(jobToExecute);
		trigger = triggerJobRepository.saveAndFlush(trigger);
		
		LOG.debug("Saved trigger: [uuid: {} ],",trigger.getUuid());
		
		LOG.debug("Schedule in quartz the job");
		schedulerQuartzService.saveSimpleTrigger(trigger, jobToExecute,false);
		return trigger;
	}

	@Override
	@Transactional
	public AbstractSchedulerJob modifyTrigger(TriggerJob trigger,JobDetails jobToExecute) throws JobSchedulerException{
		
		LOG.debug("Try to save trigger : {}",trigger.getStringDetail());
		
		Assert.notNull(trigger, "If you want create trigger, please provide an trigger object.");
		Assert.notNull(jobToExecute,"Indicate what the Job perform.");
		
		LOG.info("Start to schedule job by trigger");
		
		
		if(trigger.getWhenStart()==null){
			trigger.setWhenStart(Calendar.getInstance().getTime());
		}
		trigger.setItereted(0);
		trigger.setJobDetail(jobToExecute);
		trigger = triggerJobRepository.saveAndFlush(trigger);
		
		LOG.debug("Saved trigger: [uuid: {} ],",trigger.getUuid());
		
		LOG.debug("Schedule in quartz the job");
		
		schedulerQuartzService.saveSimpleTrigger(trigger, jobToExecute,true);
		return trigger;
	}
	
	@Override
	@Transactional
	public AbstractSchedulerJob createCronJob(CronJob cronJob, JobDetails jobToExecute) throws JobSchedulerException{
		
		LOG.debug("Try to save cronJob : {}",cronJob.getStringDetail());
		
		Assert.notNull(cronJob, "If you want create a cron, please provide a cron object.");
		Assert.notNull(jobToExecute,"Indicate what the Job perform.");

		cronJob.setJobDetail(jobToExecute);
		cronJob = cronJobRepository.saveAndFlush(cronJob);
		
			
		schedulerQuartzService.saveCronTrigger(cronJob, jobToExecute,false);
		return cronJob;
	}
	
	@Override
	@Transactional
	public AbstractSchedulerJob modifyCronJob(CronJob cronJob, JobDetails jobToExecute) throws JobSchedulerException{
		
		LOG.debug("Try to save cronJob : {}",cronJob.getStringDetail());
		
		Assert.notNull(cronJob, "If you want create a cron, please provide a cron object.");
		Assert.notNull(jobToExecute,"Indicate what the Job perform.");
		cronJob.setJobDetail(jobToExecute);
		cronJob = cronJobRepository.saveAndFlush(cronJob);
		
		schedulerQuartzService.saveCronTrigger(cronJob, jobToExecute,true);
		return cronJob;
	}
	
	@Override
	@Transactional
	public void pauseJob(JobDetails jobToPaused){
		
		LOG.info("Stopping all Scheduler of JOB [{}]",jobToPaused.getUuid());
		
		Assert.notNull(jobToPaused,"Indicate what the Job to stop.");
		
		jobToPaused = jobDetailsRepository.findByUUID(jobToPaused.getUuid());
		JobDetailState stateOfJob = JobDetailState.PAUSED;
		
		try {
			schedulerQuartzService.pauseJob(jobToPaused);
		} catch (SchedulerException e) {
			LOG.error("Error to set in pause all scheduler of Job ["+jobToPaused.getUuid()+"]",e);
			stateOfJob = JobDetailState.ERROR;
		}
		
		jobDetailsRepository.saveAndFlush(jobToPaused);
		jobToPaused.setStateOfJob(stateOfJob);
		
	
	}
	
	@Override
	@Transactional
	public void resumeJob(JobDetails jobToResume){
		
		LOG.info("Resume all Scheduler of JOB [{}]",jobToResume.getUuid());
		
		Assert.notNull(jobToResume,"Indicate what the Job to resume.");
		
		jobToResume = jobDetailsRepository.findByUUID(jobToResume.getUuid());
		JobDetailState stateOfJob = JobDetailState.RESUMING;
		
		try {
			schedulerQuartzService.resumeJob(jobToResume);
		} catch (SchedulerException e) {
			LOG.error("Error to resume all scheduler of Job ["+jobToResume.getUuid()+"]",e);
			stateOfJob = JobDetailState.ERROR;
		}
		jobDetailsRepository.saveAndFlush(jobToResume);
		jobToResume.setStateOfJob(stateOfJob);
		
	}	
	
	@Override
	@Transactional
	public void manageJobToStart(JobExecutionContext context){
		
		JobDetails job = jobDetailsRepository.findByUUID(context.getJobDetail().getKey().getName());
		job.setStateOfJob(JobDetailState.RUNNING);
		jobDetailsRepository.save(job);
	}
	
	@Override
	@Transactional
	public void manageJobFinished(JobExecutionContext context,JobExecutionException jobException){
		
		LOG.debug("Manage Finished Job {}",context.getJobDetail().getKey());
		
		JobDetails job = jobDetailsRepository.findByUUID(context.getJobDetail().getKey().getName());
		
		Assert.notNull(job, "Not found job to manage");
		
		if(jobException!=null){
			LOG.debug("Job {} throw an exception with message [{}]",context.getJobDetail().getKey(),jobException.getMessage());
			JobLog jLog = new JobLog();
			jLog.setDateStart(new Date());
			jLog.setDateEnd(new Date());
			jLog.setMessage(jobException.getMessage());
			jLog.setState(JobLogState.EXCEPTION);
			jobLogService.addJobLog(context.getTrigger().getKey().getName(), jLog);
			
			LOG.info("Set job {} in state ERROR",context.getJobDetail().getKey());
			job.setStateOfJob(JobDetailState.ERROR);
			jobDetailsRepository.saveAndFlush(job);
			
			LOG.debug("Job {} must to be stopped on Fail ? {}",context.getJobDetail().getKey(),job.isStopOnFail());
			if(job.isStopOnFail()){
				LOG.info("Set job {} in pause",context.getJobDetail().getKey());
				pauseJob(job);
			}
		}else{
			job.setStateOfJob(JobDetailState.SUCCESFULL);
			jobDetailsRepository.saveAndFlush(job);
		}
		
		
	}
}
