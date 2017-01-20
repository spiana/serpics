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
package com.serpics.core.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.serpics.core.data.Interceptor;
import com.serpics.core.data.InterceptorMapping;
import com.serpics.core.data.InterceptorMappingInitializer;
import com.serpics.test.ExecutionTestListener;

@ContextConfiguration({  "classpath:META-INF/core-serpics.xml","classpath:META-INF/core-serpics-test.xml"} )
@TestExecutionListeners({ ExecutionTestListener.class, DependencyInjectionTestExecutionListener.class })
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
public class InterceptorTest {
	@Resource(name="interceptorMapping")
	InterceptorMappingInitializer interceptorMapping;
	
	@Test
	public void interceptorTest(){
		
		Assert.assertEquals(1,interceptorMapping.getCreateInterceptor().size());
		String key  = interceptorMapping.getCreateInterceptor().keySet().iterator().next();
		List<InterceptorMapping> mapping = interceptorMapping.getCreateInterceptor().get(key);
		Assert.assertEquals(1,mapping.size());
		Assert.assertEquals("com.serpics.core.test.repositories.TestInterceptor", mapping.get(0).getTargetEntity());
		
	}
}
