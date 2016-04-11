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
import com.serpics.commerce.data.model.Paymethod;
import com.serpics.commerce.data.model.Paymethodlookup;
import com.serpics.commerce.data.repositories.PaymethodRepository;
import com.serpics.commerce.data.repositories.PaymethodlookupRepository;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.membership.services.BaseService;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;



@ContextConfiguration({ "classpath:META-INF/base-serpics.xml" , 
	"classpath:META-INF/postman-serpics.xml" ,
	"classpath:META-INF/membership-serpics.xml", "classpath:META-INF/catalog-serpics.xml",
	"classpath:META-INF/warehouse-serpics.xml",
	"classpath:META-INF/commerce-serpics.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class PaymentMethodTest extends AbstractTransactionalJunit4SerpicTest {
	
	@Autowired
	BaseService baseService;
	
	@Autowired
	PaymethodRepository paymethodRepository;
	@Autowired
	PaymethodlookupRepository paymethodlookupRepository;
	@Autowired
	CommerceEngine commerceEngine;
	
	@Before
	public void beforeTest(){
		if(!baseService.isInitialized())
			baseService.initIstance();
	}
	
	@Test
	@Transactional
	public void paymethodTest() throws SerpicsException{
		CommerceSessionContext c= commerceEngine.connect("default-store");
		
		Paymethod p1 = new Paymethod("Credit_card");
		Paymethod p2 = new Paymethod("paypal");
		Paymethod p3 = new Paymethod("cash");
		
		p1 =paymethodRepository.save(p1);
		p2= paymethodRepository.save(p2);
		p3= paymethodRepository.save(p3);
		
		Paymethodlookup pl1= new Paymethodlookup();
		pl1.setActive(true);
		pl1.setStore((Store)c.getStoreRealm());
		pl1.setPaymethod(p1);
		paymethodlookupRepository.save(pl1);
		
		Paymethodlookup pl2= new Paymethodlookup();
		pl2.setActive(false);
		pl2.setStore((Store)c.getStoreRealm());
		pl2.setPaymethod(p2);
		paymethodlookupRepository.save(pl2);
		
		List<Paymethod> l = paymethodRepository.findActivePaymentmethod((Store)c.getStoreRealm());
		Assert.assertEquals(1, l.size());
		
		List<Paymethodlookup> l1 = paymethodlookupRepository.findAll();
		Assert.assertEquals(2, l1.size());
		
		
	}
}
