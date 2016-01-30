package com.serpics.base.test;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.data.model.Currency;
import com.serpics.base.data.model.Store;
import com.serpics.base.data.model.Tax;
import com.serpics.base.data.repositories.CurrencyRepository;
import com.serpics.base.data.repositories.StoreRepository;
import com.serpics.base.data.repositories.TaxRepository;
import com.serpics.base.facade.data.TaxData;
import com.serpics.core.facade.Converter;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration({ "classpath:META-INF/base-serpics.xml"} )
@RunWith(SpringJUnit4ClassRunner.class)
public class TaxBaseTest extends AbstractTransactionalJunit4SerpicTest {

	Logger log = LoggerFactory.getLogger(TaxBaseTest.class);

	@Autowired
	TaxRepository taxRepository;
	
	@Resource
	Converter<Tax, TaxData> taxConverter;
	
	@Resource
	StoreRepository storeRepository;
	
	@Resource
	CurrencyRepository currencyRepository;
	
	
	
	@Test
	@Transactional
	public void taxConverterTest(){
			
		
			Currency c = new Currency();
			c.setIsoCode("EUR");
			currencyRepository.save(c);
			Store s = new Store();
			s.setName("test-store");
			s.setCurrency(c);
			storeRepository.save(s);
			
			
			
			
			
			Tax t = new Tax();
			t.setName("VAT");
			t.setRate(10.0);
			t.setStore(s);
			taxRepository.save(t);
			
			TaxData tdata = taxConverter.convert(t);
			
			Assert.assertEquals("VAT" , tdata.getName());
			
	}
	
	@Transactional
	@Test(expected= DataIntegrityViolationException.class)
	public void taxUniquetest(){

		Currency c = new Currency();
		c.setIsoCode("EUR");
		currencyRepository.save(c);
		Store s = new Store();
		s.setName("test-store");
		s.setCurrency(c);
		storeRepository.save(s);
		
			Tax t = new Tax();
			t.setName("VAT");
			t.setRate(10.0);
			t.setStore(s);
			
			taxRepository.save(t);
			
			Tax t1 = new Tax();
			t1.setName("VAT");
			t1.setRate(10.0);
			t1.setStore(s);
			taxRepository.save(t1);
			
	}
}
