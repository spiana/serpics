/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
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

@ContextConfiguration({ "classpath:META-INF/i18n-serpics.xml","classpath:META-INF/base-serpics.xml" , "classpath:META-INF/postman-serpics.xml" ,"classpath:META-INF/membership-serpics.xml"})
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
