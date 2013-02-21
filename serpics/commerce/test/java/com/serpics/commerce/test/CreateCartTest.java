package com.serpics.commerce.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.services.BaseService;
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

@ContextConfiguration({ "classpath:resources/applicationContext.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
public class CreateCartTest {
	@Autowired
	BaseService b;

	@Autowired
	CommerceEngine ce;
	@Resource
	CartRepository cartRepository;
	@Resource
	OrderRepository orderRepository;

	@Before
	public void init() {
		b.initIstance();
	}

	@Test
	public void test() throws SerpicsException {
		CommerceSessionContext context = ce.connect("default-store", "superuser", "admin".toCharArray());
		assertNotNull("not connect with context !", context);

		CartService cs = (CartService) ce.getApplicationContext().getBean("cartService");
		OrderService orderService = ce.getApplicationContext().getBean(OrderService.class);

		Cart cart = cs.createSessionCart();
		assertNotNull(cart);
		// Assert.assertEquals(0, cart.getPending());

		cs.cartAdd("product", 10.0, false);

		cart = cs.createSessionCart();
		assertEquals(1, cart.getOrderitems().size());
		Orderitem o = cart.getOrderitems().iterator().next();
		assertEquals(10.0, o.getQuantity(), 0);
		o.setQuantity(11);
		cs.cartUpdate(o);

		cart = cs.createSessionCart();
		assertEquals(1, cart.getOrderitems().size());
		o = cart.getOrderitems().iterator().next();
		assertEquals(11.0, o.getQuantity(), 0);

		Orderitem o1 = new Orderitem("p", "prova", 10, new Double(200), "EUR");
		cs.cartAdd("product", 10.0, true);
		cart = cs.createSessionCart();
		assertEquals(1, cart.getOrderitems().size());
		o = cart.getOrderitems().iterator().next();
		assertEquals(21.0, o.getQuantity(), 0);
		assertEquals(100, o.getSkuPrice(), 0);
		cs.cartAdd("product", 10.0, false);
		assertEquals(2, cart.getOrderitems().size());
		cs.cartAdd("product1", 10.0, true);
		assertEquals(3, cart.getOrderitems().size());

		Order or = orderService.createOrder(cart);
		assertNotNull("order not create", or);

		assertEquals(0, cartRepository.findAll().size());

		assertEquals(1, orderRepository.findAll().size());

	}
}
