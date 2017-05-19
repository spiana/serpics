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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.catalog.data.model.Catalog;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.data.repositories.PriceListRepository;
import com.serpics.catalog.services.CatalogService;
import com.serpics.catalog.services.PriceService;
import com.serpics.catalog.services.ProductService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.data.model.Cart;
import com.serpics.commerce.data.model.Cartitem;
import com.serpics.commerce.data.model.Order;
import com.serpics.commerce.data.repositories.CartRepository;
import com.serpics.commerce.data.repositories.OrderRepository;
import com.serpics.commerce.services.CartService;
import com.serpics.commerce.services.OrderService;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.membership.services.BaseService;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration({ 
	"classpath:META-INF/i18n-serpics.xml" , "classpath:META-INF/mediasupport-serpics.xml" ,
	"classpath:META-INF/base-serpics.xml" , 
	"classpath:META-INF/postman-serpics.xml" ,
	"classpath:META-INF/membership-serpics.xml", "classpath:META-INF/catalog-serpics.xml",
	"classpath:META-INF/warehouse-serpics.xml",
	"classpath:META-INF/commerce-serpics.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class CreateCartTest extends AbstractTransactionalJunit4SerpicTest {
    @Autowired
    BaseService b;

    @Resource
    CatalogService catalogService;

    @Autowired
    CommerceEngine ce;
    @Resource
    CartRepository cartRepository;
    @Resource
    OrderRepository orderRepository;
    @Resource
    ProductService productService;

    @Resource
    PriceService priceService;
    
    @Resource
    PriceListRepository priceListRepository;
    
    @Resource(name = "cartService")
    CartService cs;

    @Resource
    OrderService orderService;

    private CommerceSessionContext context;
     
    @Before
    public void init() throws SerpicsException {
    	if (!b.isInitialized())
    		b.initIstance();
    	
    	
    	context = ce.connect("default-store", "superuser", "admin".toCharArray());
    	catalogService.initialize();
    }

    @Test
    @Transactional
    public void test() throws SerpicsException {


        assertNotNull("not connect with context !", context);

        Catalog c = catalogService.findByCode("default-catalog");
        
        assertNotNull("default catalog not found !", c);


        final Product p = new Product();
        p.setCode("product");
        p.setBuyable(true);
        p.setCatalog(c);
        
        productService.create(p);
        
        Price price = new Price();
        price.setCurrentPrice(100.0);
        price.setProductCost(9.0);
        priceService.addPrice(p ,price);
        
        final Product p1 = new Product();
        p1.setCode("product1");
        p1.setBuyable(true);
        p1.setCatalog(c);
        productService.create(p1);
        
        Price price1 = new Price();
        price1.setCurrentPrice(110.0);
        price1.setProductCost(9.0);
        price1.setPrecedence(0.0);
        priceService.addPrice(p1 ,price1);

        
        Price price2 = new Price();
        price2.setCurrentPrice(100.0);
        price2.setProductCost(9.0);
        price2.setPrecedence(1.0);
        priceService.addPrice(p1 ,price2);

        
        
        Cart cart = cs.createSessionCart();
        assertNotNull(cart);
        // Assert.assertEquals(0, cart.getPending());

        cs.cartAdd("product", 10.0, false);

        cart = cs.createSessionCart();
        assertEquals(1, cart.getItems().size());
        Cartitem o = cart.getItems().iterator().next();
        assertEquals(10.0, o.getQuantity(), 0);
        o.setQuantity(11);
        cs.cartUpdate(o, cart);

        cart = cs.createSessionCart();
        assertEquals(1, cart.getItems().size());
        o = cart.getItems().iterator().next();
        assertEquals(11.0, o.getQuantity(), 0);

        cs.cartAdd("product", 10.0, true);
        cart = cs.createSessionCart();
        assertEquals(1, cart.getItems().size());
        o = cart.getItems().iterator().next();
        assertEquals(21.0, o.getQuantity(), 0);
        assertEquals(100, o.getSkuPrice(), 0);
        cs.cartAdd("product", 10.0, false);
        cart = cs.createSessionCart();
        assertEquals(2, cart.getItems().size());
        cs.cartAdd("product1", 10.0, true);
        cart = cs.createSessionCart();
        assertEquals(3, cart.getItems().size());
        cs.prepareCart(cart);
        assertEquals(4100.0, cart.getOrderAmount().doubleValue(), 0.0);

        final Order or = orderService.createOrder(cart);
        assertNotNull("order not create", or);
        assertEquals(0, cartRepository.findAll().size());
        assertEquals(1, orderRepository.findAll().size());
    }

    
    @Test
    @Transactional
    public void cartDelete() throws SerpicsException {
    	 
    	final Product p1 = new Product();
        p1.setCode("product");
        p1.setBuyable(true);
        productService.create(p1);
        
        Price price1 = new Price();
        price1.setCurrentPrice(110.0);
        price1.setProductCost(9.0);
        price1.setPrecedence(0.0);
        priceService.addPrice(p1 ,price1);

        Cart cart = cs.createSessionCart();
        cart = cs.cartAdd("product", 1, false);
        Assert.assertTrue(cart.getItems().size()>0);
        cs.cartDelete(cart);
    }
}
