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
package com.serpics.smc.ui;

import javax.annotation.Resource;

import com.serpics.scheduler.exception.JobSchedulerException;
import com.serpics.scheduler.job.AbstractJob;
import com.serpics.scheduler.model.JobDetails;
import com.serpics.scheduler.service.JobService;
import com.serpics.scheduler.service.SchedulerService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterForm;
import com.serpics.vaadin.ui.MasterTable;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;

/**
 * @author spiana
 *
 */
@VaadinComponent("jobDetailTable")
public class JobDetailMasterTable extends MasterTable<JobDetails> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5686604758190650946L;

	@Resource
	private SchedulerService schedulerService;
	@Resource
	private JobService jobService;
	
	@Resource
	private JobSchedulerTable jobSchedulerTable;
	
	/**
	 * 
	 */
	public JobDetailMasterTable() {
		super(JobDetails.class);
		
	}
	
	/* (non-Javadoc)
	 * @see com.serpics.vaadin.ui.MasterTable#buildEntityWindow()
	 */
	@Override
	public EntityFormWindow<JobDetails> buildEntityWindow() {
		EntityFormWindow<JobDetails> b = new EntityFormWindow<JobDetails>();
		b.addTab(new MasterForm<JobDetails>(JobDetails.class) {
			private static final long serialVersionUID = -4339238026177435493L;
			/* (non-Javadoc)
			 * @see com.serpics.vaadin.ui.MasterForm#save()
			 */
			@Override
			public void save() throws CommitException {
				fieldGroup.commit();
				entityItem.commit();
				
				if (!entityItem.isPersistent()){
					try {
						jobService.createJobDetail((Class<? extends AbstractJob>) Class.forName(entityItem.getEntity().getNameClassJob()), entityItem.getEntity());
					} catch (ClassNotFoundException | JobSchedulerException e) {
						throw new CommitException(e);
					}
				}else {
					try {
						jobService.modifyJobDetail((Class<? extends AbstractJob>) Class.forName(entityItem.getEntity().getNameClassJob()), entityItem.getEntity());
					} catch (ClassNotFoundException | JobSchedulerException e) {
						throw new CommitException(e);
					}
				}
			
				if ( !entityItem.isPersistent())
					entityItem.getContainer().addEntity(entityItem.getEntity());
			}
		}, "main");
		b.addTab(jobSchedulerTable, "triggers");
		return b;
	}
}
