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

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.serpics.base.commerce.CommerceEngine;
import com.serpics.core.Engine;
import com.serpics.scheduler.exception.JobSchedulerException;
import com.serpics.scheduler.job.AbstractStoreJob;
import com.serpics.scheduler.model.JobLog;
import com.serpics.scheduler.model.StoreJobDetails;
import com.serpics.scheduler.service.JobService;
import com.serpics.scheduler.service.SchedulerService;
import com.serpics.vaadin.data.utils.I18nUtils;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterDetailTable;
import com.serpics.vaadin.ui.MasterForm;
import com.serpics.vaadin.ui.MasterTable;
import com.serpics.vaadin.ui.component.ComboBox;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;

/**
 * @author spiana
 *
 */
public abstract class AbstractJobDetailMasterTable<T> extends MasterTable<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5686604758190650946L;

	@Resource
	private SchedulerService schedulerService;
	@Resource
	protected JobService jobService;

	@Resource
	private JobSchedulerTable jobSchedulerTable;
	
	@Resource
	CommerceEngine commerceEngine;

	/**
	 * 
	 */
	public AbstractJobDetailMasterTable(Class<T> entityClass) {
		super(entityClass);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.serpics.vaadin.ui.MasterTable#buildEntityWindow()
	 */

	public MasterForm<T> getMasterForm() {

		return new MasterForm<T>(getEntityType()) {
			private static final long serialVersionUID = -4339238026177435493L;

			@Override
			protected void buildContent() {
				
				super.buildContent();
				Field<?> stopOnFail = fieldGroup.getField("stopOnFail");
				
				Button resumeJobButton = new Button(I18nUtils.getMessage("smc.button.resumejob", "Resume"), new Button.ClickListener() {
					
					@Override
					public void buttonClick(ClickEvent event) {
						// TODO Auto-generated method stub
						
					}
				});
				
				resumeJobButton.setEnabled((Boolean)stopOnFail.getValue());
				
				int index= getComponentIndex(stopOnFail);
				addComponent(resumeJobButton, index+1);
				
				
			}
			@Override
			protected Field<?> createField(String pid) {
				// Add  Select for choose the job to run.
				if (pid.equals("nameClassJob") && 
						entityClass.isAssignableFrom(StoreJobDetails.class)
						){
					ComboBox _s = new ComboBox("nameClassJob");
					loadAllJobs(_s);
					_s.setWidth("50%");
					return bindField(pid, _s);
				}else
					return super.createField(pid);
				
			}
			/*
			 * (non-Javadoc)
			 * 
			 * @see com.serpics.vaadin.ui.MasterForm#save()
			 */
			@Override
			public void save() throws CommitException {
				fieldGroup.commit();
				entityItem.commit();
				T job = null;
				try {
					job = execSave(entityItem.getEntity(), !entityItem.isPersistent());
				} catch (ClassNotFoundException | JobSchedulerException e) {
					throw new CommitException(e);
				}
				entityItem.refresh();
				entityItem.getContainer().refresh();
				}
			
			private Set<String> loadAllJobs(ComboBox combo){
				Map<String , AbstractStoreJob> jobs = commerceEngine.getApplicationContext().getBeansOfType(AbstractStoreJob.class);
				Set<String>  jobClasses = new HashSet<String>();
				for (String component : jobs.keySet()) {
					combo.addItem(jobs.get(component).getClass().getName());
					combo.setItemCaption(jobs.get(component).getClass().getName(), component);
					jobClasses.add(jobs.get(component).getClass().getName());
				}
				return jobClasses;
			}
			
		};
		
	}

	public abstract T execSave(T entity, boolean create) throws ClassNotFoundException, JobSchedulerException;// entityItem.getEntity().getNameClassJob()

	@Override
	public EntityFormWindow<T> buildEntityWindow() {
		EntityFormWindow<T> b = new EntityFormWindow<T>();
		MasterDetailTable<JobLog, T> loggerTab = new MasterDetailTable<JobLog, T>(JobLog.class ,"logs") {};
		
		b.addTab(getMasterForm(), "main");
		b.addTab(jobSchedulerTable, "triggers");
		b.addTab(loggerTab, "logs");
		return b;
	}
}
