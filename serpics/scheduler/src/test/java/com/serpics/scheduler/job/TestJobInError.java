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

import com.serpics.commerce.session.CommerceSessionContext;

public class TestJobInError extends AbstractStoreJob {

	@Override
	protected void executeJob(JobExecutionContext jobcontext, CommerceSessionContext commerceContext)
			throws JobExecutionException {
		logger.info("################################# Start Job and throw an exception");
		throw new JobExecutionException("Exception in Job Test",false);
	}

}
