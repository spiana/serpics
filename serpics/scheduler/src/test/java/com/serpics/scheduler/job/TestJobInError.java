package com.serpics.scheduler.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.serpics.commerce.session.CommerceSessionContext;

public class TestJobInError extends AbstractJob {

	@Override
	protected void executeJob(JobExecutionContext jobcontext, CommerceSessionContext commerceContext)
			throws JobExecutionException {
		logger.info("################################# Start Job and throw an exception");
		throw new JobExecutionException("Exception in Job Test",false);
	}

}
