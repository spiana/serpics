package com.serpics.core.test.hooks;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.serpics.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.core.security.UserPrincipal;
import com.serpics.core.session.CommerceSessionContext;
import com.serpics.core.test.AbstractTest;
import com.serpics.membership.hooks.MembershipHook;
import com.serpics.membership.persistence.Store;

public class HookTest extends AbstractTest {

	@Resource
	CommerceEngine commerceEngine;

	@Override
	@Before
	public void init() throws SerpicsException {
		super.init();
	}


	@Test
	public void test() throws SerpicsException {
		baseService.createStore("test-store");
		TestHook testHook;

		CommerceSessionContext context = commerceEngine.connect("default-store");

		testHook = (TestHook) commerceEngine.getApplicationContext().getBean("test");		
		Assert.assertEquals(testHook.getClass(), TestHookImpl.class);


		context = commerceEngine.connect("test-store");
		testHook = (TestHook) commerceEngine.getApplicationContext().getBean("test");		
		Assert.assertEquals(testHook.getClass(), AlternativeTestHookImpl.class);



	}
}
