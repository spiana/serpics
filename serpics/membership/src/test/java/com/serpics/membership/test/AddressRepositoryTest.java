package com.serpics.membership.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.membership.data.model.PrimaryAddress;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.repositories.PrimaryAddressRepository;
import com.serpics.membership.data.repositories.UserRepository;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration({ "classpath:META-INF/base-serpics.xml" , "classpath:META-INF/membership-serpics.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AddressRepositoryTest  extends AbstractTransactionalJunit4SerpicTest{

	@Resource
	 UserRepository userRepository ;
	 
	 @Resource
	 PrimaryAddressRepository primaryAddressRepository;
	
	@Test
	public void addressTest(){
		User u = new User();
		u = userRepository.saveAndFlush(u);
		
		PrimaryAddress p = new PrimaryAddress();
		p.setMember(u);
		primaryAddressRepository.saveAndFlush(p);
		
		
	}
}
