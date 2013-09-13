package com.serpics.admin.ui;


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

	@Autowired CatTree2 tree;
	
	@Autowired
	private CategoryRelationRepository catRepo;


	
	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);
       layout.addComponent(tree);
        
       
        
		
	}

	
	
	
}