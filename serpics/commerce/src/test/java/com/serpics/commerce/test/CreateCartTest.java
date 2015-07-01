package com.serpics.commerce.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.data.model.Catalog;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.data.model.Pricelist;
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
import com.serpics.test.ExecutionTestListener;
import com.serpics.warehouse.InventoryNotAvailableException;

@ContextConfiguration({ "classpath*:META-INF/applicationContext.xml" })
@TestExecutionListeners({ ExecutionTestListener.class, DependencyInjectionTestExecutionListener.class })
@Transactional
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
public class CreateCartTest {
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

    @Before
    public void init() {
        b.initIstance();
    }

    @Test
    public void test() throws SerpicsException {

        final CommerceSessionContext context = ce.connect("default-store", "superuser", "admin".toCharArray());


        assertNotNull("not connect with context !", context);

        Catalog c = new Catalog();
        c.setCode("default-catalog");
        c = catalogService.create(c);
        context.setCatalog(c);
        Pricelist pl = new Pricelist();
        pl.setCatalog(c);
        pl.setDefaultList(true);
        pl.setName("default-list");
        priceListRepository.create(pl);

        final Product p = new Product();
        p.setCode("product");
        p.setBuyable(1);
        p.setCatalog(c);
        
        productService.create(p);
        
        Price price = new Price();
        price.setCurrentPrice(100.0);
        price.setProductCost(9.0);
        priceService.addPrice(p ,price);
        
        final Product p1 = new Product();
        p1.setCode("product1");
        p1.setBuyable(1);
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
        assertEquals(1, cart.getOrderitems().size());
        Cartitem o = cart.getCartitems().iterator().next();
        assertEquals(10.0, o.getQuantity(), 0);
        o.setQuantity(11);
        cs.cartUpdate(o, cart);

        cart = cs.createSessionCart();
        assertEquals(1, cart.getOrderitems().size());
        o = cart.getCartitems().iterator().next();
        assertEquals(11.0, o.getQuantity(), 0);

        cs.cartAdd("product", 10.0, true);
        cart = cs.createSessionCart();
        assertEquals(1, cart.getOrderitems().size());
        o = cart.getCartitems().iterator().next();
        assertEquals(21.0, o.getQuantity(), 0);
        assertEquals(100, o.getSkuPrice(), 0);
        cs.cartAdd("product", 10.0, false);
        cart = cs.createSessionCart();
        assertEquals(2, cart.getOrderitems().size());
        cs.cartAdd("product1", 10.0, true);
        cart = cs.createSessionCart();
        assertEquals(3, cart.getOrderitems().size());
        cs.prepareCart(cart);
        assertEquals(4100.0, cart.getOrderAmount().doubleValue(), 0.0);

        final Order or = orderService.createOrder(cart);
        assertNotNull("order not create", or);
        assertEquals(0, cartRepository.findAll().size());
        assertEquals(1, orderRepository.findAll().size());
    }

    @Test
    public void cartDelete() throws InventoryNotAvailableException, ProductNotFoundException {
        Cart cart = cs.createSessionCart();
        cart = cs.cartAdd("product", 1, false);
        cs.cartDelete(cart);
    }
}
