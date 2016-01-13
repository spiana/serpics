package com.serpics.initialsetup.service.test.task;

import com.serpics.initialsetup.annotation.SystemSetupTaskConfig;
import com.serpics.initialsetup.task.AbstractSystemTask;

@SystemSetupTaskConfig(order=2)
public class TaskTest2 extends AbstractSystemTask {

	@Override
	public void doExecuteProjectData() {
		System.out.println("doExecuteProjectData");

	}

	@Override
	public void doExecuteSampleData() {
		System.out.println("doExecuteSampleData");

	}

}
