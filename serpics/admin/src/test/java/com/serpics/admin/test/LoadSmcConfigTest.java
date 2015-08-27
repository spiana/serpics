package com.serpics.admin.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.membership.data.model.PrimaryAddress;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.services.BaseService;
import com.serpics.membership.services.UserService;
import com.serpics.stereotype.SerpicsTest;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;
import com.serpics.vaadin.jpacontainer.ServiceContainerFactory;
import com.serpics.vaadin.ui.PropertiesUtils;
import com.vaadin.addon.jpacontainer.JPAContainer;


@ContextConfiguration( {"classpath*:META-INF/applicationContext-test.xml" })
@SerpicsTest("default-store")
@Ignore
public class LoadSmcConfigTest  extends AbstractTransactionalJunit4SerpicTest{
	
	@Autowired
	PropertiesUtils props;
	
	@Autowired
	BaseService baseService;
	
	@Autowired 
	UserService userService;
	
	@Autowired
	CommerceEngine ce;
	
	@Test
	@Transactional
	public void test(){
		Assert.assertNotNull(props);

		String[] table = props.getTableProperty("test.entity");
		Assert.assertEquals(2, table.length);
	}

	@Test
	@Transactional
	@Ignore
	public void test1() throws SerpicsException{
		if(!baseService.isInitialized())
			baseService.initIstance();
		
		ce.connect("default-store");
		
		JPAContainer<UsersReg> s = ServiceContainerFactory.make(UsersReg.class);
		
		
		for (int x=1 ; x< 200 ;x++){
			UsersReg u = new UsersReg();
			u.setLogonid("test"+x);
			userService.registerUser(u, new PrimaryAddress());
		}
		
	
		System.out.println("elemnenti presenti :" + s.getItemIds().size());
	}
}
