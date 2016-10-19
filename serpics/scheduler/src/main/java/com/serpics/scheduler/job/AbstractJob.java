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
