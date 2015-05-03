package com.serpics.system.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.membership.services.BaseService;
import com.serpics.system.services.UserDetailsService;
@ContextConfiguration({ "classpath*:META-INF/applicationContext-test.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserserviceTest  {
	
	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	BaseService baseService;
	
	@Test
	public void test(){
		baseService.initIstance();
		
		UserDetails u = userDetailsService.loadUserByUsername("superuser");
		
		
	}
}
