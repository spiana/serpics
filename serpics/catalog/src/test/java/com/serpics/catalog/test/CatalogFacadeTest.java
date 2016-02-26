package com.serpics.catalog.test;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.facade.data.MediaData;
import com.serpics.catalog.facade.BrandFacade;
import com.serpics.catalog.facade.CategoryFacade;
import com.serpics.catalog.facade.PriceFacade;
import com.serpics.catalog.facade.ProductFacade;
import com.serpics.catalog.facade.data.BrandData;
import com.serpics.catalog.facade.data.CategoryData;
import com.serpics.catalog.facade.data.PriceData;
import com.serpics.catalog.facade.data.ProductData;
import com.serpics.catalog.services.CatalogService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.membership.services.BaseService;


@TransactionConfiguration(defaultRollback = true)
public class CatalogFacadeTest  extends CatalogBaseTest{
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

    private HashMap<String,CategoryData> listOfCategoriesForTest = new HashMap<String,CategoryData>();
    long categoryInit;
    long brandInit;
    int topCategoriesInit;
    long productInit;
    
    @Before
    public void setUp() throws SerpicsException{
    	super.beforeTest();
    	categoryInit = categoryFacade.listCategory(new PageRequest(0, 10)).getTotalElements();
    	brandInit = brandFacade.pageBrand(new PageRequest(0, 10)).getTotalElements();
    	topCategoriesInit = categoryFacade.listTopCategory().size();
    	productInit = productFacade.listProduct(new PageRequest(0, 10)).getTotalElements();
    	createCategory();
    	createProduct();
    }
    
    @Test
    @Transactional
    public void testListCategory(){
    	Page<CategoryData> p= categoryFacade.listCategory(new PageRequest(0, 10));
    	Assert.assertNotNull("Category not found", p);

    	Assert.assertEquals("Number of categories", categoryInit + listOfCategoriesForTest.values().size(), p.getTotalElements());
    	
    	CategoryData cat = categoryFacade.findCategoryByCode("UOMO");
    	Assert.assertEquals("Id of category UOMO",listOfCategoriesForTest.get("UOMO").getId(),cat.getId());
    	Assert.assertEquals("Url of category UOMO",listOfCategoriesForTest.get("UOMO").getUrl(),cat.getUrl());
    	Assert.assertEquals("UUID of category UOMO",listOfCategoriesForTest.get("UOMO").getUuid(),cat.getUuid());
    	
    	List<CategoryData> childU = categoryFacade.listChildCategories(cat.getId());
    	Assert.assertNotNull("SUB UOMO NULL", childU);
    	Assert.assertEquals("Number of childs", 1, childU.size());
    	
    	Assert.assertEquals("Child of category UOMO", listOfCategoriesForTest.get("ABB").getUuid(),childU.get(0).getUuid());
    	
    	List<CategoryData> categories = categoryFacade.listTopCategory();
    	Assert.assertNotNull("Empty top categories", categories);
    	Assert.assertEquals("Number of top categories", topCategoriesInit + 2, categories.size());
    	
    }
    
    @Test
    @Transactional
    public void testProductByCategory(){
    	CategoryData c = categoryFacade.findCategoryByCode("BLUES");
    	Page<ProductData> list = productFacade.listProductByCategory(c.getId(), new PageRequest(0, 10));
    	
    	Assert.assertEquals(2, list.getNumberOfElements());
    	
    	list = productFacade.listProductByCategory(c.getId(), new PageRequest(0, 1));
    	Assert.assertEquals(1, list.getNumberOfElements());
    }
    
    @Test
    @Transactional
    public void testListProduct() {
    	Page<BrandData> lb = brandFacade.pageBrand(new PageRequest(0, 10));
    	Assert.assertEquals("Number of brand", brandInit + 1, lb.getTotalElements());
    	
    	Page<ProductData> p = productFacade.listProduct(new PageRequest(0, 10));
    	Assert.assertNotNull("Product not found", p);
    	Assert.assertEquals("Number of products", productInit + 2, p.getTotalElements());
    	
    	CategoryData category = categoryFacade.findCategoryByCode("BLUES");
    	Page<ProductData> lp = productFacade.listProductByCategory(category.getId(), new PageRequest(0, 10));
    	Assert.assertNotNull("list product is null", lp);
    	Assert.assertEquals("Number of products under category BLUES",2, lp.getTotalElements());
    	
    	ProductData productData = lp.iterator().next();
    	Assert.assertEquals("Product code in category BLUES", "PROD1", productData.getCode());
    	Assert.assertEquals("Product price in category BLUES", 10.00 , productData.getPrice().getCurrentPrice(),0);
    	Assert.assertEquals("Product media in category BLUES", "imgthmb", productData.getMedias().iterator().next().getName());
		
    	List<CategoryData> cprd = productFacade.getParentCategory(productData);
		Assert.assertNotNull("List category parent null", cprd);
    }
    
    
    private void createCategory() {
    	
    	listOfCategoriesForTest.clear();
    	
    	CategoryData catU = new CategoryData();
    	catU.setCode("UOMO");
    	catU.setDescription("UOMO");
    	catU.setUrl("http://prova cateogrty");
    	catU = categoryFacade.create(catU);
    	
    	listOfCategoriesForTest.put("UOMO",catU);
    	
    	CategoryData catA = new CategoryData();
    	catA.setCode("ABB");
    	catA.setDescription("ABBIGLIAMNTO");
    	catA.setUrl("http://prova cateogrty3");
    	catA = categoryFacade.create(catA,catU.getId());
    	
    	listOfCategoriesForTest.put("ABB",catA);
    	
    	CategoryData catB = new CategoryData();
     	catB.setCode("BOTTOM");
     	catB.setDescription("BOTTOM");
     	catB.setUrl("http://prova cateogrty5");
     	catB.setMetaDescription("meta bo");
     	catB.setMetaKeyword("bo");
     	catB = categoryFacade.create(catB, catA.getId());
     	
     	listOfCategoriesForTest.put("BOTTOM",catB);
     	
    	CategoryData catD = new CategoryData();
    	catD.setCode("DONNA");
    	catD.setDescription("DONNA");
    	catD.setUrl("http://prova cateogrty2");
    	catD = categoryFacade.create(catD);
    	
    	listOfCategoriesForTest.put("DONNA",catD);
    	
    	CategoryData catAD = new CategoryData();
    	catAD.setCode("ABBD");
    	catAD.setDescription("ABBIGLIAMNTO");
    	catAD = categoryFacade.create(catAD);
    	categoryFacade.addCategoryParent( catAD.getId(), catD.getId());
    	
    	listOfCategoriesForTest.put("ABBD",catAD);
    	
    	CategoryData catBL = new CategoryData();
    	catBL.setCode("BLUES");
    	catBL.setDescription("BLUES");
    	catBL.setUrl("http://prova cateogrty4");
    	categoryFacade.create(catBL, catAD.getId());
    	
    	listOfCategoriesForTest.put("BLUES",catBL);

    }
    
    private void createProduct() {
    	
    	BrandData b = new BrandData();
    	b.setCode("prodRANC");
    	b = brandFacade.addBrand(b);
    	
    	CategoryData c = categoryFacade.findCategoryByCode("BLUES");
    	CategoryData c2 = categoryFacade.findCategoryByCode("ABBD");
    	
    	ProductData entry = new ProductData();
    	entry.setCode("PROD1");
    	entry.setDescription("PRODOTTO DI TEST NUMERO 1");
    	entry.setBuyable(true);
    	entry.setPublished(true); 
    	entry.setDowloadable(true);
    	entry.setManufacturSku("0001P");
    	entry.setMetaDescription("MD PROD1");
    	entry.setMetaKey("MK PROD1");
    	entry.setUnitMeas("Q");
    	
    	entry.setUrl("http://www.test.it");
    	entry.setWeight(10.50);
    	entry.setWeightMeas("KG");
    	entry.setBrand(b);
    	entry  = productFacade.create(entry);
    	productFacade.addEntryCategoryParent( entry.getId(), c.getId());
    	
    	PriceData price = new PriceData();
    	price.setCurrentPrice(10.00);
    	price.setPrecedence(1);
    	productFacade.addPrice(entry.getId(), price);
    	
    	MediaData media = new MediaData();
    	media.setSource("prova.jpg");
    	media.setName("imgthmb");
    	media.setSequence(1);
    	productFacade.addMedia(entry.getId(),media);
    	
    	entry = new ProductData();
    	entry.setCode("PROD2");
    	entry.setDescription("PRODOTTO DI TEST NUMERO 2");
    	entry.setBuyable(true);
    	entry.setPublished(true); 
    	entry = productFacade.createWithCategory(entry,c.getId());
    	
    	productFacade.addEntryCategoryParent( entry.getId(), c2.getId());
    	
    	 price = new PriceData();
     	price.setCurrentPrice(20.00);
     	price.setPrecedence(1);
     	productFacade.addPrice(entry.getId(), price);
     	
     	MediaData media1 = new MediaData();
    	media1.setSource("prova.jpg");
    	media1.setName("imgthmb");
    	media1.setSequence(1);
     	productFacade.addMedia(entry.getId(),media1);
    }
    
    @Test
    @Transactional
    public void testUpdateProduct() {
    	ProductData product = productFacade.findByName("PROD1");
    	product.setUnitMeas("L");
    	productFacade.updateProduct(product);
    	product = productFacade.findByName("PROD1");
    	Assert.assertEquals("L", product.getUnitMeas());
    }
    
    //Rimuovere l'ignore dopo aver messo a posto i cascade sulle relazioni
    @Test

    @Transactional
    public void testDeleteProduct() {
    	ProductData product = productFacade.findByName("PROD1");
    	Long productUuid = product.getId();
    	productFacade.deleteProduct(productUuid);
    	product = productFacade.findByName("PROD1");
    	Assert.assertNull(product);
    }
    
}
