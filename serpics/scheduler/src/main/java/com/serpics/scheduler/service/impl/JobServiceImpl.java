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
import com.serpics.scheduler.exception.SerpicsSchedulerException;
import com.serpics.scheduler.job.AbstractSerpicsJob;
import com.serpics.scheduler.model.AbstractSchedulerSerpicsJob;
import com.serpics.scheduler.model.CronJob;
import com.serpics.scheduler.model.JobDetailState;
import com.serpics.scheduler.model.JobLog;
import com.serpics.scheduler.model.JobLogState;
import com.serpics.scheduler.model.SerpicsJobDetails;
import com.serpics.scheduler.model.TriggerJob;
import com.serpics.scheduler.repositories.CronJobRepository;
import com.serpics.scheduler.repositories.SerpicsJobDetailsRepository;
import com.serpics.scheduler.repositories.TriggerJobRepository;
import com.serpics.scheduler.service.JobLogService;
import com.serpics.scheduler.service.JobService;
import com.serpics.scheduler.service.SerpicsQuartzService;

@Service("jobService")
public class JobServiceImpl implements JobService {

	Logger LOG = LoggerFactory.getLogger(JobServiceImpl.class);
	
	@Autowired
	private SerpicsQuartzService serpicsQuartzService;
	
	@Resource
	private SerpicsJobDetailsRepository serpicsJobDetailsRepository;
	
	@Resource
	private TriggerJobRepository triggerJobRepository;
	
	@Resource
	private CronJobRepository cronJobRepository;
	
	@Resource
	private JobLogService jobLogService;
	
	@Override
	@Transactional
	public SerpicsJobDetails createJobDetail(Class<? extends AbstractSerpicsJob> jobToCreate,SerpicsJobDetails jobDetails)throws SerpicsSchedulerException{
		
		jobDetails.setStateOfJob(JobDetailState.CREATED);
		
		jobDetails = serpicsJobDetailsRepository.saveAndFlush(jobDetails);
		LOG.debug("Saved SerpicsJobDetails : [uuid: {} ] ",jobDetails.getUuid());
		
		LOG.debug("Create job in quartz");
		serpicsQuartzService.addJob(jobToCreate, jobDetails);
		return jobDetails;
	}
	
	@Override
	@Transactional
	public SerpicsJobDetails createJobDetail(Class<? extends AbstractSerpicsJob> jobToCreate,Store store,Catalog catalog) throws SerpicsSchedulerException{
		
		Assert.notNull(jobToCreate, "If you want to create a job, please provide an job object.");
		Assert.notNull(store,"Indicate the Store where the perform job");
		
		LOG.info("Create Job Details for job  [{}] with params store [ {} ] , catalog [ {} ]",jobToCreate.getCanonicalName(),store.getName(),catalog!=null?catalog.getCode():"No Catalog");
		
		SerpicsJobDetails jobDetails = new SerpicsJobDetails();
		jobDetails.setStore(store);
		jobDetails.setCatalog(catalog);
		jobDetails.setNameClassJob(jobToCreate.getCanonicalName());
		jobDetails.setStopOnFail(false);
		return createJobDetail(jobToCreate, jobDetails);
	}

	@Override
	@Transactional
	public AbstractSchedulerSerpicsJob createTrigger(TriggerJob trigger,SerpicsJobDetails jobToExecute) throws SerpicsSchedulerException{
		
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
		serpicsQuartzService.addSimpleTrigger(trigger, jobToExecute);
		return trigger;
	}

	@Override
	@Transactional
	public AbstractSchedulerSerpicsJob createCronJob(CronJob cronJob, SerpicsJobDetails jobToExecute) throws SerpicsSchedulerException{
		
		LOG.debug("Try to save cronJob : {}",cronJob.getStringDetail());
		
		Assert.notNull(cronJob, "If you want create a cron, please provide a cron object.");
		Assert.notNull(jobToExecute,"Indicate what the Job perform.");
		
		cronJob.setJobDetail(jobToExecute);
		
		cronJob = cronJobRepository.saveAndFlush(cronJob);
		
		serpicsQuartzService.addCronTrigger(cronJob, jobToExecute);
		return cronJob;
	}
	@Override
	public void pauseJob(SerpicsJobDetails jobToPaused){
		
		LOG.info("Stopping all Scheduler of JOB [{}]",jobToPaused.getUuid());
		
		Assert.notNull(jobToPaused,"Indicate what the Job to stop.");
		
		jobToPaused = serpicsJobDetailsRepository.findByUUID(jobToPaused.getUuid());
		JobDetailState stateOfJob = JobDetailState.PAUSED;
		
		try {
			serpicsQuartzService.pauseJob(jobToPaused);
		} catch (SchedulerException e) {
			LOG.error("Error to set in pause all scheduler of Job ["+jobToPaused.getUuid()+"]",e);
			stateOfJob = JobDetailState.ERROR;
		}
		
		jobToPaused.setStateOfJob(stateOfJob);
		
		serpicsJobDetailsRepository.saveAndFlush(jobToPaused);
	}
	
	@Override
	public void resumeJob(SerpicsJobDetails jobToResume){
		
		LOG.info("Resume all Scheduler of JOB [{}]",jobToResume.getUuid());
		
		Assert.notNull(jobToResume,"Indicate what the Job to resume.");
		
		jobToResume = serpicsJobDetailsRepository.findByUUID(jobToResume.getUuid());
		JobDetailState stateOfJob = JobDetailState.RESUMING;
		
		try {
			serpicsQuartzService.resumeJob(jobToResume);
		} catch (SchedulerException e) {
			LOG.error("Error to resume all scheduler of Job ["+jobToResume.getUuid()+"]",e);
			stateOfJob = JobDetailState.ERROR;
		}
		jobToResume.setStateOfJob(stateOfJob);
		
		serpicsJobDetailsRepository.saveAndFlush(jobToResume);
		
	}	
	
	@Override
	public void manageJobToStart(JobExecutionContext context){
		
		SerpicsJobDetails job = serpicsJobDetailsRepository.findByUUID(context.getJobDetail().getKey().getName());
		job.setStateOfJob(JobDetailState.RUNNING);
		serpicsJobDetailsRepository.save(job);
	}
	
	@Override
	public void manageJobFinished(JobExecutionContext context,JobExecutionException jobException){
		
		LOG.debug("Manage Finished Job {}",context.getJobDetail().getKey());
		
		SerpicsJobDetails job = serpicsJobDetailsRepository.findByUUID(context.getJobDetail().getKey().getName());
		
		Assert.notNull(job, "Not found job to manage");
		
		if(jobException!=null){
			LOG.debug("Job {} throw an exception with message [{}]",context.getJobDetail().getKey(),jobException.getMessage());
			JobLog jLog = new JobLog();
			jLog.setDateLog(new Date());
			jLog.setMessage(jobException.getMessage());
			jLog.setState(JobLogState.EXCEPTION);
			jobLogService.addJobLog(context.getTrigger().getKey().getName(), jLog);
			
			LOG.info("Set job {} in state ERROR",context.getJobDetail().getKey());
			job.setStateOfJob(JobDetailState.ERROR);
			serpicsJobDetailsRepository.saveAndFlush(job);
			
			LOG.debug("Job {} must to be stopped on Fail ? {}",context.getJobDetail().getKey(),job.isStopOnFail());
			if(job.isStopOnFail()){
				LOG.info("Set job {} in pause",context.getJobDetail().getKey());
				pauseJob(job);
			}
		}else{
			job.setStateOfJob(JobDetailState.SUCCESFULL);
			serpicsJobDetailsRepository.saveAndFlush(job);
		}
		
		
	}
}
