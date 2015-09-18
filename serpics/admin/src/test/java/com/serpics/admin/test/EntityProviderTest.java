package com.serpics.admin.test;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.membership.data.model.PrimaryAddress;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.services.BaseService;
import com.serpics.membership.services.UserService;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;
import com.serpics.vaadin.jpacontainer.ServiceContainerFactory;
import com.vaadin.addon.jpacontainer.JPAContainer;


@ContextConfiguration("classpath*:/META-INF/applicationContext-test.xml")
@Transactional
public class EntityProviderTest extends AbstractTransactionalJunit4SerpicTest {

	@Resource
	BaseService baseService;
	
	@Resource
	UserService userService;
	
	@Resource
	CommerceEngine engine;
	
	@Test
	@Transactional
	public void EntityProviderTest () throws SerpicsException{
		if (!baseService.isInitialized())
			baseService.initIstance();
		
		engine.connect("default-store");
		
		UsersReg u = new UsersReg();
		u.setLogonid("test12");
		JPAContainer c = ServiceContainerFactory.make(UsersReg.class);
		int i = c.getItemIds().size();
		Assert.assertEquals(0, i);
		userService.registerUser(u, new PrimaryAddress());
		
		UsersReg u1 = new UsersReg();
		u1.setLogonid("test13");
		userService.registerUser(u1, new PrimaryAddress());
		
		c.refresh();
		i = c.getItemIds().size();
		Assert.assertEquals(2, i);
	}
	
	
	
	
	
}
