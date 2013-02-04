package com.serpics.core.test;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.services.BaseService;
import com.serpics.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.core.datatype.UserRegisterType;
import com.serpics.core.session.CommerceSessionContext;
import com.serpics.membership.persistence.PermanentAddress;
import com.serpics.membership.persistence.User;
import com.serpics.membership.persistence.UsersReg;
import com.serpics.membership.services.MembershipService;

@ContextConfiguration({ "classpath:resources/applicationContext.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
public class CommerceScopeTest {

	@Resource
	BaseService baseService;

	@Autowired
	MembershipService m;

	@Autowired
	CommerceEngine ce;

	@Resource(name = "sessionContext")
	CommerceSessionContext commerceSessionContext;

	@Before
	public void init() throws SerpicsException {
		baseService.initIstance();
		CommerceSessionContext context = ce.connect("default-store", "superuser", "admin".toCharArray());
		registerTestUser(context);
		Assert.assertEquals(context, ce.getApplicationContext().getBean("sessionContext"));

	}

	private CommerceSessionContext getContext() {
		return commerceSessionContext;
	}

	@Test
	public void test() throws SerpicsException {
		CommerceSessionContext context = ce.connect("default-store", "superuser", "admin".toCharArray());
		TestScopebean b = (TestScopebean) ce.getApplicationContext().getBean("test");
		Assert.assertEquals(context, getContext());

		CommerceSessionContext context1 = ce.connect("default-store", "test", "password".toCharArray());
		TestScopebean b1 = (TestScopebean) ce.getApplicationContext().getBean("test");
		Assert.assertEquals(context1, getContext());

		TestScopebean b2 = (TestScopebean) ce.getApplicationContext().getBean("test");
		Assert.assertEquals(b1, b2);

		context = ce.bind(context.getSessionId());
		TestScopebean b3 = (TestScopebean) ce.getApplicationContext().getBean("test");
		Assert.assertEquals(b, b3);
	}

	private void registerTestUser(CommerceSessionContext context) {

		User u = new User();
		u.setLastname("test");
		UsersReg ur = new UsersReg();
		ur.setLogonid("test");
		ur.setPassword("password");
		ur.setStatus(UserRegisterType.ACTIVE);
		PermanentAddress a = new PermanentAddress();
		a.setNickname("test-address");

		u = m.registerUser(u, ur, a);

	}

}
