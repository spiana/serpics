package com.serpics.commerce.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.data.model.Paymethod;
import com.serpics.commerce.data.model.Paymethodlookup;
import com.serpics.commerce.data.repositories.PaymethodRepository;
import com.serpics.commerce.data.repositories.PaymethodlookupRepository;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.membership.data.model.Store;
import com.serpics.membership.services.BaseService;


@ContextConfiguration({ "classpath*:META-INF/applicationContext-test.xml" })
//@Transactional
public class PaymentMethodTest extends AbstractTransactionalJUnit4SpringContextTests {
	
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
		baseService.initIstance();
	}
	
	@Test
	public void paymethodTest() throws SerpicsException{
		CommerceSessionContext c= commerceEngine.connect("default-store");
		
		Paymethod p1 = new Paymethod("Credit_card");
		Paymethod p2 = new Paymethod("paypal");
		Paymethod p3 = new Paymethod("cash");
		
		p1 =paymethodRepository.create(p1);
		p2= paymethodRepository.create(p2);
		p3= paymethodRepository.create(p3);
		
		Paymethodlookup pl1= new Paymethodlookup();
		pl1.setActive(true);
		pl1.setStore((Store)c.getStoreRealm());
		pl1.setPaymethod(p1);
		paymethodlookupRepository.create(pl1);
		
		Paymethodlookup pl2= new Paymethodlookup();
		pl2.setActive(false);
		pl2.setStore((Store)c.getStoreRealm());
		pl2.setPaymethod(p2);
		paymethodlookupRepository.create(pl2);
		
		List<Paymethod> l = paymethodRepository.findActivePaymentmethod((Store)c.getStoreRealm());
		Assert.assertEquals(1, l.size());
		
		List<Paymethodlookup> l1 = paymethodlookupRepository.findAll();
		Assert.assertEquals(2, l1.size());
		
		
	}
}
