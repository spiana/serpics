package com.serpics.commerce.test;

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
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.data.model.Tax;
import com.serpics.commerce.data.model.Taxlookup;
import com.serpics.commerce.data.repositories.TaxRepository;
import com.serpics.commerce.data.repositories.TaxlookupRepository;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.membership.services.BaseService;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;



@ContextConfiguration({ "classpath:META-INF/base-serpics.xml" , 
	"classpath:META-INF/membership-serpics.xml", "classpath:META-INF/catalog-serpics.xml",
	"classpath:META-INF/warehouse-serpics.xml",
	"classpath:META-INF/commerce-serpics.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TaxesTest extends AbstractTransactionalJunit4SerpicTest {
	
	@Autowired
	BaseService baseService;
	
	@Autowired
	TaxRepository taxesRepository;
	
	@Autowired
	TaxlookupRepository taxlookupRepository;
	
	@Autowired
	CommerceEngine commerceEngine;
	
	@Before
	public void beforeTest(){
		if(!baseService.isInitialized())
			baseService.initIstance();
	}
	
	@Test
	@Transactional
	public void taxesTest() throws SerpicsException{
		
		CommerceSessionContext c= commerceEngine.connect("default-store");
		
		Tax t1 = new Tax("iva",22.0);
		Tax t2 = new Tax("iva10",10.01);
		Tax t3 = new Tax("iva4",4.04);
		
		t1= taxesRepository.save(t1);
		t2= taxesRepository.save(t2);
		t3= taxesRepository.save(t3);
		
		Taxlookup tl1= new Taxlookup();
		tl1.setActive(true);
		tl1.setStore((Store)c.getStoreRealm());
		tl1.setTax(t1);
		taxlookupRepository.save(tl1);
		
		Taxlookup tl2= new Taxlookup();
		tl2.setActive(false);
		tl2.setStore((Store)c.getStoreRealm());
		tl2.setTax(t2);
		taxlookupRepository.save(tl2);
		
		List<Tax> l = taxesRepository.findActiveTax((Store)c.getStoreRealm());
		Assert.assertEquals(1, l.size());
		
		List<Taxlookup> l1 = taxlookupRepository.findAll();
		Assert.assertEquals(2, l1.size());
		
	}
}
