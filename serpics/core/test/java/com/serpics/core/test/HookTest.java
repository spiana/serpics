package com.serpics.core.test;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.serpics.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.core.security.UserPrincipal;
import com.serpics.core.session.CommerceSessionContext;
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

	@Resource
	MembershipHook membershipHook;
	
	@Test
	public void test() throws SerpicsException {
		baseService.createStore("store1");
//		MembershipHook membershipHook;

		CommerceSessionContext context = commerceEngine.connect("default-store");

//		membershipHook = (MembershipHook) commerceEngine.getApplicationContext().getBean("MembershipHook");

		UserPrincipal u = membershipHook.login((Store) context.getStoreRealm(), "superuser", "admin".toCharArray());
		Assert.assertNotNull(u);
		Assert.assertEquals("superuser", u.getName());

		// context = commerceEngine.connect("store1");
//		membershipHook = (MembershipHook) commerceEngine.getApplicationContext().getBean("MembershipHook");

		u = membershipHook.login((Store) context.getStoreRealm(), "superuser", "admin".toCharArray());
		Assert.assertNotNull(u);
		Assert.assertEquals("superuser", u.getName());

	}
}
