package com.serpics.membership.test;


import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public void beforeTest(){
		baseService.initIstance();
	
	}
	
	@Test
	@Transactional
	public void testGetuser() throws SerpicsException{
				
			ce.connect("default-store" , "superuser" ,"admin".toCharArray() );
			Page<UserData> l = userFacade.findAllUser(new PageRequest(0, 100));
			
			Assert.assertEquals(1, l.getContent().size());
			Assert.assertEquals("Superuser", l.getContent().get(0).getLastname());
			Assert.assertEquals("Superuser",userFacade.getCurrentuser().getLastname());
	
			
			createUser("test1");
			ce.connect("default-store", "test1", "1".toCharArray());
			Assert.assertEquals("test1",userFacade.getCurrentuser().getLastname());
	
			ce.connect("default-store", "test1", "1".toCharArray());
			ce.connect("default-store" , "superuser" ,"admin".toCharArray() );
			createUser("test2");
			
			ce.connect("default-store", "test2", "1".toCharArray());
			Assert.assertEquals("test2",userFacade.getCurrentuser().getLastname());
			
			Page<UserData> l1 = userFacade.findAllUser(new PageRequest(0, 100));
			Assert.assertEquals(3, l1.getContent().size());
	}
	
	@Test
	@Transactional
	public void registUsertest() throws SerpicsException{
		
	//	ce.connect("default-store");
		UserData d = new UserData();
		d.setLogonid("registerUser");
		userFacade.registerUser(d);
		
	}
	
	private void createUser(String name){
		UsersReg u = new UsersReg();
		u.setLogonid(name);
		u.setPassword("1");
		u.setLastname(name);
		
		userService.registerUser(u, new PrimaryAddress());
		
	}
}
