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

import java.util.Collection;
import java.util.logging.Logger;

import javax.annotation.Resource;

import com.serpics.base.commerce.CommerceEngine;
import com.serpics.scheduler.exception.JobSchedulerException;
import com.serpics.scheduler.model.AbstractSchedulerJob;
import com.serpics.scheduler.model.CronJob;
import com.serpics.scheduler.model.JobDetails;
import com.serpics.scheduler.model.TriggerJob;
import com.serpics.scheduler.service.JobService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.jpacontainer.ServiceContainerFactory;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterDetailTable;
import com.serpics.vaadin.ui.MasterForm;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.v7.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.v7.data.util.BeanItem;

/**
 * @author spiana
 *
 */
@VaadinComponent("schedulersTable")
public class JobSchedulerTable extends MasterDetailTable<AbstractSchedulerJob, JobDetails>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4975551807079274944L;
	
	private transient JPAContainer<CronJob> cronContainer;
	private transient JPAContainer<TriggerJob> triggerContainer;
	
	@Resource
	private transient JobService jobService;
	
	@Resource
	private transient CommerceEngine engine;
	
	
	/**
	 * 
	 */
	public JobSchedulerTable() {
		super(AbstractSchedulerJob.class, "schedulers");
	}
	
	/* (non-Javadoc)
	 * @see com.serpics.vaadin.ui.MasterTable#buildContainer()
	 */
	@Override
	protected void buildContainer() {
		super.buildContainer();
		cronContainer = ServiceContainerFactory.make(CronJob.class);
		triggerContainer = ServiceContainerFactory.make(TriggerJob.class);
	}

	/* (non-Javadoc)
	 * @see com.serpics.vaadin.ui.MasterTable#buildContent()
	 */
	@Override
	protected void buildContent() {
		super.buildContent();
	
		MenuBar b = new MenuBar();
		MenuItem items = b.addItem("add", null);
		
		items.addItem("add cronJob", new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				EntityFormWindow<CronJob> m = createCronJobEditor();
				edit(m, createCronJobEntityItem(), true);
			}
		});

		items.addItem("add trigger", new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				EntityFormWindow<TriggerJob> m = createTriggerJobEditor();
				edit(m, createTriggerJobEntityItem(), true);
			}
		});
	
		
		editButtonPanel.replaceComponent(newButton, b);
		
		}
	
	/* (non-Javadoc)
	 * @see com.serpics.vaadin.ui.MasterTable#modify(java.lang.Object)
	 */
	@Override
	public void modify(Object itemsId) {
		Object itemId = null;
		if (itemsId instanceof Collection)
				itemId = ((Collection)itemsId).iterator().next();
		else
			itemId = itemsId;
		
		switch (container.getItem(itemId).getEntity().getType()){
		case 0: // cronjob
			EntityItem<CronJob> cron = cronContainer.getItem(itemId);	
			edit(createCronJobEditor(), cron, false);
			break;
		case 1: //trigger
			EntityItem<TriggerJob> trigger = triggerContainer.getItem(itemId);
			edit(createTriggerJobEditor(), trigger, false);
			break;

		default:
			throw new RuntimeException("not support trigger extension !");
		
		}
	}
	
	
	 public CronJob createCronJob(){
		 try {
			return	CronJob.class.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}
	 }
	
	 public TriggerJob createTrigger(){
		 	try {
				return TriggerJob.class.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				return null;
			}
	 }
	 
	 private EntityItem<CronJob> createCronJobEntityItem() {
	     try{
	    	
	    	 CronJob newInstance = CronJob.class.newInstance();
		      BeanItem<CronJob> beanItem =  new BeanItem<CronJob>(newInstance);
		      beanItem.getItemProperty(backReferencePropertyId).setValue(this.masterEntity);
		      EntityItem<CronJob> item = cronContainer.createEntityItem(newInstance);
		      return item ;
	     }catch (Exception _e){
	    	    Logger.getLogger(super.getClass().getName()).warning("Could not instantiate detail instance " + this.container.getEntityClass().getName());
	     }
	     	return null;
	    }
	 
	 private EntityItem<TriggerJob> createTriggerJobEntityItem() {
	     try{
	    	
	    	 TriggerJob newInstance = TriggerJob.class.newInstance();
		      BeanItem<TriggerJob> beanItem =  new BeanItem<TriggerJob>(newInstance);
		      beanItem.getItemProperty(backReferencePropertyId).setValue(this.masterEntity);
		      EntityItem<TriggerJob> item = triggerContainer.createEntityItem(newInstance);
		      return item ;
	     }catch (Exception _e){
	    	    Logger.getLogger(super.getClass().getName()).warning("Could not instantiate detail instance " + this.container.getEntityClass().getName());
	     }
	     	return null;
	    }
	 
	 private EntityFormWindow<CronJob> createCronJobEditor(){
		 EntityFormWindow<CronJob> m = new EntityFormWindow<CronJob>();
			m.addTab(new MasterForm<CronJob>(CronJob.class) {
				/**
				 * 
				 */
				private static final long serialVersionUID = -6397269997751104529L;

				@Override
				public void save() throws CommitException {
				
					fieldGroup.commit();
					entityItem.commit();
					
					try{
						if (!entityItem.isPersistent()){
							 CronJob job = (CronJob)jobService.createCronJob(entityItem.getEntity(),entityItem.getEntity().getJobDetail() );
							cronContainer.addEntity(job);
						}
						else
							jobService.modifyCronJob(entityItem.getEntity(),entityItem.getEntity().getJobDetail() );
					
						container.refresh();
						
					}catch (JobSchedulerException e){
						throw new CommitException(e);
					}
					
				}
			}, "cronjob");
			
			return m;
	 }
	 
	 private EntityFormWindow<TriggerJob> createTriggerJobEditor(){
		 EntityFormWindow<TriggerJob> m = new EntityFormWindow<TriggerJob>();
			
			m.addTab(new MasterForm<TriggerJob>(TriggerJob.class) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 2810124199208283168L;

				@Override
				public void save() throws CommitException {
				
					fieldGroup.commit();
					entityItem.commit();
					
					try{
						if (!entityItem.isPersistent()){
							TriggerJob trigger = (TriggerJob) jobService.createTrigger(entityItem.getEntity(),entityItem.getEntity().getJobDetail() );
							triggerContainer.addEntity(trigger);
										}
						else
							jobService.modifyTrigger(entityItem.getEntity(),entityItem.getEntity().getJobDetail() );
						
						container.refresh();
					}catch (JobSchedulerException e){
						throw new CommitException(e);
					}
					
				}
			}, "triggerjob");
			
			return m;
	 }
}
