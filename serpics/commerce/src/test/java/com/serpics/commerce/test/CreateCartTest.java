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
import com.serpics.catalog.persistence.Catalog;
import com.serpics.catalog.persistence.Product;
import com.serpics.catalog.services.CatalogService;
import com.serpics.catalog.services.ProductService;
import com.serpics.commerce.persistence.Cart;
import com.serpics.commerce.persistence.Order;
import com.serpics.commerce.persistence.Orderitem;
import com.serpics.commerce.repositories.CartRepository;
import com.serpics.commerce.repositories.OrderRepository;
import com.serpics.commerce.services.CartService;
import com.serpics.commerce.services.OrderService;
import com.serpics.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.core.session.CommerceSessionContext;
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

        final Product p = new Product();
        p.setCode("product");
        p.setBuyable(1);
        p.setCatalog(c);
        productService.create(p);
        final Product p1 = new Product();
        p1.setCode("product1");
        p1.setBuyable(1);
        p1.setCatalog(c);
        productService.create(p1);

        Cart cart = cs.createSessionCart();
        assertNotNull(cart);
        // Assert.assertEquals(0, cart.getPending());

        cs.cartAdd("product", 10.0, false);

        cart = cs.createSessionCart();
        assertEquals(1, cart.getOrderitems().size());
        Orderitem o = cart.getOrderitems().iterator().next();
        assertEquals(10.0, o.getQuantity(), 0);
        o.setQuantity(11);
        cs.cartUpdate(o, cart);

        cart = cs.createSessionCart();
        assertEquals(1, cart.getOrderitems().size());
        o = cart.getOrderitems().iterator().next();
        assertEquals(11.0, o.getQuantity(), 0);

        cs.cartAdd("product", 10.0, true);
        cart = cs.createSessionCart();
        assertEquals(1, cart.getOrderitems().size());
        o = cart.getOrderitems().iterator().next();
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
