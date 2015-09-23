package com.serpics.base.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.serpics.base.data.model.Locale;
import com.serpics.base.data.repositories.LocaleRepository;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.core.data.InterceptorEntityMapping;
import com.serpics.core.data.InterceptorMappingInitializer;
import com.serpics.stereotype.SerpicsTest;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;


@ContextConfiguration({ "classpath:META-INF/base-serpics.xml", "classpath:META-INF/base-serpics-test.xml"})
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
		
		Locale locale = new Locale();
		locale = localeRepository.create(locale);
		Assert.assertEquals("test", locale.getName());
		
		List<Locale> l1 = localeRepository.findAll();
		Assert.assertEquals(1, l1.size());
		
	}
}
