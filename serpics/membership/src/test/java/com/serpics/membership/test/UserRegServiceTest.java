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

import com.serpics.base.data.model.Store;
import com.serpics.base.data.repositories.StoreRepository;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.data.repositories.UserRepository;
import com.serpics.membership.data.repositories.UserregRepository;
import com.serpics.membership.services.BaseService;
import com.serpics.membership.services.UserService;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration({"classpath:META-INF/i18n-serpics.xml" , "classpath:META-INF/base-serpics.xml" ,"classpath:META-INF/postman-serpics.xml" , "classpath:META-INF/membership-serpics.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserRegServiceTest extends AbstractTransactionalJunit4SerpicTest{

    @Autowired
    BaseService baseService;

    @Autowired
    UserregRepository userRegRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StoreRepository storeRepository;

    @Before
    public void init() {
    	if (!baseService.isInitialized())
			baseService.initIstance();
    }

    @Test
	@Transactional
    public void first() {
        final List<UsersReg> l = userRegRepository.findAll();
        Assert.assertEquals(0, l.size());
        UsersReg u = userRegRepository.findBylogonid("superuser");
        Assert.assertNotNull(u);
    }

    @Test
    @Transactional
    public void second() {
        final List<Store> stores = storeRepository.findAll();
        final List<User> l = userRepository.findAllByStore(stores.get(0));
        Assert.assertEquals(0, l.size());

    }

}
