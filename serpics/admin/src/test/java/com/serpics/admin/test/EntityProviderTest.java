package com.serpics.admin.test;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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



@ContextConfiguration({ "classpath:META-INF/base-serpics.xml" ,
						"classpath:META-INF/membership-serpics.xml"})
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
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
		
		UsersReg u2 = new UsersReg();
		u2.setLogonid("user567");
		userService.registerUser(u2, new PrimaryAddress());
		
		// test if contains
		c.addContainerFilter("logonid", "er56", false, false);
		c.refresh();
		i = c.getItemIds().size();
		Assert.assertEquals(1, i);
		
		// test if start with
		c.addContainerFilter("logonid", "use", false, true);
		c.refresh();
		i = c.getItemIds().size();
		Assert.assertEquals(1, i);
	}
	
	
	
	
}
