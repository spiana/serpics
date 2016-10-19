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

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.core.datatype.UserRegisterType;
import com.serpics.core.session.CommerceSessionContext;
import com.serpics.membership.persistence.PermanentAddress;
import com.serpics.membership.persistence.User;
import com.serpics.membership.persistence.UsersReg;
import com.serpics.membership.services.BaseService;
import com.serpics.membership.services.MembershipService;
import com.serpics.stereotype.SerpicsTest;

@ContextConfiguration({ "classpath:resources/applicationContext.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
@SerpicsTest("default-store")
public class CommerceScopeTest {

	@Resource
	BaseService baseService;

	@Autowired
	CommerceEngine ce;

	@Resource(name = "sessionContext")
	CommerceSessionContext commerceSessionContext;

	private CommerceSessionContext getContext() {
		return commerceSessionContext;
	}

	@Test
	@Transactional
	public void test() throws SerpicsException {
		baseService.initIstance();
		CommerceSessionContext context = ce.connect("default-store", "superuser", "admin".toCharArray());
		registerTestUser(context);
		Assert.assertEquals(context, ce.getApplicationContext().getBean("sessionContext"));

		context = ce.connect("default-store", "superuser", "admin".toCharArray());
		TestScopebean b = (TestScopebean) ce.getApplicationContext().getBean("testScope");
		Assert.assertEquals(context, getContext());

		CommerceSessionContext context1 = ce.connect("default-store", "testscope", "password".toCharArray());
		TestScopebean b1 = (TestScopebean) ce.getApplicationContext().getBean("testScope");
		Assert.assertEquals(context1, getContext());

		TestScopebean b2 = (TestScopebean) ce.getApplicationContext().getBean("testScope");
		Assert.assertEquals(b1, b2);

		context = ce.bind(context.getSessionId());
		TestScopebean b3 = (TestScopebean) ce.getApplicationContext().getBean("testScope");
		Assert.assertEquals(b, b3);
	}

	private void registerTestUser(CommerceSessionContext context) {
		MembershipService m = ce.getApplicationContext().getBean(MembershipService.class);

		User u = new User();
		u.setLastname("test");
		UsersReg ur = new UsersReg();
		ur.setLogonid("testscope");
		ur.setPassword("password");
		ur.setStatus(UserRegisterType.ACTIVE);
		PermanentAddress a = new PermanentAddress();
		a.setNickname("test-address");

		u = m.registerUser(u, ur, a);

	}

}
