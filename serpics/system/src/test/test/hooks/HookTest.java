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
package com.serpics.core.test.hooks;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.membership.services.BaseService;
import com.serpics.stereotype.SerpicsTest;
import com.serpics.test.ExecutionTestListener;

@ContextConfiguration({ "classpath:resources/applicationContext.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ ExecutionTestListener.class, DependencyInjectionTestExecutionListener.class })
@SerpicsTest("test-store")
public class HookTest {

	@Resource
	BaseService baseService;
	@Resource
	CommerceEngine commerceEngine;

	@Resource(name = "test")
	TestHook autowiringtestHook;

	@Test
	@Transactional
	public void test() throws SerpicsException {
		baseService.initIstance();

		baseService.createStore("test-store");
		baseService.createStore("test-1-store");

		TestHook testHook;

		Assert.assertEquals(autowiringtestHook.getClass(), AlternativeTestHookImpl.class);

		commerceEngine.connect("default-store");
		testHook = (TestHook) commerceEngine.getApplicationContext().getBean("test");
		Assert.assertEquals(testHook.getClass(), TestHookImpl.class);

		commerceEngine.connect("test-store");
		testHook = (TestHook) commerceEngine.getApplicationContext().getBean("test");
		Assert.assertEquals(testHook.getClass(), AlternativeTestHookImpl.class);

		commerceEngine.connect("test-1-store");
		testHook = (TestHook) commerceEngine.getApplicationContext().getBean("test");
		Assert.assertEquals(testHook.getClass(), TestHookImpl.class);

	}
}
