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
import com.serpics.commerce.data.model.Cart;
import com.serpics.commerce.data.model.Shipmode;
import com.serpics.commerce.data.model.Shipmodelookup;
import com.serpics.commerce.data.repositories.CartRepository;
import com.serpics.commerce.data.repositories.ShipmodeRepository;
import com.serpics.commerce.data.repositories.ShipmodelookupRepository;
import com.serpics.commerce.services.CartService;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.membership.data.model.Address;
import com.serpics.membership.data.repositories.AddressRepository;
import com.serpics.membership.services.BaseService;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;



@ContextConfiguration({ "classpath:META-INF/base-serpics.xml" , 
	"classpath:META-INF/membership-serpics.xml", "classpath:META-INF/catalog-serpics.xml",
	"classpath:META-INF/warehouse-serpics.xml",
	"classpath:META-INF/commerce-serpics.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ShipmodeTest extends AbstractTransactionalJunit4SerpicTest {
	
	@Autowired
	BaseService baseService;
	
	@Autowired
	ShipmodeRepository shipmodeRepository;
	@Autowired
	ShipmodelookupRepository shipmodelookupRepository;
	@Autowired
	CommerceEngine commerceEngine;
	@Autowired
	CartService cartService;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	AddressRepository addressRepository;
	
	@Before
	public void beforeTest(){
		if(!baseService.isInitialized())
			baseService.initIstance();
	}
	
	@Test
	@Transactional
	public void shipmodeTest() throws SerpicsException{
		CommerceSessionContext c= commerceEngine.connect("default-store");
		
		Shipmode s1 = new Shipmode();
		s1.setName("Poste");
		
		s1 = shipmodeRepository.save(s1);
		
		Shipmodelookup sl1 = new Shipmodelookup();
		sl1.setStore((Store) c.getStoreRealm());
		sl1.setShipmode(s1);
		sl1 = shipmodelookupRepository.save(sl1);
		
		List<Shipmode> l = cartService.getShipmode();
		Assert.assertEquals(1, l.size());
		
		Cart cart = cartService.createSessionCart();
		Address address = new Address();
		
		address.setZipcode("26025");
		
		addressRepository.save(address);
		
		cart.setShippingAddress(address);
		
		cartRepository.saveAndFlush(cart);
		
		Shipmode s2 = new Shipmode();
		s2.setName("Poste2");
		
		s2 = shipmodeRepository.save(s2);
		
		Shipmodelookup sl2 = new Shipmodelookup();
		sl2.setStore((Store) c.getStoreRealm());
		sl2.setShipmode(s2);
		sl2.setZipcode("26026");
		sl2 = shipmodelookupRepository.save(sl2);
		
		Shipmode s3 = new Shipmode();
		s3.setName("Poste3");
		
		s3 = shipmodeRepository.save(s3);
		
		Shipmodelookup sl3 = new Shipmodelookup();
		sl3.setStore((Store) c.getStoreRealm());
		sl3.setShipmode(s3);
		sl3.setZipcode("26025");
		sl3 = shipmodelookupRepository.save(sl3);
		
		List<Shipmode> l2 = cartService.getShipmode();
		Assert.assertEquals(2, l2.size());
		
		
	}
}
