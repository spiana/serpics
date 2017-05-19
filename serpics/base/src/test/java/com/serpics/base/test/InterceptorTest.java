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
package com.serpics.base.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.core.data.InterceptorEntityMapping;
import com.serpics.core.data.InterceptorMappingInitializer;
import com.serpics.i18n.data.model.Locale;
import com.serpics.i18n.data.repositories.LocaleRepository;
import com.serpics.stereotype.SerpicsTest;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;


@ContextConfiguration({ 
	"classpath:META-INF/i18n-serpics.xml",
	"classpath:META-INF/base-serpics-test.xml"})
@SerpicsTest("default-store")
@RunWith(SpringJUnit4ClassRunner.class)
public class InterceptorTest extends AbstractTransactionalJunit4SerpicTest{

	@Autowired
	CommerceEngine commerceEngine;
	
	@Autowired
	InterceptorMappingInitializer m ; 
	
	@Autowired
	LocaleRepository localeRepository;
	
	@Test
	public void first() throws SerpicsException{
	
		InterceptorEntityMapping l = m.getCreateInterceptor();
		Assert.assertEquals(1, l.size());
		Assert.assertEquals(2, l.get(Locale.class.getName()).size());
		
		List<Locale> linit = localeRepository.findAll();
		
		Locale locale = new Locale();
		locale = localeRepository.saveAndFlush(locale);
		Assert.assertEquals("test", locale.getName());
		
		List<Locale> l1 = localeRepository.findAll();
		Assert.assertEquals(linit.size()+1, l1.size());
		
	}
}
