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

import com.serpics.base.data.repositories.LocaleRepository;
import com.serpics.catalog.facade.ProductFacade;
import com.serpics.catalog.facade.data.MediaData;
import com.serpics.catalog.facade.data.PriceData;
import com.serpics.catalog.facade.data.ProductData;
import com.serpics.catalog.services.CatalogService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.facade.CartFacade;
import com.serpics.commerce.facade.data.CartData;
import com.serpics.commerce.facade.data.CartItemData;
import com.serpics.commerce.facade.data.CartItemModification;
import com.serpics.commerce.facade.data.CartModificationStatus;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.membership.facade.data.AddressData;
import com.serpics.membership.services.BaseService;
import com.serpics.stereotype.SerpicsTest;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;


@ContextConfiguration({ "classpath:META-INF/base-serpics.xml" , 
	"classpath:META-INF/membership-serpics.xml", "classpath:META-INF/catalog-serpics.xml",
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
    	productFacade.addPrice(entry.getId(), price);
    	
    	MediaData media = new MediaData();
    	media.setSrc("prova.jpg");
    	media.setName("imgthmb");
    	media.setSequence(1);
    	productFacade.addMedia(entry.getId(),media);
    	
    	CartItemModification cartmod = cartFacade.cartAdd(entry.getCode());
    	Assert.assertEquals(CartModificationStatus.OK, cartmod.getModificationStatus());
    	
    	log.info("**** carrello " + cartFacade.getCurrentCart().getTotalProduct());
    	
    	
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
    	productFacade.addPrice(entry.getId(), price);
    	
    	MediaData media = new MediaData();
    	media.setSrc("prova2.jpg");
    	media.setName("imgthmb2");
    	media.setSequence(1);
    	productFacade.addMedia(entry.getId(),media);
    	
    	CartItemModification cartmod = cartFacade.cartAdd(entry.getCode(), 2);
    	Assert.assertEquals(CartModificationStatus.OK, cartmod.getModificationStatus());
    	
    	log.info("**** carrello " + cartFacade.getCurrentCart().getTotalProduct());
    	
    	
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
    	productFacade.addPrice(entry.getId(), price);
    	
    	MediaData media = new MediaData();
    	media.setSrc("prova3.jpg");
    	media.setName("imgthmb3");
    	media.setSequence(1);
    	productFacade.addMedia(entry.getId(),media);
    	
    	CartItemModification cartmod = cartFacade.cartAdd(entry.getCode());
    	Assert.assertEquals(CartModificationStatus.OK, cartmod.getModificationStatus());
    	
    	log.info("**** carrello " + cartFacade.getCurrentCart().getTotalProduct());
    	
    	
    	
	}
	private void displayCart() {
		CartData cart = cartFacade.getCurrentCart();
		
		Assert.assertNotNull(cart);
		Assert.assertEquals(3, cart.getOrderItems().size());
		
		for (CartItemData cartItemData : cart.getOrderItems()) {
			log.info("RIGA CARRELLO " + cartItemData.getSku() +  "  + " +  cartItemData.getQuantity() +  " - " + cartItemData.getSkuPrice());
			if( cartItemData.getSku().equals("PROD1")) cartItemData.setQuantity(3);
			if(cartItemData.getSku().equals("PROD2")) cartItemData.setQuantity(0);
		}
		cart = cartFacade.update(cart);
		
		Assert.assertEquals(2, cart.getOrderItems().size());
		
		
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
	}

	private void confirmOrder() {
		// TODO Auto-generated method stub
	}
	
}
