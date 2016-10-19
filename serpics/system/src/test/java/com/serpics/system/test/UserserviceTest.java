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
package com.serpics.system.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.serpics.membership.services.BaseService;
import com.serpics.system.services.UserDetailsService;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;
@ContextConfiguration({"classpath:META-INF/base-serpics.xml" , "classpath:META-INF/membership-serpics.xml", "classpath*:META-INF/system-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserserviceTest extends AbstractTransactionalJunit4SerpicTest  {
	
	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	BaseService baseService;
	
	@Test
	public void test(){
		baseService.initIstance();
		
		userDetailsService.loadUserByUsername("superuser");
		
		
	}
}
