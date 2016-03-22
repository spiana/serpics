package com.serpics.initialsetup.test.task;

import java.io.InputStreamReader;

import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.initialsetup.annotation.SystemSetupTaskConfig;
import com.serpics.initialsetup.task.AbstractSystemTask;

@SystemSetupTaskConfig(order=1,description="Test Task One")
public class TaskTest extends AbstractSystemTask {

	@Override
	public void doExecuteProjectData() {
		getImportCsvService().importCsv(new InputStreamReader(this.getClass().getClassLoader()
                .getResourceAsStream("productProjectTest1.csv")), AbstractProduct.class);

	}

	@Override
	public void doExecuteSampleData() {
		getImportCsvService().importCsv(new InputStreamReader(this.getClass().getClassLoader()
                .getResourceAsStream("productSampleTest1.csv")), AbstractProduct.class);

	}
	
}
