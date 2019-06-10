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

import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.serpics.core.SerpicsException;
import com.serpics.core.scope.StoreScopeContextHolder;
import com.serpics.stereotype.SerpicsTest;

@TestExecutionListeners({ ExecutionTestListener.class, DependencyInjectionTestExecutionListener.class })
public abstract class AbstractTest {

//	@Resource
//	protected BaseService baseService;

	public void init() throws SerpicsException {
		//baseService.initIstance();
		SerpicsTest s = this.getClass().getAnnotation(SerpicsTest.class);
		// reset test scope store after Test platform initialization
		if (s != null)
			StoreScopeContextHolder.setCurrentStoreRealm(s.value());
	}
}
