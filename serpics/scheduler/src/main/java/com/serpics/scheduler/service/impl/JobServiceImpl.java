package com.serpics.scheduler.service.impl;

import java.util.Calendar;

import javax.annotation.Resource;

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
import com.serpics.scheduler.model.SerpicsJobDetails;
import com.serpics.scheduler.model.TriggerJob;
import com.serpics.scheduler.repositories.CronJobRepository;
import com.serpics.scheduler.repositories.SerpicsJobDetailsRepository;
import com.serpics.scheduler.repositories.TriggerJobRepository;
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
		
		jobDetails = serpicsJobDetailsRepository.saveAndFlush(jobDetails);
		LOG.debug("Saved SerpicsJobDetails : [uuid: {} ] ",jobDetails.getUuid());
		
		LOG.debug("Create job in quartz");
		serpicsQuartzService.addJob(jobToCreate, jobDetails);
		
		return jobDetails;
	}

	@Override
	@Transactional
	public AbstractSchedulerSerpicsJob createTrigger(TriggerJob trigger,SerpicsJobDetails jobToExecute) throws SerpicsSchedulerException{
		
		LOG.debug("Try to save trigger : {0}",trigger.getStringDetail());
		
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
		
		LOG.debug("Try to save cronJob : {0}",cronJob.getStringDetail());
		
		Assert.notNull(cronJob, "If you want create a cron, please provide a cron object.");
		Assert.notNull(jobToExecute,"Indicate what the Job perform.");
		
		cronJob.setJobDetail(jobToExecute);
		
		cronJob = cronJobRepository.saveAndFlush(cronJob);
		
		serpicsQuartzService.addCronTrigger(cronJob, jobToExecute);
		return cronJob;
	}
	
}
