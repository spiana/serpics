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


import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.commerce.CommerceEngine;
import com.serpics.base.data.model.Store;
import com.serpics.base.data.repositories.StoreRepository;
import com.serpics.core.SerpicsException;
import com.serpics.i18n.data.model.Currency;
import com.serpics.i18n.data.repositories.CurrencyRepository;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.data.repositories.UserregRepository;
import com.serpics.membership.services.BaseService;
import com.serpics.stereotype.SerpicsTest;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration({"classpath:META-INF/i18n-serpics.xml" , "classpath:META-INF/base-serpics.xml" , "classpath:META-INF/postman-serpics.xml" ,"classpath:META-INF/membership-serpics.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@SerpicsTest("defualt-store")
public class RepositoryTest extends AbstractTransactionalJunit4SerpicTest {
	@Autowired
	BaseService baseService;
	@Autowired
	UserregRepository userRegrepository;
	
	@Autowired
	StoreRepository storeRepository;
	
	@Autowired
	CurrencyRepository currencyRepository;
	
	@Autowired
	CommerceEngine ce ;
	

	@Before
	public void beforeTest(){
		if (!baseService.isInitialized())
			baseService.initIstance();
	}
	
	@Test
	@Transactional
	public void repositoryTest(){
		UsersReg r =userRegrepository.findBylogonid("superuser");
		Assert.assertNotNull(r);
	}	
	
	@Test
	public void userRegTest() throws SerpicsException{
		ce.connect("default-store");
		
		UsersReg u = new UsersReg();
		u.setFirstname("firstname");
		u.setLastname("lastname");
		u.setLogonid("testuser1");
		userRegrepository.saveAndFlush(u);
		
		List<UsersReg> l = userRegrepository.findAll();
		Assert.assertEquals(1,l.size());
		Assert.assertEquals("testuser1",l.get(0).getLogonid());	
		
		createStore();
		
		ce.connect("test-store");
		UsersReg u1 = new UsersReg();
		u1.setFirstname("firstname");
		u1.setLastname("lastname");
		u1.setLogonid("testuser2");
		userRegrepository.saveAndFlush(u1);
		
		List<UsersReg> l1 = userRegrepository.findAll();
		Assert.assertEquals(1,l1.size());
		Assert.assertEquals("testuser2",l1.get(0).getLogonid());	
	}
	
	private void createStore(){
		Currency example = new Currency();
		example.setIsoCode("EUR");
		Currency c = currencyRepository.findOne(currencyRepository.makeSpecification(example));
		Store _s = new Store();
		_s.setName("test-store");
		_s.setCurrency(c);
		storeRepository.saveAndFlush(_s);
	}
}
