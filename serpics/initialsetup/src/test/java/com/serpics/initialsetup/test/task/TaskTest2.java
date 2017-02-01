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
package com.serpics.initialsetup.test.task;

import java.io.InputStreamReader;

import com.serpics.catalog.data.model.Product;
import com.serpics.initialsetup.annotation.SystemSetupTaskConfig;
import com.serpics.initialsetup.task.AbstractSystemTask;

@SystemSetupTaskConfig(module = "test1" , order=2,description="Test Task Two")
public class TaskTest2 extends AbstractSystemTask {

	@Override
	public void doExecuteProjectData() {
		getImportCsvService().importCsv(new InputStreamReader(this.getClass().getClassLoader()
                .getResourceAsStream("productProjectTest2.csv")), Product.class);

	}

	@Override
	public void doExecuteSampleData() {
		getImportCsvService().importCsv(new InputStreamReader(this.getClass().getClassLoader()
                .getResourceAsStream("productSampleTest2.csv")), Product.class);

	}

}
