package com.serpics.membership.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.core.test.AbstractTransactionalJunit4SerpicTest;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.data.repositories.UserRegrepository;
import com.serpics.membership.services.BaseService;

@ContextConfiguration({  "classpath*:META-INF/applicationContext.xml"})
@Transactional
public class RepositoryTest extends AbstractTransactionalJunit4SerpicTest {
	@Autowired
	BaseService baseService;
	@Autowired
	UserRegrepository userRegrepository;
	
	
	public void beforeTest(){
		baseService.initIstance();
	}
	
	@Test
	public void userRegTest(){
		
		UsersReg u = new UsersReg();
		u.setFirstname("firstname");
		u.setLastname("lastname");
		userRegrepository.create(u);
		
	}
}
