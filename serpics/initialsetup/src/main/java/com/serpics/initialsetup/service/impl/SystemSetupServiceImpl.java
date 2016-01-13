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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.serpics.initialsetup.ImportType;
import com.serpics.initialsetup.annotation.SystemSetupTaskConfig;
import com.serpics.initialsetup.service.SystemSetupService;
import com.serpics.initialsetup.task.SystemSetupTask;

@Service("systemSetupService")
public class SystemSetupServiceImpl implements SystemSetupService {

	private static final Logger logger = LoggerFactory.getLogger(SystemSetupServiceImpl.class);

	private List<SystemSetupTask> listOfTask;

	private boolean stopOnErrorCheckTask = true;

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

			logger.info("Exist {0} Task in error", taskInError);

			logger.debug("To continue execution Tasks not in error? {0}",
					BooleanUtils.toStringYesNo(stopOnErrorCheckTask));

			if (stopOnErrorCheckTask) {
				return;
			}

		}

		logger.info("Order task by order value in annotation");

		listOfTaskChecked = orderTasks(listOfTaskChecked);

		for (SystemSetupTask task : listOfTaskChecked) {

			logger.info("Run {0} [{1}] task", new Object[] { task.getClass().getSimpleName(),
					task.getClass().getAnnotation(SystemSetupTaskConfig.class).description() });

			task.execute(iType);

		}

		logger.info("Finished System Setup Initialize");
	}

	@SuppressWarnings("unchecked")
	private Collection<SystemSetupTask> checkListOfTasks(Collection<SystemSetupTask> tasksToCheck) {

		return CollectionUtils.select(tasksToCheck, new Predicate() {

			@Override
			public boolean evaluate(Object paramObject) {
				SystemSetupTask taskToCheck = (SystemSetupTask) paramObject;
				SystemSetupTaskConfig annotation = taskToCheck.getClass().getAnnotation(SystemSetupTaskConfig.class);

				if (annotation == null) {
					logger.info("Task class {0} has not annotation TaskConfig.",
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

				SystemSetupTaskConfig config1 = o1.getClass().getAnnotation(SystemSetupTaskConfig.class);
				SystemSetupTaskConfig config2 = o2.getClass().getAnnotation(SystemSetupTaskConfig.class);

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

	@Autowired
	@Required
	public void setListOfTask(List<SystemSetupTask> listOfTask) {
		this.listOfTask = listOfTask;
	}

	public boolean isStopOnErrorCheckTask() {
		return stopOnErrorCheckTask;
	}

	public void setStopOnErrorCheckTask(boolean stopOnErrorCheckTask) {
		this.stopOnErrorCheckTask = stopOnErrorCheckTask;
	}

}
