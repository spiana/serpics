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

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.quartz.CronTrigger;
import org.quartz.JobExecutionContext;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.listeners.TriggerListenerSupport;

import com.serpics.scheduler.model.AbstractSchedulerJob;
import com.serpics.scheduler.model.JobLog;
import com.serpics.scheduler.model.JobLogState;
import com.serpics.scheduler.model.TriggerJob;
import com.serpics.scheduler.service.JobLogService;
import com.serpics.scheduler.service.JobService;
import com.serpics.scheduler.service.SchedulerService;

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
	private SchedulerService schedulerService;
	
	@Override
	public String getName() {
		return "DefaultTriggerListener";
	}
	
	@Override
	public void triggerFired(Trigger trigger, JobExecutionContext context) {
		
		getLog().debug("triggerFired {}  of Job {} on store {}, mayFireAgain? ",trigger.getKey(),trigger.getJobKey(),context.getJobDetail().getJobDataMap().get("realmStore"),trigger.mayFireAgain());
		
		JobLog jLog = new JobLog();
		jLog.setDateStart(new Date());
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
		
		AbstractSchedulerJob abstractScedulerJob = schedulerService.getSchedulerJob(trigger.getKey().getName());
		
		
		JobLog jLog = jobLogService.findPendingJobLogForJob(abstractScedulerJob);
		jLog.setDateEnd(new Date());
		
		if(listOfErrorJob.contains(triggerInstructionCode)){
			jLog.setState(JobLogState.ERROR);
		}else{
			jLog.setState(JobLogState.FINISHED);
		}
		
		jobLogService.addJobLog(trigger.getKey().getName(), jLog);
		
		
		
		if(trigger instanceof SimpleTrigger){
			
			SimpleTrigger simple = (SimpleTrigger)trigger;
			getLog().info("SimpleTrigger timesTriggered (" + simple.getTimesTriggered() +") count ("+simple.getRepeatCount()+") ");
			abstractScedulerJob.setNextRun(simple.getNextFireTime());
			abstractScedulerJob.setLastRun(new Date());
			((TriggerJob)abstractScedulerJob).setItereted(simple.getTimesTriggered());
		}else if(trigger instanceof CronTrigger){
			
			CronTrigger cron = (CronTrigger)trigger;
			getLog().info("CronTrigger "+cron.getExpressionSummary()+ "  "+cron.getNextFireTime());
			abstractScedulerJob.setNextRun(cron.getNextFireTime());
			abstractScedulerJob.setLastRun(new Date());
			
		}else{
			getLog().info("No trigger recognise");
		}
		
		schedulerService.save(abstractScedulerJob);
	}

}
