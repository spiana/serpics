package com.serpics.catalog.test;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.catalog.facade.BrandFacade;
import com.serpics.catalog.facade.CtentryFacade;
import com.serpics.catalog.facade.data.BrandData;
import com.serpics.catalog.facade.data.CategoryData;
import com.serpics.catalog.facade.data.ProductData;
import com.serpics.catalog.services.CatalogService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.membership.services.BaseService;
import com.serpics.stereotype.SerpicsTest;
import com.serpics.test.ExecutionTestListener;

@ContextConfiguration({ "classpath*:META-INF/applicationContext.xml" })
@TestExecutionListeners({ ExecutionTestListener.class, DependencyInjectionTestExecutionListener.class })
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)

@SerpicsTest("default-store")
@Transactional
public class CatalogFacadeTest {
	Logger log = LoggerFactory.getLogger(CatalogFacadeTest.class);
	@Resource
	CtentryFacade ctentryFacade;
	
	@Resource
	BrandFacade brandFacade;

    @Autowired
    BaseService baseService;
    
    @Autowired
    CommerceEngine commerceEngine;

    @Autowired
    CatalogService catalogService;
    
    
    
    CommerceSessionContext context;

    @Before
    public void beforeTest() throws SerpicsException {
        baseService.initIstance();
        context = commerceEngine.connect("default-store", "superuser", "admin".toCharArray());
        catalogService.initialize();
    }
    
    
    @Test
    public void catalogManager() {
    	createCategory();
    	listCategory();
    	createProduct();
    	listProduct();
    }
    
    
    private void createCategory() {
    	CategoryData entryU = new CategoryData();
    	entryU.setCode("UOMO");
    	entryU.setDescription("UOMO");
    	entryU.setUrl("http://prova cateogrty");
    	entryU = ctentryFacade.addCategory(entryU);
    	
    	
    	CategoryData entryD = new CategoryData();
    	entryD.setCode("DONNA");
    	entryD.setDescription("DONNA");
    	entryD.setUrl("http://prova cateogrty2");
    	entryD = ctentryFacade.addCategory(entryD);
    	
    	CategoryData entry = new CategoryData();
    	entry.setCode("ABB");
    	entry.setDescription("ABBIGLIAMNTO");
    	entry.setUrl("http://prova cateogrty3");
    	entry = ctentryFacade.addCategory(entry,entryU.getUuid());
    	ctentryFacade.addCategoryParent( entry.getUuid(), entryD.getUuid());
    	
    	CategoryData entry1 = new CategoryData();
    	entry1.setCode("BLUES");
    	entry1.setDescription("BLUES");
    	entry1.setUrl("http://prova cateogrty4");
    	ctentryFacade.addCategory(entry1, entryD.getUuid());
    	
    	entry1 = new CategoryData();
     	entry1.setCode("BOTTOM");
     	entry1.setDescription("BOTTOM");
     	entry1.setUrl("http://prova cateogrty5");
     	entry1.setMetaDescription("meta bo");
     	entry1.setMetaKey("bo");
    	ctentryFacade.addCategory(entry1, entry.getUuid());
    	
    	
    	
    }
    
    private void listCategory() {
    	Page<CategoryData> p= ctentryFacade.listCategory(new PageRequest(0, 10));
    	Assert.assertNotNull("Category not found", p);
    	log.info("Number category " +  p.getTotalElements());
    	
    	
    }
    
    private void createProduct() {
    	BrandData b = new BrandData();
    	b.setName("RANC");
    	b.setId(1);
    	b = brandFacade.addBrand(b);
    	
    	CategoryData c = ctentryFacade.findCategoryByCode("BLUES");
    	
    	ProductData entry = new ProductData();
    	entry.setCode("PROD1");
    	entry.setDescription("PRODOTTO DI TEST NUMERO 1");
    	entry.setBuyable(1);
    	entry.setPublished(1); 
    	entry.setDowloadable(1);
    	entry.setManufacturSku("0001P");
    	entry.setMetaDescription("MD PROD1");
    	entry.setMetaKey("MK PROD1");
    	entry.setUnitMeas("Q");
    	entry.setUrl("http://www.test.it");
    	entry.setWeight(10.50);
    	entry.setWeightMeas("KG");
    	entry.setBrand(b);
    	entry  = ctentryFacade.addProduct(entry);
    	ctentryFacade.addEntryCategoryParent( entry.getUuid(), c.getUuid());
    	
    	entry = new ProductData();
    	entry.setCode("PROD2");
    	entry.setDescription("PRODOTTO DI TEST NUMERO 2");
    	entry.setBuyable(1);
    	entry.setPublished(1); 
    	ctentryFacade.addProduct(entry,c.getUuid());
    }
    
    private void listProduct() {
    	Page<BrandData> lb = brandFacade.findAll(new PageRequest(0, 10));
    	Page<ProductData> p = ctentryFacade.listProduct(new PageRequest(0, 10));
    	Assert.assertNotNull("Product not found", p);
    	log.info("Number product " +  p.getTotalElements());
    	Page<CategoryData> l= ctentryFacade.listCategory(new PageRequest(0, 10));
    	
    }
}
