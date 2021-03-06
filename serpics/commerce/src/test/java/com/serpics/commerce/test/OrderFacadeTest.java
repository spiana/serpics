/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.commerce.test;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.commerce.CommerceEngine;
import com.serpics.base.commerce.session.CommerceSessionContext;
import com.serpics.base.facade.data.MediaData;
import com.serpics.catalog.facade.ProductFacade;
import com.serpics.catalog.facade.data.PriceData;
import com.serpics.catalog.facade.data.ProductData;
import com.serpics.catalog.services.CatalogService;
import com.serpics.commerce.facade.CartFacade;
import com.serpics.commerce.facade.OrderFacade;
import com.serpics.commerce.facade.data.CartData;
import com.serpics.commerce.facade.data.CartItemData;
import com.serpics.commerce.facade.data.CartModification;
import com.serpics.commerce.facade.data.CartModificationStatus;
import com.serpics.commerce.facade.data.OrderData;
import com.serpics.core.SerpicsException;
import com.serpics.i18n.data.repositories.LocaleRepository;
import com.serpics.membership.facade.data.AddressData;
import com.serpics.membership.services.BaseService;
import com.serpics.stereotype.SerpicsTest;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;


@ContextConfiguration({ 
	"classpath:META-INF/i18n-serpics.xml" , "classpath:META-INF/mediasupport-serpics.xml" ,
	"classpath:META-INF/base-serpics.xml" , 
	"classpath:META-INF/postman-serpics.xml" ,
	"classpath:META-INF/membership-serpics.xml", "classpath:META-INF/catalog-serpics.xml",
	"classpath:META-INF/warehouse-serpics.xml",
	"classpath:META-INF/commerce-serpics.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@SerpicsTest("default-store")
@Transactional
public class OrderFacadeTest extends AbstractTransactionalJunit4SerpicTest {
	Logger log = LoggerFactory.getLogger(OrderFacadeTest.class);

	@Autowired
	BaseService baseService;
	
	@Autowired
	CatalogService catalogService;
	
	@Autowired 
	CommerceEngine commerceEngine;
	
	@Resource
	ProductFacade productFacade;
	
	@Resource
	CartFacade cartFacade;
	
	@Resource
	OrderFacade orderFacade;
	 
	CommerceSessionContext context;

	@Autowired
	LocaleRepository localeRepository;
	 
	 @Before
	public void beforeTest() throws SerpicsException {
		 if (!baseService.isInitialized())
			 baseService.initIstance();
	    context = commerceEngine.connect("default-store", "superuser", "admin".toCharArray());
	    context.setLocale(localeRepository.findByLanguage("en"));
	    catalogService.initialize();
	}
	    
	    
	@Test
	@Transactional
	public void shoppingTest() {
		createProduct1();
		createProduct2();
		createProduct3();
		displayCart();
		setAddress();
		prepareOrder();
		confirmOrder();		
	}

	
	private void createProduct1() {
		
		ProductData entry = new ProductData();
    	entry.setCode("PROD1");
    	entry.setDescription("PRODOTTO DI TEST NUMERO 1");
    	entry.setBuyable(true);
    	entry.setPublished(true); 
    	entry.setDowloadable(true);
    	entry.setManufacturSku("0001P");
    	entry.setMetaDescription("MD PROD1");
    	entry.setMetaKey("MK PROD1");
    	entry.setUnitMeas("Q");
    	
    	entry.setUrl("http://www.test.it");
    	entry.setWeight(10.50);
    	entry.setWeightMeas("KG");
    	
    	entry  = productFacade.create(entry);
    	
    	PriceData price = new PriceData();
    	price.setCurrentPrice(10.00);
    	price.setProductCost(5.00);
    	price.setPrecedence(1);
    	productFacade.addPrice(entry.getId(), price);
    	
    	MediaData media = new MediaData();
    	media.setSource("prova.jpg");
    	media.setName("imgthmb");
    	media.setSequence(1);
    	productFacade.addMedia(entry.getId(),media);
    	
    	CartModification cartmod = cartFacade.cartAdd(entry.getCode());
    	Assert.assertEquals(CartModificationStatus.OK, cartmod.getModificationStatus());
    	
    	log.info("**** carrello " + cartFacade.getCurrentCart().getTotalProduct());
    	
    	
	}
	
	private void createProduct2() {
	
	
		ProductData entry = new ProductData();
    	entry.setCode("PROD2");
    	entry.setDescription("PRODOTTO DI TEST NUMERO 2");
    	entry.setBuyable(true);
    	entry.setPublished(true); 
    	entry.setDowloadable(true);
    	
    	entry  = productFacade.create(entry);
    	
    	PriceData price = new PriceData();
    	price.setCurrentPrice(10.00);
    	price.setPrecedence(1);
    	price.setProductCost(5.00);
    	productFacade.addPrice(entry.getId(), price);
    	
    	MediaData media = new MediaData();
    	media.setSource("prova2.jpg");
    	media.setName("imgthmb2");
    	media.setSequence(1);
    	productFacade.addMedia(entry.getId(),media);
    	
    	CartModification cartmod = cartFacade.cartAdd(entry.getCode(), 2);
    	Assert.assertEquals(CartModificationStatus.OK, cartmod.getModificationStatus());
    	
    	log.info("**** carrello " + cartFacade.getCurrentCart().getTotalProduct());
    	
    	
	}
	
	private void createProduct3() {
		
		
		ProductData entry = new ProductData();
    	entry.setCode("PROD3");
    	entry.setDescription("PRODOTTO DI TEST NUMERO 3");
    	entry.setBuyable(true);
    	entry.setPublished(true); 
    	entry.setDowloadable(true);
    	
    	entry  = productFacade.create(entry);
    	
    	PriceData price = new PriceData();
    	price.setCurrentPrice(10.00);
    	price.setProductCost(5.00);
    	price.setPrecedence(1);
    	productFacade.addPrice(entry.getId(), price);
    	
    	MediaData media = new MediaData();
    	media.setSource("prova3.jpg");
    	media.setName("imgthmb3");
    	media.setSequence(1);
    	productFacade.addMedia(entry.getId(),media);
    	
    	CartModification cartmod = cartFacade.cartAdd(entry.getCode());
    	Assert.assertEquals(CartModificationStatus.OK, cartmod.getModificationStatus());
    	
    	log.info("**** carrello " + cartFacade.getCurrentCart().getTotalProduct());
    	
    	
    	
	}
	private void displayCart()  {
		CartData cart = cartFacade.getCurrentCart();
		
		Assert.assertNotNull(cart);
		Assert.assertEquals(3, cart.getOrderItems().size());
		
		for (CartItemData cartItemData : cart.getOrderItems()) {
			log.info("RIGA CARRELLO " + cartItemData.getSku() +  "  + " +  cartItemData.getQuantity() +  " - " + cartItemData.getSkuPrice());
			if( cartItemData.getSku().equals("PROD1")) {
				cartItemData.setQuantity(3);
					CartModification cartmod = cartFacade.update(cartItemData);
			    	Assert.assertEquals(CartModificationStatus.OK, cartmod.getModificationStatus());
			}
			if(cartItemData.getSku().equals("PROD2")){
				cartItemData.setQuantity(0);
				CartModification cartmod = cartFacade.update(cartItemData);
		    	Assert.assertEquals(CartModificationStatus.OK, cartmod.getModificationStatus());
			}
		}
		
		Assert.assertEquals(3, cart.getOrderItems().size());
		
		
	}
	
	private void setAddress() {
		AddressData billingAddress = new AddressData();
		billingAddress.setAddress1("via di prova");
		billingAddress.setStreetNumber("12");
		billingAddress.setCity("Verbania");
		AddressData shippingAddress = new AddressData();
		shippingAddress.setAddress1("via di destinazione");
		shippingAddress.setStreetNumber("12");
		shippingAddress.setCity("Verbania");
		cartFacade.addShippingAddress(shippingAddress);
		cartFacade.addBillingAddress(billingAddress);
		
	}
	
	private void prepareOrder() {
		CartData cart = cartFacade.getCurrentCart();
		Assert.assertNotNull("BILLING ADDRESS NOT SET" + cart.getBillingAddress());
		Assert.assertEquals("Numbers of row's cart is not like expected",2, cart.getOrderItems().size());
		
		OrderData order = orderFacade.placeOrder();
		
		Assert.assertEquals("Numbers of row's orders is not like expected",2, cart.getOrderItems().size());
		Assert.assertNotNull("ID ordine non salvato",order.getId());
	}

	private void confirmOrder() {
		// TODO Auto-generated method stub
	}
	
}
