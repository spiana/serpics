package com.serpics.catalog.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.catalog.persistence.Catalog;
import com.serpics.catalog.persistence.Category;
import com.serpics.catalog.persistence.Product;
import com.serpics.catalog.repositories.CatalogRepository;
import com.serpics.catalog.repositories.ProductRepository;
import com.serpics.catalog.services.CatalogService;
import com.serpics.core.CommerceEngine;
import com.serpics.core.SerpicsException;
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

	@Test
	@Transactional
	public void test() throws SerpicsException {
		baseService.initIstance();
		CommerceSessionContext context = ce.connect("default-store", "superuser", "admin".toCharArray());
		Catalog catalog = new Catalog();
		catalog.setName("default-catalog");
		catalog = catalogService.createCatalog(catalog);
		context.setCatalog(catalog);
		List<Catalog> l = catalogRepository.findAll();

		Assert.assertEquals(1, l.size());

		Category category = new Category();
		category.setName("main");
		category.setUrl("main");
		catalogService.createCategory(category, null);
		Product p = new Product();
		p.setSku("test-sku");
		p.setCatalog(catalog);
		p.setName("test sku");
		p.setUrl(catalog.getName() + "/" + p.getSku());
		catalogService.createproduct(p);

		Product p1 = new Product();
		p1.setSku("test-sku");
		p1.setCatalog(catalog);

		Product p2 = productRepository.findOne(productRepository.makeSpecification(p1));
		Assert.assertEquals("test sku", p2.getName());

		catalogRepository.delete(catalog);
		List<Category> l1 = catalogService.findRootCategory();
		Assert.assertEquals(1, l1.size());
		List<Product> l2 = productRepository.findAll();
		Assert.assertEquals(0, l2.size());
	}
}
