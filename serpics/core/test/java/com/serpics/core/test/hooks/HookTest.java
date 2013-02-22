package com.serpics.core.test.hooks;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.serpics.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.core.session.CommerceSessionContext;
import com.serpics.stereotype.SerpicsTest;
import com.serpics.test.AbstractTest;

@SerpicsTest("test-store")
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
		TestHook testHook;

		testHook = (TestHook) commerceEngine.getApplicationContext().getBean("test");
		Assert.assertEquals(testHook.getClass(), AlternativeTestHookImpl.class);

		baseService.createStore("test-store");
		baseService.createStore("test-1-store");
		/*
		 * CommerceSessionContext context =
		 * commerceEngine.connect("default-store");
		 * 
		 * testHook = (TestHook)
		 * commerceEngine.getApplicationContext().getBean("test");
		 * Assert.assertEquals(testHook.getClass(), TestHookImpl.class);
		 */
		CommerceSessionContext context = context = commerceEngine.connect("test-store");
		testHook = (TestHook) commerceEngine.getApplicationContext().getBean("test");
		Assert.assertEquals(testHook.getClass(), AlternativeTestHookImpl.class);

		context = commerceEngine.connect("test-1-store");
		testHook = (TestHook) commerceEngine.getApplicationContext().getBean("test");
		Assert.assertEquals(testHook.getClass(), TestHookImpl.class);

	}
}
