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
import com.serpics.scheduler.model.SystemJobDetails;
import com.serpics.stereotype.VaadinComponent;

@VaadinComponent("systemJobDetailTable")
public class SystemJobDetailMasterTable extends AbstractJobDetailMasterTable<SystemJobDetails> {

	private static final long serialVersionUID = -5987698922480175886L;

	public SystemJobDetailMasterTable() {
		super(SystemJobDetails.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execSave(SystemJobDetails entity,boolean create) throws ClassNotFoundException, JobSchedulerException {
		if (create){
				jobService.createJobDetail((Class<? extends AbstractJob>) Class.forName(entity.getNameClassJob()), entity);
		}else {
				jobService.modifyJobDetail((Class<? extends AbstractJob>) Class.forName(entity.getNameClassJob()), entity);
		}
		
	}

}
