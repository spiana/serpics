package com.serpics.catalog.test;

import java.util.List;
import java.util.Set;

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
import com.serpics.catalog.facade.CategoryFacade;
import com.serpics.catalog.facade.PriceFacade;
import com.serpics.catalog.facade.ProductFacade;
import com.serpics.catalog.facade.data.BrandData;
import com.serpics.catalog.facade.data.CategoryData;
import com.serpics.catalog.facade.data.MediaData;
import com.serpics.catalog.facade.data.PriceData;
import com.serpics.catalog.facade.data.ProductData;
import com.serpics.catalog.services.CatalogService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.membership.services.BaseService;
import com.serpics.stereotype.SerpicsTest;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;
import com.serpics.test.ExecutionTestListener;

@ContextConfiguration({ "classpath:META-INF/base-serpics.xml" , 
	"classpath:META-INF/membership-serpics.xml", "classpath:META-INF/catalog-serpics.xml"})
@TestExecutionListeners({ ExecutionTestListener.class, DependencyInjectionTestExecutionListener.class })
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
@SerpicsTest("default-store")
@Transactional
public class CatalogFacadeTest  extends AbstractTransactionalJunit4SerpicTest{
	Logger log = LoggerFactory.getLogger(CatalogFacadeTest.class);
	@Resource
	CategoryFacade categoryFacade;
	
	@Resource
	ProductFacade productFacade;
	
	@Resource
	BrandFacade brandFacade;
	
	@Resource
	PriceFacade priceFacade;
	
    @Autowired
    BaseService baseService;
    
    @Autowired
    CommerceEngine commerceEngine;

    @Autowired
    CatalogService catalogService;
    
  
    CommerceSessionContext context;

    @Before
    public void beforeTest() throws SerpicsException {
        if(!baseService.isInitialized())
        	baseService.initIstance();
       
        context = commerceEngine.connect("default-store", "superuser", "admin".toCharArray());
        catalogService.initialize();
    }
    
    
    @Test
    @Transactional
    public void catalogManager1() {
    	createCategory();
    	listCategory();
    	createProduct();
    	listProduct();
    	updateProduct();
    	deleteProduct();
  
    	
    }
    
    private void createCategory() {
    	CategoryData catU = new CategoryData();
    	catU.setCode("UOMO");
    	catU.setDescription("UOMO");
    	catU.setUrl("http://prova cateogrty");
    	catU = categoryFacade.create(catU);
    	
    	CategoryData catA = new CategoryData();
    	catA.setCode("ABB");
    	catA.setDescription("ABBIGLIAMNTO");
    	catA.setUrl("http://prova cateogrty3");
    	catA = categoryFacade.create(catA,catU.getUuid());
    	
    	CategoryData catB = new CategoryData();
     	catB.setCode("BOTTOM");
     	catB.setDescription("BOTTOM");
     	catB.setUrl("http://prova cateogrty5");
     	catB.setMetaDescription("meta bo");
     	catB.setMetaKey("bo");
     	catB = categoryFacade.create(catB, catA.getUuid());
     	
    	CategoryData catD = new CategoryData();
    	catD.setCode("DONNA");
    	catD.setDescription("DONNA");
    	catD.setUrl("http://prova cateogrty2");
    	catD = categoryFacade.create(catD);
    	
    	CategoryData catAD = new CategoryData();
    	catAD.setCode("ABBD");
    	catAD.setDescription("ABBIGLIAMNTO");
    	catAD = categoryFacade.create(catAD);
    	categoryFacade.addCategoryParent( catAD.getUuid(), catD.getUuid());
    	
    	CategoryData catBL = new CategoryData();
    	catBL.setCode("BLUES");
    	catBL.setDescription("BLUES");
    	catBL.setUrl("http://prova cateogrty4");
    	categoryFacade.create(catBL, catAD.getUuid());
    	
    	
    	
    	
    }
    
    private void listCategory() {
    	Page<CategoryData> p= categoryFacade.listCategory(new PageRequest(0, 10));
    	Assert.assertNotNull("Category not found", p);
    	log.info("Number category " +  p.getTotalElements());
    	
    	CategoryData cat = categoryFacade.findCategoryByCode("UOMO");
    	List<CategoryData> childU = categoryFacade.listChildCategories(cat.getUuid());
    	Assert.assertNotNull("SUB UOMO NULL", childU);
    	List<CategoryData> categories = categoryFacade.listTopCategory();
    	Assert.assertNotNull("Empty top categories", categories);
    	log.info("** top cateogries " + categories.size());
    	for (CategoryData categoryData : categories) {
    		log.info("TOP: " + categoryData.getCode() + " - " + categoryData.getDescription());
    		
    		List<CategoryData> subCategories = categoryFacade.listChildCategories(categoryData.getUuid());
    		if(subCategories != null) {
    			for (CategoryData subCategory : subCategories) {
    				log.info("|--- " + subCategory.getCode() + " - " + subCategory.getDescription());
    				
				}
    		}
		}
    	
    }
    
    private void createProduct() {
    	BrandData b = new BrandData();
    	b.setName("prodRANC");
    	b = brandFacade.addBrand(b);
    	
    	
    	
    	CategoryData c = categoryFacade.findCategoryByCode("BLUES");
    	CategoryData c2 = categoryFacade.findCategoryByCode("ABBD");
    	
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
    	entry  = productFacade.create(entry);
    	productFacade.addEntryCategoryParent( entry.getUuid(), c.getUuid());
    	
    	
    	
    	PriceData price = new PriceData();
    	price.setCurrentPrice(10.00);
    /*	price.setMinQty(1);
    	price.setProductCost(5.50);
    	price.setProductPrice(8.50);*/
    	price.setPrecedence(1);
    	productFacade.addPrice(entry.getUuid(), price);
    	
    	MediaData media = new MediaData();
    	media.setSrc("prova.jpg");
    	media.setName("imgthmb");
    	media.setSequence(1);
    	productFacade.addMedia(entry.getUuid(),media);
    	
    	entry = new ProductData();
    	entry.setCode("PROD2");
    	entry.setDescription("PRODOTTO DI TEST NUMERO 2");
    	entry.setBuyable(1);
    	entry.setPublished(1); 
    	entry = productFacade.create(entry,c.getUuid());
    	
    	productFacade.addEntryCategoryParent( entry.getUuid(), c2.getUuid());
    	
    	 price = new PriceData();
     	price.setCurrentPrice(20.00);
     	price.setPrecedence(1);
     	productFacade.addPrice(entry.getUuid(), price);
     	
     	MediaData media1 = new MediaData();
    	media1.setSrc("prova.jpg");
    	media1.setName("imgthmb");
    	media1.setSequence(1);
     	productFacade.addMedia(entry.getUuid(),media1);
    }
    
    private void listProduct() {
    	Page<BrandData> lb = brandFacade.findAll(new PageRequest(0, 10));
    	Page<ProductData> p = productFacade.listProduct(new PageRequest(0, 10));
    	Assert.assertNotNull("Product not found", p);
    	log.info("Number product " +  p.getTotalElements());
    	Page<CategoryData> l= categoryFacade.listCategory(new PageRequest(0, 10));
    	CategoryData category = categoryFacade.findCategoryByCode("BLUES");
    	Page<ProductData> lp = productFacade.listProductByCategory(category.getUuid(), new PageRequest(0, 10));
    	Assert.assertNotNull("list product is null", lp);
    	log.info("Number product " + lp.getTotalElements() + " in category " + category.getCode());
    	
    	for (ProductData productData : lp) {
			log.info("product " + productData.getCode() + " - " + productData.getDescription() + " - ") ;
			
			Set<MediaData> medias = productData.getMedia();
			log.info("TOTALE MEDIA " + medias.size());
			
			Assert.assertNotNull("price is null");
			if(productData.getPrices() != null) log.info("Current price" +productData.getPrices().getCurrentPrice());
			
			if(productData.getCategories() != null) {
				Set<CategoryData> listParentCategory = productData.getCategories();
				log.info("parent categories " + listParentCategory.size());
			}
			List<CategoryData> cprd = productFacade.getParentCategory(productData);
			Assert.assertNotNull("List category parent null", cprd);
			log.info("GET PARENT " + cprd.size());
		}
    }
    
    
    private void updateProduct() {
    	ProductData product = productFacade.findByName("PROD1");
    	product.setUnitMeas("L");
    	productFacade.updateProduct(product);
    }
    
    private void deleteProduct() {
    	ProductData product = productFacade.findByName("PROD1");
    	String productUuid = product.getUuid();
    	productFacade.deleteProduct(productUuid);
    	log.info("*** ELIMINATO ?");
    	//Page<ProductData> p = productFacade.listProduct(new PageRequest(0, 10));
    //	log.info("*** ELIMINATO ? " + p.getTotalElements());
    }
    
}
