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
package com.serpics.catalog.test;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.serpics.base.commerce.CommerceEngine;
import com.serpics.base.commerce.session.CommerceSessionContext;
import com.serpics.catalog.services.CatalogService;
import com.serpics.core.SerpicsException;
import com.serpics.i18n.data.repositories.LocaleRepository;
import com.serpics.membership.services.BaseService;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration({ "classpath:META-INF/i18n-serpics.xml" , "classpath:META-INF/mediasupport-serpics.xml" , "classpath:META-INF/base-serpics.xml" , 
	"classpath:META-INF/membership-serpics.xml", "classpath:META-INF/catalog-serpics.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Ignore
public abstract class CatalogBaseTest extends AbstractTransactionalJunit4SerpicTest {

    @Autowired
    BaseService baseService;
    @Autowired
    CommerceEngine commerceEngine;

    @Autowired
    CatalogService catalogService;
    
    CommerceSessionContext context ;
    
    @Autowired
    LocaleRepository localeRepository;
    
    @Before
    public void beforeTest() throws SerpicsException {
    	if (!baseService.isInitialized()){
    		baseService.initIstance();
    	}
    	context = commerceEngine.connect("default-store", "superuser", "admin".toCharArray());
    	context.setLocale(localeRepository.findByLanguage("en"));
		catalogService.initialize();
    }

}
