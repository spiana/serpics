package com.serpics.scheduler.job;

import org.quartz.JobExecutionContext;

import com.serpics.commerce.session.CommerceSessionContext;

public class TestJob extends AbstractStoreJob {

	@Override
	protected void executeJob(JobExecutionContext jobcontext, CommerceSessionContext commerceContext) {
		
		logger.info("******************************Run Job Test. next fire time {}",jobcontext.getNextFireTime());
		
	}

}
