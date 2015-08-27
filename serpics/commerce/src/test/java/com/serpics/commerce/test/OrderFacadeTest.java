package com.serpics.commerce.test;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.catalog.facade.ProductFacade;
import com.serpics.catalog.facade.data.MediaData;
import com.serpics.catalog.facade.data.PriceData;
import com.serpics.catalog.facade.data.ProductData;
import com.serpics.catalog.services.CatalogService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.facade.CartFacade;
import com.serpics.commerce.facade.data.CartData;
import com.serpics.commerce.facade.data.CartItemData;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.membership.facade.data.AddressData;
import com.serpics.membership.services.BaseService;
import com.serpics.stereotype.SerpicsTest;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration({ "classpath*:META-INF/applicationContext-test.xml" })
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
	 
	CommerceSessionContext context;

	 
	 @Before
	public void beforeTest() throws SerpicsException {
		 if (!baseService.isInitialized())
			 baseService.initIstance();
	    context = commerceEngine.connect("default-store", "superuser", "admin".toCharArray());
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
    	entry.setBuyable(1);
    	entry.setPublished(1); 
    	entry.setDowloadable(1);
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
    	productFacade.addPrice(entry.getUuid(), price);
    	
    	MediaData media = new MediaData();
    	media.setSrc("prova.jpg");
    	media.setName("imgthmb");
    	media.setSequence(1);
    	productFacade.addMedia(entry.getUuid(),media);
    	
    	CartData cart = cartFacade.cartAdd(entry.getUuid());
    	Assert.assertNotNull("carrello is null " + cart);
    	log.info("**** carrello " + cart.getTotalProduct());
    	
    	
	}
	
	private void createProduct2() {
	
	
		ProductData entry = new ProductData();
    	entry.setCode("PROD2");
    	entry.setDescription("PRODOTTO DI TEST NUMERO 2");
    	entry.setBuyable(1);
    	entry.setPublished(1); 
    	entry.setDowloadable(1);
    	
    	entry  = productFacade.create(entry);
    	
    	PriceData price = new PriceData();
    	price.setCurrentPrice(10.00);
    	price.setPrecedence(1);
    	price.setProductCost(5.00);
    	productFacade.addPrice(entry.getUuid(), price);
    	
    	MediaData media = new MediaData();
    	media.setSrc("prova2.jpg");
    	media.setName("imgthmb2");
    	media.setSequence(1);
    	productFacade.addMedia(entry.getUuid(),media);
    	
    	CartData cart = cartFacade.cartAdd(entry.getUuid(), 2);
    	Assert.assertNotNull("carrello is null " + cart);
    	log.info("**** carrello " + cart.getTotalProduct());
    	
    	
	}
	
	private void createProduct3() {
		
		
		ProductData entry = new ProductData();
    	entry.setCode("PROD3");
    	entry.setDescription("PRODOTTO DI TEST NUMERO 3");
    	entry.setBuyable(1);
    	entry.setPublished(1); 
    	entry.setDowloadable(1);
    	
    	entry  = productFacade.create(entry);
    	
    	PriceData price = new PriceData();
    	price.setCurrentPrice(10.00);
    	price.setProductCost(5.00);
    	price.setPrecedence(1);
    	productFacade.addPrice(entry.getUuid(), price);
    	
    	MediaData media = new MediaData();
    	media.setSrc("prova3.jpg");
    	media.setName("imgthmb3");
    	media.setSequence(1);
    	productFacade.addMedia(entry.getUuid(),media);
    	
    	CartData cart = cartFacade.cartAdd(entry.getUuid());
    	Assert.assertNotNull("carrello is null " + cart);
    	log.info("**** carrello " + cart.getTotalProduct());
    	
    	
	}
	private void displayCart() {
		CartData cart = cartFacade.getCurrentCart();
		
		Assert.assertNotNull(cart);
		Assert.assertEquals(3, cart.getOrderItems().size());
		
		/* UPDATE QUANTITY */
		String lastUuid = null;
		for (CartItemData cartItemData : cart.getOrderItems()) {
			log.info("RIGA CARRELLO " + cartItemData.getSku() +  "  + " +  cartItemData.getQuantity() +  " - " + cartItemData.getSkuPrice());
			if( cartItemData.getSku().equals("PROD1")) cartItemData.setQuantity(3);
			if(cartItemData.getSku().equals("PROD2")) cartItemData.setQuantity(0);
			lastUuid = cartItemData.getUuid();
		}
		cart = cartFacade.update(cart);
		
		Assert.assertEquals(2, cart.getOrderItems().size());
		
		
	}
	
	private void setAddress() {
		CartData cart = cartFacade.getCurrentCart();
		AddressData billingAddress = new AddressData();
		billingAddress.setAddress1("via di prova");
		billingAddress.setStreetNumber("12");
		billingAddress.setCity("Verbania");
		AddressData shippingAddress = new AddressData();
		shippingAddress.setAddress1("via di destinazione");
		shippingAddress.setStreetNumber("12");
		shippingAddress.setCity("Verbania");
		cartFacade.addAddress(shippingAddress, billingAddress);
		
	}
	
	private void prepareOrder() {
		CartData cart = cartFacade.getCurrentCart();
		Assert.assertNotNull("BILLING ADDRESS NOT SET" + cart.getBillingAddress());
	}

	private void confirmOrder() {
		
    
		// TODO Auto-generated method stub
    	
		
	}


	

	


	


	
}
