package com.serpics.admin;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpics.catalog.persistence.Catalog;
import com.serpics.catalog.persistence.Product;
import com.serpics.catalog.repositories.CategoryRelationRepository;
import com.serpics.catalog.services.CatalogService;
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
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


@Component
@Scope("prototype")
public class CategoryUI extends UI{
	
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

	@Resource(name = "cartService")
	CartService cs;

	@Resource
	OrderService orderService;

	
	@Autowired
	private CategoryRelationRepository catRepo;


	
	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);
        try {
			test();
		} catch (SerpicsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Button button = new Button("Click Me");
        button.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
            	try {
					CommerceSessionContext context = ce.connect("default-store", "superuser", "admin".toCharArray());
				} catch (SerpicsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                layout.addComponent(new Label("Thank you for clicking"));
                for (com.serpics.catalog.persistence.Category c : catalogService.findRootCategory()){
//                	catRepo.findByCategory_parent(c);
                	System.out.println(c.getCode());
                }
                
            }
        });
        layout.addComponent(button);
        
		
	}

	
	
	public void test() throws SerpicsException {

		b.initIstance();
		CommerceSessionContext context = ce.connect("default-store", "superuser", "admin".toCharArray());
		
		
		assertNotNull("not connect with context !", context);

		Catalog c = new Catalog();
		c.setCode("default-catalog");
		c = catalogService.createCatalog(c);
		context.setCatalog(c);
		
		Product p = new Product();
		p.setCode("product");
		p.setBuyable(1);
		p.setCatalog(c);
		catalogService.createproduct(p);
		Product p1 = new Product();
		p1.setCode("product1");
		p1.setBuyable(1);
		p1.setCatalog(c);
		catalogService.createproduct(p1);
		
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

		Order or = orderService.createOrder(cart);
		assertNotNull("order not create", or);
		assertEquals(0, cartRepository.findAll().size());
		assertEquals(1, orderRepository.findAll().size());
	}
}
