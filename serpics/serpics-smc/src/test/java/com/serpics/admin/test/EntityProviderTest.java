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
package com.serpics.admin.test;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.data.model.MultilingualText;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.data.repositories.ProductRepository;
import com.serpics.catalog.services.CatalogService;
import com.serpics.catalog.services.ProductService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.membership.data.model.PrimaryAddress;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.services.BaseService;
import com.serpics.membership.services.UserService;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;
import com.serpics.vaadin.jpacontainer.ServiceContainerFactory;
import com.serpics.vaadin.ui.filter.MultilingualLikeFilter;
import com.vaadin.addon.jpacontainer.JPAContainer;



@ContextConfiguration({ "classpath:META-INF/base-serpics.xml" ,
						"classpath:META-INF/membership-serpics.xml","classpath:META-INF/catalog-serpics.xml"})
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class EntityProviderTest extends AbstractTransactionalJunit4SerpicTest {

	@Resource
	BaseService baseService;
	
	@Autowired
	CatalogService catalogService;
	
	@Resource
	UserService userService;
	
	@Resource
	ProductRepository productRepository;
	
	@Resource
	CommerceEngine engine;
	
	@Autowired
	ProductService productService;
	
	@Test
	@Transactional
	public void EntityProviderTest () throws SerpicsException{
		if (!baseService.isInitialized())
			baseService.initIstance();
		
		CommerceSessionContext context = engine.connect("default-store");
		
		
		UsersReg u = new UsersReg();
		u.setLogonid("test12");
		JPAContainer c = ServiceContainerFactory.make(UsersReg.class);
		int i = c.getItemIds().size();
		Assert.assertEquals(0, i);
		userService.registerUser(u, new PrimaryAddress());
		
		UsersReg u1 = new UsersReg();
		u1.setLogonid("test13");
		userService.registerUser(u1, new PrimaryAddress());
		
		c.refresh();
		i = c.getItemIds().size();
		Assert.assertEquals(2, i);
		
		UsersReg u2 = new UsersReg();
		u2.setLogonid("user567");
		userService.registerUser(u2, new PrimaryAddress());
		
		// test if contains
		c.addContainerFilter("logonid", "er56", false, false);
		c.refresh();
		i = c.getItemIds().size();
		Assert.assertEquals(1, i);
		
		// test if start with
		c.addContainerFilter("logonid", "use", false, true);
		c.refresh();
		i = c.getItemIds().size();
		Assert.assertEquals(1, i);
	}
	
	
	@Test
	@Transactional
	public void EntityProviderTest1 () throws SerpicsException{
		if (!baseService.isInitialized())
			baseService.initIstance();
		
		engine.connect("default-store");
		
		UsersReg u = new UsersReg();
		u.setLogonid("test12");
		JPAContainer c = ServiceContainerFactory.make(UsersReg.class);
		int i = c.getItemIds().size();
		Assert.assertEquals(0, i);
		
		userService.registerUser(u, new PrimaryAddress());
		
		engine.connect("test-store");
		UsersReg u1 = new UsersReg();
		u1.setLogonid("test13");
		userService.registerUser(u1, new PrimaryAddress());
		
		UsersReg u2 = new UsersReg();
		u2.setLogonid("test131");
		userService.registerUser(u2, new PrimaryAddress());
		
		c.refresh();
		i = c.getItemIds().size();
		Assert.assertEquals(2, i);
		
		engine.connect("default-store");
		c.refresh();
		i = c.getItemIds().size();
		Assert.assertEquals(1, i);
	}
	
	@Test
	@Transactional
	public void multilingualTest() throws SerpicsException{
		if (!baseService.isInitialized())
			baseService.initIstance();
		catalogService.initialize();
		CommerceSessionContext context = engine.connect("default-store" ,"superuser" ,"admin".toCharArray());
		context.setCatalog(catalogService.findByCode("default-catalog"));
		Product p = new Product();
		p.setCode("p1");
		p.setDescription(new MultilingualText("en", "test"));
		
		productService.create(p);
		
		Product p2 = new Product();
		p2.setCode("p2");
		p2.setDescription(new MultilingualText("en", "test"));
		
		productService.create(p2);
		
		Product p3 = new Product();
		p3.setCode("p3");
		p3.setDescription(new MultilingualText("it", "test"));
		
		productService.create(p3);
		
		Page<Product> page =productRepository.findAll(new Specification<Product>() {
			
			@Override
			public Predicate toPredicate(Root<Product> arg0, CriteriaQuery<?> arg1,
					CriteriaBuilder arg2) {
				
				Subquery subquery = arg1.subquery(MultilingualText.class);
				//CriteriaQuery<MultilingualText> subquery = arg2.createQuery(MultilingualText.class);
				Root ml = subquery.from(MultilingualText.class);
				
				Predicate p1 = arg2.equal(ml.join("map").get("language"), "en");
				Predicate p2 = arg2.equal(ml.join("map").get("text"), "test");
				
				subquery.select(ml).where(arg2.and(p1,p2));
				Predicate p3 = arg2.like(arg0.<String>get("code"),arg2.literal("p3%"));
				Predicate p4 =arg2.in(arg0.get("description")).value(subquery);
				
				return arg2.or(p3,p4);
				
				
			}
		}, new PageRequest(0, 100));
		
		Assert.assertEquals(3, page.getNumberOfElements());
//		
		JPAContainer c = ServiceContainerFactory.make(Product.class);
		MultilingualLikeFilter j = new MultilingualLikeFilter("description", "en", "%est%");
		c.addContainerFilter(j);
	
		Assert.assertEquals(2, c.getItemIds().size());
		
//		MultilingualLikeFilter j0 = new MultilingualLikeFilter("name", "en", "%est%");
//		c.removeAllContainerFilters();
//		c.addContainerFilter(j0);
//		c.addContainerFilter(new Equal("code" , "p2"));
//		Assert.assertEquals(1, c.getItemIds().size());
//		
//		MultilingualLikeFilter j1 = new MultilingualLikeFilter("name", "it", "%est%");
//		c.removeAllContainerFilters();
//		c.addContainerFilter(j1);
//		Assert.assertEquals(1, c.getItemIds().size());
	}
}
