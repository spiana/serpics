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
package com.serpics.initialsetup.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serpics.initialsetup.ImportType;
import com.serpics.initialsetup.annotation.SystemSetupTaskConfig;
import com.serpics.initialsetup.service.SystemSetupService;
import com.serpics.initialsetup.task.SystemSetupTask;
/**
 * Implementation of service that manage list of task.
 * Retrieve tasks on context by type. All classes that implement {@link com.serpics.initialsetup.annotation.SystemSetupTaskConfig SystemSetupTaskConfig} class will be checked and ordered.
 * 
 * Tasks will be sorted by store, catalog and order value.  
 * 
 * @author alessandro.marasco@tinvention.net
 *
 */
@Service("systemSetupService")
public class SystemSetupServiceImpl implements SystemSetupService{

	private static final Logger logger = LoggerFactory.getLogger(SystemSetupServiceImpl.class);

	private List<SystemSetupTask> listOfTask;

	private boolean stopOnErrorCheckTask = true;
	
	/**
	 * Main method that manage list of Task
	 */
	@Override
	public void doSystemSetupTasks(ImportType iType) {
		logger.info("Start to execute Task...");

		List<SystemSetupTask> listOfTaskToExecute = getListOfTask();

		if (CollectionUtils.isEmpty(listOfTaskToExecute)) {
			logger.info("List of Task to execute is empty.");
			return;
		}

		logger.info("Check list of task if contains error");
		Collection<SystemSetupTask> listOfTaskChecked = checkListOfTasks(listOfTaskToExecute);
		int taskInError = getListOfTask().size() - listOfTaskChecked.size();
		if (taskInError > 0) {

			logger.info("Exist {} Task in error", taskInError);

			logger.debug("To continue execution Tasks not in error? {}",
					BooleanUtils.toStringYesNo(stopOnErrorCheckTask));

			if (stopOnErrorCheckTask) {
				return;
			}

		}

		logger.info("Order task by order value in annotation");

		listOfTaskChecked = orderTasks(listOfTaskChecked);

		for (SystemSetupTask task : listOfTaskChecked) {
			SystemSetupTask taskTarget = getTargetObject(task,SystemSetupTask.class);
			logger.info("Run {} [{}] task", taskTarget.getClass().getSimpleName(),
					taskTarget.getClass().getAnnotation(SystemSetupTaskConfig.class).description());

			task.execute(iType);

		}

		logger.info("Finished System Setup Initialize");
	}

	@SuppressWarnings("unchecked")
	private Collection<SystemSetupTask> checkListOfTasks(Collection<SystemSetupTask> tasksToCheck) {

		return CollectionUtils.select(tasksToCheck, new Predicate() {

			@Override
			public boolean evaluate(Object paramObject) {
				SystemSetupTask taskToCheck = getTargetObject(paramObject,SystemSetupTask.class);
//				SystemSetupTask taskToCheck = (SystemSetupTask) paramObject;
				SystemSetupTaskConfig annotation = taskToCheck.getClass().getAnnotation(SystemSetupTaskConfig.class);

				if (annotation == null) {
					logger.info("Task class {} has not annotation TaskConfig.",
							taskToCheck.getClass().getSimpleName());
					return false;
				}

				if (StringUtils.isBlank(annotation.store())) {
					logger.info("Task class {0} store not set.", taskToCheck.getClass().getSimpleName());
					return false;
				}

				// Catalog blank will be set default Catalog
//				if (StringUtils.isBlank(annotation.catalog())) {
//					logger.info("Task class {0} catalog not set.", taskToCheck.getClass().getSimpleName());
//					return false;
//				}

				return true;
			}
		});

	}

	private List<SystemSetupTask> orderTasks(Collection<SystemSetupTask> listOfTaskToOrder) {
		logger.debug("Start to order tasks..");
		List<SystemSetupTask> sorted = new ArrayList<SystemSetupTask>(listOfTaskToOrder);
		Collections.sort(sorted, new Comparator<SystemSetupTask>() {

			@Override
			public int compare(SystemSetupTask o1, SystemSetupTask o2) {
				SystemSetupTask test  = getTargetObject(o1,SystemSetupTask.class);
				SystemSetupTask test2  = getTargetObject(o2,SystemSetupTask.class);
				SystemSetupTaskConfig config1 = test.getClass().getAnnotation(SystemSetupTaskConfig.class);
				SystemSetupTaskConfig config2 = test2.getClass().getAnnotation(SystemSetupTaskConfig.class);

				return new CompareToBuilder().append(config1.store(), config2.store())
						.append(config1.catalog(), config2.catalog()).append(config1.order(), config2.order())
						.toComparison();
			}
		});

		return sorted;
	}

	public List<SystemSetupTask> getListOfTask() {
		return listOfTask;
	}

	@Autowired(required=false)
	public void setListOfTask(final List<SystemSetupTask> listOfTask) {
		this.listOfTask = listOfTask;
	}

	public boolean isStopOnErrorCheckTask() {
		return stopOnErrorCheckTask;
	}

	public void setStopOnErrorCheckTask(boolean stopOnErrorCheckTask) {
		this.stopOnErrorCheckTask = stopOnErrorCheckTask;
	}

	@SuppressWarnings({"unchecked"})
	protected <T> T getTargetObject(Object proxy, Class<T> targetClass){
	  if (AopUtils.isJdkDynamicProxy(proxy)) {
		  try{
			  return (T) ((Advised)proxy).getTargetSource().getTarget();
		  }catch(Exception ex){
			  logger.error("Cannot resolve the target object in proxy",ex);
		  }
	  } 
	    return (T) proxy; // expected to be cglib proxy then, which is simply a specialized class
	}
	
}
