package com.serpics.system.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.serpics.membership.services.BaseService;
import com.serpics.system.services.UserDetailsService;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;
@ContextConfiguration({"classpath:META-INF/base-serpics.xml" , "classpath:META-INF/membership-serpics.xml", "classpath*:META-INF/system-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserserviceTest extends AbstractTransactionalJunit4SerpicTest  {
	
	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	BaseService baseService;
	
	@Test
	public void test(){
		baseService.initIstance();
		
		userDetailsService.loadUserByUsername("superuser");
		
		
	}
}
