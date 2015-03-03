package com.serpics.base.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.base.persistence.Locale;
import com.serpics.base.repositories.LocaleRepository;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.core.data.CreateInterceptor;
import com.serpics.core.data.InterceptorEntityMapping;
import com.serpics.core.data.InterceptorMappingInitializer;
import com.serpics.core.session.SessionContext;



public class InterceptorTest extends BaseTest {

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
