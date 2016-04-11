package com.serpics.commerce.test;

import java.util.Set;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.data.model.Store;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.services.CatalogService;
import com.serpics.catalog.services.PriceService;
import com.serpics.catalog.services.ProductService;
import com.serpics.commerce.PaymentIntent;
import com.serpics.commerce.PaymentState;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.data.model.Cart;
import com.serpics.commerce.data.model.Order;
import com.serpics.commerce.data.model.Payment;
import com.serpics.commerce.data.model.Paymethod;
import com.serpics.commerce.data.model.Paymethodlookup;
import com.serpics.commerce.data.repositories.PaymethodRepository;
import com.serpics.commerce.data.repositories.PaymethodlookupRepository;
import com.serpics.commerce.services.CartService;
import com.serpics.commerce.services.OrderService;
import com.serpics.commerce.services.PaymentService;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.membership.services.BaseService;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;



@ContextConfiguration({ "classpath:META-INF/base-serpics.xml" , 
	"classpath:META-INF/postman-serpics.xml" ,
	"classpath:META-INF/membership-serpics.xml", "classpath:META-INF/catalog-serpics.xml",
	"classpath:META-INF/warehouse-serpics.xml",
	"classpath:META-INF/commerce-serpics.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class PaymentTest extends AbstractTransactionalJunit4SerpicTest {
	
	@Autowired
	BaseService baseService;
	
	@Autowired
	CatalogService catalogService;
	
	@Autowired
	PaymethodRepository paymethodRepository;
	@Autowired
	PaymethodlookupRepository paymethodlookupRepository;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	PriceService priceService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	CommerceEngine commerceEngine;
	
	@Resource
	OrderService orderService;
	
	@Before
	public void beforeTest(){
		if(!baseService.isInitialized())
			baseService.initIstance();
		
		
	}
	
	@Test
	@Transactional
	public void paymentTest() throws SerpicsException{
		CommerceSessionContext c= commerceEngine.connect("default-store");
		catalogService.initialize();
		
		Paymethod p1 = new Paymethod("dummy");
		p1.setPaymentStrategy("dummyPaymentStrategy");
		
		p1 =paymethodRepository.save(p1);
		Paymethodlookup pl1= new Paymethodlookup();
		pl1.setActive(true);
		pl1.setStore((Store)c.getStoreRealm());
		pl1.setPaymethod(p1);
		paymethodlookupRepository.save(pl1);
	
		createProduct();
		cartService.cartAdd("P1", 1D, false);
		cartService.addPaymethod("dummy");
		
		Cart cart = cartService.getSessionCart();
		paymentService.createPayment(cart , PaymentIntent.SALE);
		Payment payment = paymentService.findCurrentPendingPayment(cart);
		payment = paymentService.authorizePayment(payment);
		Assert.assertEquals(PaymentState.COMPLETED, payment.getState());
		orderService.createOrder(cart);
		
		cartService.cartAdd("P1", 1D, false);
		cartService.addPaymethod("dummy");
		cart = cartService.getSessionCart();
		payment= paymentService.createPayment(cart, PaymentIntent.AUTHORIZE);
		
		payment = paymentService.authorizePayment(payment);
		Assert.assertEquals(PaymentState.APPROVED, payment.getState());
		Assert.assertEquals(cart.getOrderAmount(), payment.getAmount());
		
		payment = paymentService.capturePayment(payment);
		
		Assert.assertEquals(PaymentState.COMPLETED, payment.getState());
		Assert.assertEquals(cart.getOrderAmount(), payment.getCaptureAmount());
		orderService.createOrder(cart);
		
		cartService.cartAdd("P1", 1D, false);
		cartService.addPaymethod("dummy");
		cart = cartService.getSessionCart();
		payment= paymentService.createPayment(cart, PaymentIntent.AUTHORIZE);
		
		Order order = orderService.createOrder(cart);
		Set<Payment>  payments = order.getPayments();
		
		Assert.assertEquals(1, payments.size());
		Assert.assertEquals(PaymentState.APPROVED, payments.iterator().next().getState());
		
	}
	
	private void createProduct(){
		
		Product p = new Product();
		p.setCode("P1");
		Price price = new Price();
		price.setCurrentPrice(10D);
		price.setProduct(p);
		
		productService.create(p) ;
		
		priceService.create(price);
		
		
		
		productService.detach(p);
		
		
	}
}
