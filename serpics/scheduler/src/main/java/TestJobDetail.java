import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.scheduler.job.AbstractStoreJob;

/**
 * Copyright 2015-2016 StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author spiana
 *
 */
public class TestJobDetail extends AbstractStoreJob{

	/* (non-Javadoc)
	 * @see com.serpics.scheduler.job.AbstractJob#executeJob(org.quartz.JobExecutionContext, com.serpics.commerce.session.CommerceSessionContext)
	 */
	@Override
	protected void executeJob(JobExecutionContext jobcontext,
			CommerceSessionContext commerceContext)
			throws JobExecutionException {
				System.out.println("I'm running !");
		
	}

}
