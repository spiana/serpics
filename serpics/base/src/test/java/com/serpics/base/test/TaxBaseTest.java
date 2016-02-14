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
import com.serpics.base.data.model.TaxCategory;
import com.serpics.base.data.repositories.CurrencyRepository;
import com.serpics.base.data.repositories.StoreRepository;
import com.serpics.base.data.repositories.TaxCategoryRepository;
import com.serpics.base.facade.data.TaxCategoryData;
import com.serpics.core.facade.Converter;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration({ "classpath:META-INF/base-serpics.xml"} )
@RunWith(SpringJUnit4ClassRunner.class)
public class TaxBaseTest extends AbstractTransactionalJunit4SerpicTest {

	Logger log = LoggerFactory.getLogger(TaxBaseTest.class);

	@Autowired
	TaxCategoryRepository taxRepository;
	
	@Resource
	Converter<TaxCategory, TaxCategoryData> taxConverter;
	
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
			
			
			
			
			
			TaxCategory t = new TaxCategory();
			t.setName("VAT");
			t.setRate(10.0);
			t.setStore(s);
			taxRepository.save(t);
			
			TaxCategoryData tdata = taxConverter.convert(t);
			
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
		
			TaxCategory t = new TaxCategory();
			t.setName("VAT");
			t.setRate(10.0);
			t.setStore(s);
			
			taxRepository.save(t);
			
			TaxCategory t1 = new TaxCategory();
			t1.setName("VAT");
			t1.setRate(10.0);
			t1.setStore(s);
			taxRepository.save(t1);
			
	}

	@Transactional
	@Test
	public void taxUniquetest1(){

		Currency c = new Currency();
		c.setIsoCode("EUR");
		currencyRepository.save(c);
		Store s = new Store();
		s.setName("test-store");
		s.setCurrency(c);
		storeRepository.save(s);
		
		Store s1 = new Store();
		s1.setName("test-store-1");
		s1.setCurrency(c);
		storeRepository.save(s1);
		
			TaxCategory t = new TaxCategory();
			t.setName("VAT");
			t.setRate(10.0);
			t.setStore(s);
			
			taxRepository.save(t);
			
			TaxCategory t1 = new TaxCategory();
			t1.setName("VAT");
			t1.setRate(10.0);
			t1.setStore(s1);
			taxRepository.save(t1);
			
	}
}