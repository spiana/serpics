package com.serpics.commerce.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.data.model.Currency;
import com.serpics.base.data.model.Store;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.data.model.Cart;
import com.serpics.commerce.data.model.Shipmode;
import com.serpics.commerce.data.model.Shipmodelookup;
import com.serpics.commerce.data.model.Shipping;
import com.serpics.commerce.data.repositories.CartRepository;
import com.serpics.commerce.data.repositories.ShipmodeRepository;
import com.serpics.commerce.data.repositories.ShipmodelookupRepository;
import com.serpics.commerce.data.repositories.ShippingRepository;
import com.serpics.commerce.services.CartService;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.membership.data.model.Address;
import com.serpics.membership.data.repositories.AddressRepository;
import com.serpics.membership.services.BaseService;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;



@ContextConfiguration({ "classpath:META-INF/base-serpics.xml" , 
	"classpath:META-INF/postman-serpics.xml" ,
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
	@Autowired
	ShippingRepository shippingRepository;
	
	@Before
	public void beforeTest(){
		if(!baseService.isInitialized())
			baseService.initIstance();
	}
	
	@Test
	@Transactional
	public void shipmodeTest() throws SerpicsException{
		CommerceSessionContext c= commerceEngine.connect("default-store");
		
		Cart cart = cartService.createSessionCart();
		Address address = new Address();
		
		address.setZipcode("26025");
		
		addressRepository.save(address);
		
		cart.setShippingAddress(address);
		
		cartRepository.saveAndFlush(cart);
		
		Shipmode s1 = new Shipmode();
		s1.setName("Poste");
		s1.setShipmodeStrategy("defaultShipmodeStrategy");
		s1 = shipmodeRepository.save(s1);
		
		Shipmodelookup sl1 = new Shipmodelookup();
		sl1.setStore((Store) c.getStoreRealm());
		sl1.setShipmode(s1);
		sl1.setZipcode("26025");
		sl1 = shipmodelookupRepository.save(sl1);
		Set<Shipping> shippings1 = new HashSet<Shipping>();
		Shipping shipping1 = new Shipping();
		shipping1.setShipmodelookup(sl1);
		shipping1.setRangestart((double) 0);
		shipping1.setValue((double) 10);
		shipping1.setCurrency((Currency) c.getCurrency());
		shippingRepository.saveAndFlush(shipping1);
		shippings1.add(shipping1);
		sl1.setShippings(shippings1);
		sl1 = shipmodelookupRepository.save(sl1);
		Set<Shipmodelookup> shipmodelookups = new HashSet<Shipmodelookup>();
		s1.setShipmodelookups(shipmodelookups);
		shipmodelookups.add(sl1);
		s1 = shipmodeRepository.save(s1);

		List<Shipmode> l = cartService.getShipmode();
		Assert.assertEquals(1, l.size());
		cart.setShipmode(s1);
		cartRepository.saveAndFlush(cart);
		cart = cartService.prepareCart();
		
		Assert.assertEquals((double) 10, (double) cart.getTotalShipping(), (double) 0 );
		
		Shipmode s2 = new Shipmode();
		s2.setName("Poste2");
		s2.setShipmodeStrategy("defaultShipmodeStrategy");
		s2 = shipmodeRepository.save(s2);
		
		Shipmodelookup sl2 = new Shipmodelookup();
		sl2.setStore((Store) c.getStoreRealm());
		sl2.setShipmode(s2);
		sl2.setZipcode("26026");
		sl2 = shipmodelookupRepository.save(sl2);
		
		Shipmode s3 = new Shipmode();
		s3.setName("Poste3");
		s3.setShipmodeStrategy("defaultShipmodeStrategy");
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
