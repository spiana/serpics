package com.serpics.membership.test;


import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.core.test.AbstractTransactionalJunit4SerpicTest;
import com.serpics.membership.data.model.PrimaryAddress;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.facade.UserFacade;
import com.serpics.membership.facade.data.UserData;
import com.serpics.membership.services.BaseService;
import com.serpics.membership.services.UserService;
import com.serpics.stereotype.SerpicsTest;


@ContextConfiguration({ "classpath*:META-INF/applicationContext.xml" })
@SerpicsTest("default-store")
public class UserFacadeTest extends AbstractTransactionalJunit4SerpicTest{
	
	@Resource
	BaseService baseService;
	
	@Resource
	UserFacade userFacade;
	
	@Resource
	UserService userService ;
	
	@Resource
	CommerceEngine ce;
	
	@Before
	public void beforeTest(){
		baseService.initIstance();
	}
	
	@Test
	public void testGetuser() throws SerpicsException{
			ce.connect("default-store" );
		
			Page<UserData> l = userFacade.findAllUser(new PageRequest(0, 100));
			Assert.assertEquals(1, l.getContent().size());
			Assert.assertEquals("Superuser", l.getContent().get(0).getLastname());
			Assert.assertEquals("Superuser",userFacade.getCurrentuser().getFirstname());
			createUser();
			ce.connect("default-store", "pippo", "1".toCharArray());
			Assert.assertEquals("pippo",userFacade.getCurrentuser().getFirstname());
			Page<UserData> l1 = userFacade.findAllUser(new PageRequest(0, 100));
			Assert.assertEquals(2, l1.getContent().size());
			
			
			
	}
	
	private void createUser(){
		UsersReg u = new UsersReg();
		u.setLogonid("pippo");
		u.setPassword("1");
		u.setFirstname("pippo");
		
		userService.registerUser(u, new PrimaryAddress());
		
	}
}
