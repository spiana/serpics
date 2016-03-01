package com.serpics.scheduler.service.impl;

import java.util.Calendar;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.scheduler.exception.SerpicsSchedulerException;
import com.serpics.scheduler.job.AbstractSerpicsJob;
import com.serpics.scheduler.model.CronJob;
import com.serpics.scheduler.model.SerpicsJobDetails;
import com.serpics.scheduler.model.TriggerJob;
import com.serpics.scheduler.service.SerpicsQuartzService;

@Service("serpicsQuartzService")
public class SerpicsQuartzServiceImpl implements SerpicsQuartzService {

	Logger LOG = LoggerFactory.getLogger(SerpicsQuartzServiceImpl.class);

	private final String KEY_GROUP_TRIGGER = "DEFAULT_SERPICS_TRIGGER";
	private final String KEY_GROUP_CRONJOB = "DEFAULT_SERPICS_CRONJOB";
	private final String KEY_GROUP_JOB = "DEFAULT_SERPICS_JOB";

	@Autowired
	private Scheduler scheduler;

	@Override
	@Transactional
	public void addJob(Class<? extends AbstractSerpicsJob> jobToCreate, SerpicsJobDetails jobDetails)
			throws SerpicsSchedulerException {

		Assert.notNull(jobDetails, "Serpics Job Details cannot be null");
		LOG.debug("Try to create job in quartz with identity key [ name: {} , group: {} ]",jobDetails.getUuid(), KEY_GROUP_JOB);
		
		JobDetail jobInQuartz;
		try {
			jobInQuartz = findJobInQuartz(jobDetails.getUuid(), KEY_GROUP_JOB);
			
			if (jobInQuartz != null) {
				throw new SerpicsSchedulerException("Job with same key just exist in quartz");
			}
			
			jobInQuartz = JobBuilder.newJob(jobToCreate).withIdentity(jobDetails.getUuid(), KEY_GROUP_JOB)
					.usingJobData("realmStore", jobDetails.getStore().getName())
					.usingJobData("catalog", jobDetails.getCatalog()!=null?jobDetails.getCatalog().getCode():"")
					.storeDurably(true)
					.build();
//			Scheduler scheduler = quartzScheduler.getScheduler();
			scheduler.addJob(jobInQuartz, false);
			LOG.info("Add job in scheduler with identity key [ name: {} , group: {} ]", jobDetails.getUuid(),
					KEY_GROUP_JOB);
			} catch (SchedulerException e) {
				LOG.error("Error to add job in scheduler", e);
				throw new SerpicsSchedulerException("Error to add Job " + jobDetails.getUuid(), e);
			}
	}

	private JobDetail findJobInQuartz(String uuid, String group) throws SchedulerException {
		JobDetail jobToReturn = null;
//		Scheduler scheduler = quartzScheduler.getScheduler();

		jobToReturn = scheduler.getJobDetail(new JobKey(uuid, group));
		return jobToReturn;
	}
	
	@Override
	@Transactional
	public void addSimpleTrigger(TriggerJob triggerJob,SerpicsJobDetails jobToExecute) throws SerpicsSchedulerException{
		
		try {
			JobDetail jobDetailsQuartz = findJobInQuartz(jobToExecute.getUuid(),KEY_GROUP_JOB);
			if(jobDetailsQuartz==null){
				throw new SerpicsSchedulerException("Impossible to create a Trigger if job does not create. Create it first!");
			}
			
			TriggerBuilder<SimpleTrigger> triggerBuilder = new SimpleTriggerImpl().getTriggerBuilder();
			
			
			
			LOG.debug("Set trigger identity new Key({},{})",triggerJob.getUuid(),KEY_GROUP_TRIGGER);
			triggerBuilder.withIdentity(triggerJob.getUuid(),KEY_GROUP_TRIGGER);
			
			LOG.debug("Set trigger job new JobKey({},{}) == {}",jobToExecute.getUuid(),KEY_GROUP_JOB,jobDetailsQuartz.getKey());
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
			
			LOG.debug("Create simple Scheduler for trigger {}",triggerJob.getUuid());
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
			
			addTrigger(triggerBuilder.build());
		}catch(SchedulerException e){
			LOG.error("Error to create trigger for job in scheduler", e);
			throw new SerpicsSchedulerException("Error to add trigger " + triggerJob.getUuid(), e);
		}
	}
	
	@Override
	@Transactional
	public void addCronTrigger(CronJob cronJob,SerpicsJobDetails jobToExecute) throws SerpicsSchedulerException{
		
		try {
			JobDetail jobDetailsQuartz = findJobInQuartz(jobToExecute.getUuid(),KEY_GROUP_JOB);
			if(jobDetailsQuartz==null){
				throw new SerpicsSchedulerException("Impossible to create a Trigger if job does not create. Create it first!");
			}
			
			TriggerBuilder<CronTrigger> cronTriggerBuilder = new CronTriggerImpl().getTriggerBuilder();
			
			LOG.debug("Set crontrigger identity new Key({},{})",cronJob.getUuid(),KEY_GROUP_CRONJOB);
			cronTriggerBuilder.withIdentity(jobToExecute.getUuid(),KEY_GROUP_CRONJOB);
			
			LOG.debug("Set crontrigger job new JobKey({},{})",jobToExecute.getUuid(),KEY_GROUP_JOB);
			cronTriggerBuilder.forJob(jobDetailsQuartz.getKey());
			
			LOG.debug("Create simple Scheduler for trigger {}",cronJob.getUuid());
			CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronJob.getCronExpression());
			
			cronTriggerBuilder.withSchedule(cronScheduleBuilder);
			
			addTrigger(cronScheduleBuilder.build());
		}catch(SchedulerException e){
			LOG.error("Error to create cron trigger for job in scheduler", e);
			throw new SerpicsSchedulerException("Error to add cron trigger " + cronJob.getUuid(), e);
		}
	}
	
	@Transactional
	public void addTrigger(Trigger triggerScheduler) throws SchedulerException{
//		Scheduler scheduler = quartzScheduler.getScheduler();
		LOG.info("Try to add in quartz the trigger [] with key {} ",triggerScheduler.getClass().getName(),triggerScheduler.getKey());
		
		scheduler.scheduleJob(triggerScheduler);
		//FIX to wake up scheduler
//		if(scheduler.isInStandbyMode() && !scheduler.isShutdown()){
//			scheduler.start();
//		}
		LOG.info("Added in quartz the trigger with key {} ",triggerScheduler.getKey());
		
	}

	@Override
	public void pauseJob(SerpicsJobDetails jobToPaused) throws SchedulerException {
		
		scheduler.pauseJob(new JobKey(jobToPaused.getUuid(),KEY_GROUP_JOB));
	}

	@Override
	public void resumeJob(SerpicsJobDetails jobToResume) throws SchedulerException {
		
		scheduler.resumeJob(new JobKey(jobToResume.getUuid(),KEY_GROUP_JOB));
		
	}
}
