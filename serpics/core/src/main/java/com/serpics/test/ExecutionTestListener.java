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
package com.serpics.test;


import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import com.serpics.core.scope.StoreScopeContextHolder;
import com.serpics.stereotype.SerpicsTest;

public class ExecutionTestListener extends AbstractTestExecutionListener {
	@Override
	public void prepareTestInstance(TestContext testContext) throws Exception {
		super.prepareTestInstance(testContext);
		//Thread.currentThread().setContextClassLoader(testContext.getApplicationContext().getClassLoader());
		SerpicsTest s = testContext.getTestClass().getAnnotation(SerpicsTest.class);
		if (s != null)
			StoreScopeContextHolder.setCurrentStoreRealm(s.value());

	}

}
