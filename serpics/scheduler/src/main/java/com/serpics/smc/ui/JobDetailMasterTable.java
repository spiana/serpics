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
package com.serpics.smc.ui;

import com.serpics.scheduler.exception.JobSchedulerException;
import com.serpics.scheduler.job.AbstractJob;
import com.serpics.scheduler.model.StoreJobDetails;
import com.serpics.stereotype.VaadinComponent;

/**
 * @author spiana
 *
 */
@VaadinComponent("jobDetailTable")
public class JobDetailMasterTable extends AbstractJobDetailMasterTable<StoreJobDetails> {

	private static final long serialVersionUID = 6013043943899872749L;

	public JobDetailMasterTable() {
		super(StoreJobDetails.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public StoreJobDetails execSave(StoreJobDetails entity, boolean create) throws ClassNotFoundException, JobSchedulerException {
		if (create) {
			return (StoreJobDetails) jobService.createJobDetail((Class<? extends AbstractJob>) Class.forName(entity.getNameClassJob()), entity);
		} else {
			return (StoreJobDetails) jobService.modifyJobDetail((Class<? extends AbstractJob>) Class.forName(entity.getNameClassJob()), entity);
		}

	}

}
