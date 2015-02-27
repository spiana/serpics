package com.serpics.base.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.base.persistence.Locale;
import com.serpics.base.repositories.LocaleRepository;
import com.serpics.core.data.BeforeCreateInterceptor;
import com.serpics.core.data.InterceptorEntityMapping;
import com.serpics.core.data.InterceptorMappingInitializer;



public class InterceptorTest extends BaseTest {

	@Autowired
	InterceptorMappingInitializer m ; 
	
	@Autowired
	LocaleRepository localeRepository;
	
	@Test
	public void first(){
		InterceptorEntityMapping<BeforeCreateInterceptor> l = m.getBeforeCreateinterceptor();
		Assert.assertEquals(1, l.size());
		Assert.assertEquals(2, l.get("Locale").size());
		
		Locale locale = new Locale();
		locale = localeRepository.create(locale);
		Assert.assertEquals("test", locale.getName());
	}
}
