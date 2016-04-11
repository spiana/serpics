package com.serpics.scheduler.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public abstract class AbstractJob extends QuartzJobBean {

	protected Logger logger = LoggerFactory.getLogger(AbstractJob.class);
			
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		executeJob(arg0);
	}
	
	/**
	 * Method to run
	 * 
	 * @param jobcontext
	 * @param commerceContext
	 */
	protected abstract void executeJob(JobExecutionContext jobcontext) throws JobExecutionException;

}
