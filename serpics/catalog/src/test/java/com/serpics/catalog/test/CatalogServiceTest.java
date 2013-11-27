package com.serpics.catalog.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.AvailableforType;
import com.serpics.base.persistence.BaseAttribute;
import com.serpics.base.services.AttributeService;
import com.serpics.catalog.persistence.AbstractProduct;
import com.serpics.catalog.persistence.Bundle;
import com.serpics.catalog.persistence.Catalog;
import com.serpics.catalog.persistence.Category;
import com.serpics.catalog.persistence.Product;
import com.serpics.catalog.repositories.AbstractProductRepository;
import com.serpics.catalog.repositories.BundleRepository;
import com.serpics.catalog.repositories.CatalogRepository;
import com.serpics.catalog.repositories.ProductRepository;
import com.serpics.catalog.services.CatalogService;
import com.serpics.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.core.datatype.AttributeType;
import com.serpics.core.session.CommerceSessionContext;
import com.serpics.membership.services.BaseService;

@ContextConfiguration({ "classpath*:META-INF/applicationContext.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
public class CatalogServiceTest {

	@Resource
	BaseService baseService;

	@Resource
	CatalogService catalogService;

	@Autowired
	CommerceEngine ce;

	@Resource
	CatalogRepository catalogRepository;

	@Resource
	ProductRepository productRepository;
	@Resource
	BundleRepository bundleRepository;

	@Resource
	AbstractProductRepository abstractProductRepository;
	
	@Resource
	AttributeService attributeService;
	
	@Before
	public void beforeTest(){
		baseService.initIstance();
	}
	
	@Test
	public void AttributeTest() throws SerpicsException{
		CommerceSessionContext context = ce.connect("default-store", "superuser", "admin".toCharArray());
		
		BaseAttribute attribute = new BaseAttribute();
		attribute.setAttributeType(AttributeType.LONG);
		attribute.setAvailablefor(AvailableforType.USER);
		attribute.setName("test");
		attributeService.create(attribute);
		
		List<BaseAttribute> al = attributeService.findAll();
		Assert.assertEquals(1, al.size());
		
		baseService.createStore("test-store");
		context = ce.connect("test-store");
		
		BaseAttribute attribute1 = new BaseAttribute();
		attribute1.setAttributeType(AttributeType.TEXT);
		attribute1.setAvailablefor(AvailableforType.USER);
		attribute1.setName("test");
		attributeService.create(attribute1);
		
		BaseAttribute attribute2 = new BaseAttribute();
		attribute2.setAttributeType(AttributeType.TEXT);
		attribute2.setAvailablefor(AvailableforType.FEATURE);
		attribute2.setName("test1");
		attributeService.create(attribute2);
		
		List<BaseAttribute> al1 = attributeService.findAll();
		Assert.assertEquals(2, al1.size());
		
		List<BaseAttribute> al2 = attributeService.findbyAvailablefor(AvailableforType.FEATURE, new PageRequest(0, 10));
		Assert.assertEquals(1, al2.size());
	}
	
	
	//@Test
	@Transactional
	public void test() throws SerpicsException {
	
		CommerceSessionContext context = ce.connect("default-store", "superuser", "admin".toCharArray());
		Catalog catalog = new Catalog();
		catalog.setCode("default-catalog");
		catalog = catalogService.createCatalog(catalog);
		
		context.setCatalog(catalog);
		List<Catalog> l = catalogRepository.findAll();
		Assert.assertEquals(1, l.size());

		List<Catalog> _l = catalogRepository.findPublished();
		Assert.assertEquals(1, _l.size());


		Category category = new Category();
		category.setCode("main");
		category = catalogService.createCategory(category, null);
		
		Category category1 = new Category();
		category1.setCode("main1");
		category1 = catalogService.createCategory(category1, category);
		
		
		Product p = new Product();
		p.setCode("test-sku");
		p.setCatalog(catalog);
		p.setBuyable(1);
		catalogService.createproduct(p);

		Bundle b = new Bundle();
		b.setCode("bundle-sku");
		b.setCatalog(catalog);
		b.setBuyable(1);
		catalogService.createproduct(b);

		Product p1 = new Product();
		p1.setCode("test-sku");
		p1.setCatalog(catalog);
		p1.setBuyable(1);
		

		Bundle b1 = new Bundle();
		b1.setCode("bundle-sku");
		b1.setCatalog(catalog);
		b1.setBuyable(1);
	//	b1.setPublished(1);

		Product p2 = productRepository.findOne(productRepository.makeSpecification(p1));
		Assert.assertNotNull(p2);
		Assert.assertEquals("test-sku", p2.getCode());

		Bundle b2 = bundleRepository.findOne(bundleRepository.makeSpecification(b1));
		Assert.assertNotNull(b2);
		Assert.assertEquals("bundle-sku", b2.getCode());

		
		AbstractProduct p3 = abstractProductRepository.findOne(abstractProductRepository.makeSpecification( (AbstractProduct) p1));
		Assert.assertNotNull(p3);
		Assert.assertEquals("test-sku", p3.getCode());
		
	
		
		catalogService.deleteCatalogEntry(p3);
		List<Category> l1 = catalogService.findRootCategory();
		Assert.assertEquals(1, l1.size());
		List<Product> l2 = productRepository.findAll();
		Assert.assertEquals(0, l2.size());
		List<AbstractProduct> l3 = abstractProductRepository.findAll();
		Assert.assertEquals(1, l3.size());
		// catalogService.deleteCatalog(catalog);
		
	}
}
