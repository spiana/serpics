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
package com.serpics.membership.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.commerce.CommerceEngine;
import com.serpics.base.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.membership.services.BaseService;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration({ "classpath:META-INF/i18n-serpics.xml" ,"classpath:META-INF/mediasupport-serpics.xml","classpath:META-INF/base-serpics.xml" , "classpath:META-INF/postman-serpics.xml" ,"classpath:META-INF/membership-serpics.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BaseServiceTest  extends AbstractTransactionalJunit4SerpicTest{

	@Autowired
	BaseService b;
	
	@Autowired
	CommerceEngine commerceEngine;
	@Before
	
	public void beforeTest(){
		b.initIstance();
	}
	
	@Test
	public void test() throws SerpicsException {
		CommerceSessionContext context = commerceEngine.connect("default-store");
		assertNotNull(context);
	}

	public void setB(BaseService b) {
		this.b = b;
	}

	public void setCommerceEngine(CommerceEngine commerceEngine) {
		this.commerceEngine = commerceEngine;
	}
}
