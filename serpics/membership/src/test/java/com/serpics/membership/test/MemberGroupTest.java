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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.commerce.CommerceEngine;
import com.serpics.base.commerce.session.CommerceSessionContext;
import com.serpics.base.data.model.Store;
import com.serpics.core.SerpicsException;
import com.serpics.membership.data.model.Membergroup;
import com.serpics.membership.data.repositories.MemberGroupRepository;
import com.serpics.membership.services.BaseService;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration({ "classpath:META-INF/i18n-serpics.xml" , "classpath:META-INF/base-serpics.xml" ,"classpath:META-INF/postman-serpics.xml" , "classpath:META-INF/membership-serpics.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class MemberGroupTest extends AbstractTransactionalJunit4SerpicTest{
	
	@Autowired
	BaseService baseService;
	
	@Autowired
	CommerceEngine commerceEngine;
	
	@Autowired
	MemberGroupRepository memberGroupRepository;

	@Test
	public void test() throws SerpicsException{
		if (!baseService.isInitialized())
			baseService.initIstance();
		CommerceSessionContext context = commerceEngine.connect("default-store");
		
		Membergroup m = new Membergroup();
		m.setName("test");
		m.setStore((Store) context.getStoreRealm());
		memberGroupRepository.saveAndFlush(m);
		
		List<Membergroup> l= memberGroupRepository.findAll();
		Assert.assertEquals(1, l.size());
		
	}
}
