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
package com.serpics.postman.service;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.serpics.test.AbstractTransactionalJunit4SerpicTest;
import com.serpics.test.ExecutionTestListener;

@ContextConfiguration({ "classpath:META-INF/applicationContext.xml", "classpath:META-INF/core-serpics.xml","classpath:META-INF/base-serpics.xml",
	
	"classpath:META-INF/postman-serpics.xml" })
@TestExecutionListeners({ ExecutionTestListener.class})
@TransactionConfiguration(defaultRollback = true )
@Ignore
public class EmailServiceTest extends AbstractTransactionalJunit4SerpicTest{
	
	@Test
	public void sendMailTest(){
		Assert.assertTrue(true);
	}
}
