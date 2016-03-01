package com.serpics.scheduler.listener;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.quartz.CronTrigger;
import org.quartz.JobExecutionContext;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.listeners.TriggerListenerSupport;

import com.serpics.scheduler.model.AbstractSchedulerSerpicsJob;
import com.serpics.scheduler.model.JobLog;
import com.serpics.scheduler.model.JobLogState;
import com.serpics.scheduler.model.TriggerJob;
import com.serpics.scheduler.service.JobLogService;
import com.serpics.scheduler.service.JobService;
import com.serpics.scheduler.service.SchedulerSerpicsService;

/**
 * An Trigger Listener implementation to manage result of execution of Thread.
 * @author alessandro.marasco@tinvention.net
 *
 */
public class SchedulerTriggerListener extends TriggerListenerSupport {

	private List<Trigger.CompletedExecutionInstruction> listOfErrorJob = Arrays.asList(Trigger.CompletedExecutionInstruction.SET_TRIGGER_ERROR,Trigger.CompletedExecutionInstruction.SET_ALL_JOB_TRIGGERS_ERROR);
	@Resource
	private JobLogService jobLogService;
	
	@Resource
	private JobService jobService;
	
	@Resource
	private SchedulerSerpicsService schedulerSerpicsService;
	
	@Override
	public String getName() {
		return "SerpicsTriggerListener";
	}
	
	@Override
	public void triggerFired(Trigger trigger, JobExecutionContext context) {
		
		getLog().debug("triggerFired {}  of Job {} on store {}, mayFireAgain? ",trigger.getKey(),trigger.getJobKey(),context.getJobDetail().getJobDataMap().get("realmStore"),trigger.mayFireAgain());
		
		JobLog jLog = new JobLog();
		jLog.setDateLog(new Date());
		jLog.setState(JobLogState.STARTING);
		
		jobLogService.addJobLog(trigger.getKey().getName(), jLog);
		
	}
	
	@Override
	public void triggerMisfired(Trigger trigger) {
		getLog().info("triggerMisfired");
	}
	
	@Override
	public void triggerComplete(Trigger trigger, JobExecutionContext context,
			Trigger.CompletedExecutionInstruction triggerInstructionCode) {
		
		getLog().info("triggerComplete "+triggerInstructionCode+"  next? "+trigger.mayFireAgain());
		
		JobLog jLog = new JobLog();
		jLog.setDateLog(new Date());
		if(listOfErrorJob.contains(triggerInstructionCode)){
			jLog.setState(JobLogState.ERROR);
		}else{
			jLog.setState(JobLogState.FINISHED);
		}
		
		jobLogService.addJobLog(trigger.getKey().getName(), jLog);
		
		AbstractSchedulerSerpicsJob abstractScedulerJob = schedulerSerpicsService.getSchedulerSerpicsJob(trigger.getKey().getName());
		
		if(trigger instanceof SimpleTrigger){
			
			SimpleTrigger simple = (SimpleTrigger)trigger;
			getLog().info("SimpleTrigger timesTriggered (" + simple.getTimesTriggered() +") count ("+simple.getRepeatCount()+") ");
			abstractScedulerJob.setNextRun(simple.getNextFireTime());
			((TriggerJob)abstractScedulerJob).setItereted(simple.getTimesTriggered());
			
		}else if(trigger instanceof CronTrigger){
			
			CronTrigger cron = (CronTrigger)trigger;
			getLog().info("CronTrigger "+cron.getExpressionSummary()+ "  "+cron.getNextFireTime());
			abstractScedulerJob.setNextRun(cron.getNextFireTime());
			
		}else{
			getLog().info("No trigger recognise");
		}
		
		schedulerSerpicsService.save(abstractScedulerJob);
	}

}
