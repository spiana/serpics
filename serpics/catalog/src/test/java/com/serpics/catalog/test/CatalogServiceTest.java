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
package com.serpics.catalog.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.AvailableforType;
import com.serpics.base.data.model.BaseAttribute;
import com.serpics.base.data.repositories.BaseAttributeRepository;
import com.serpics.catalog.data.model.Catalog;
import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.data.repositories.CatalogRepository;
import com.serpics.catalog.data.repositories.ProductRepository;
import com.serpics.catalog.services.CatalogMediaService;
import com.serpics.catalog.services.CategoryService;
import com.serpics.catalog.services.PriceService;
import com.serpics.catalog.services.ProductService;
import com.serpics.core.SerpicsException;
import com.serpics.core.datatype.AttributeType;
import com.serpics.mediasupport.utils.MediaPathResolver;

public class CatalogServiceTest extends CatalogBaseTest {

    @Resource
    CategoryService categoryService;

    @Resource
    ProductService productService;

    @Resource
    CatalogRepository catalogRepository;

    @Resource
    ProductRepository productRepository;

  
    @Resource
    BaseAttributeRepository attributeRepository;
    
    @Resource
    CatalogMediaService catalogMediaService;

    @Autowired
    PriceService priceService;
    
    @Autowired
    MediaPathResolver mediaPathResolver;


 //   @Test
    public void AttributeTest() throws SerpicsException{

        final BaseAttribute attribute = new BaseAttribute();
        attribute.setAttributeType(AttributeType.INTEGER);
        attribute.setAvailablefor(AvailableforType.USER);
        attribute.setName("test");
        attributeRepository.save(attribute);

        final List<BaseAttribute> al = attributeRepository.findAll();
        Assert.assertEquals(1, al.size());

        baseService.createStore("test-store");
        commerceEngine.connect("test-store");

        final BaseAttribute attribute1 = new BaseAttribute();
        attribute1.setAttributeType(AttributeType.STRING);
        attribute1.setAvailablefor(AvailableforType.USER);
        attribute1.setName("test");
        attributeRepository.save(attribute1);

        final BaseAttribute attribute2 = new BaseAttribute();
        attribute2.setAttributeType(AttributeType.STRING);
        attribute2.setAvailablefor(AvailableforType.PRODUCT);
        attribute2.setName("test1");
        attributeRepository.save(attribute2);

        final List<BaseAttribute> al1 = attributeRepository.findAll();
        Assert.assertEquals(2, al1.size());

        final List<BaseAttribute> al2 = attributeRepository.findByAvailablefor(AvailableforType.PRODUCT);
        Assert.assertEquals(1, al2.size());
    }

    @Transactional
    @Test
    public void testMediaBasePath(){
    	Assert.assertEquals("/mediafolder", mediaPathResolver.getBaseMediaPath());
    }

    @Test
    @Transactional
    public void test() throws SerpicsException {
    	
        final List<Catalog> l = catalogRepository.findAll();
        Assert.assertEquals(1, l.size());

        final List<Catalog> _l = catalogRepository.findPublished();
        Assert.assertEquals(1, _l.size());
        
        int productInit = productRepository.findAll().size();
        int abstractProductInit = productRepository.findAll().size();
        int topCategoriesInit = categoryService.findRootCategory().size();

        Category category = new Category();
        category.setCode("main");
        category = categoryService.create(category);

        Category category1 = new Category();
        category1.setCode("main1");
        category1 = categoryService.create(category1, category);


        final Product p = new Product();
        p.setCode("test-sku");
        p.setBuyable(true);
        productService.create(p);
        
        final Product p0 = new Product();
        p0.setCode("test-sku-0");
        p0.setBuyable(false);
        productService.create(p0);

        final Price price = new Price();
        price.setProduct(p);
        price.setCurrentPrice(10.0);
        priceService.create(price);
        
        final Price price1 = new Price();
        price1.setCurrentPrice(9.0);
        price1.setPrecedence(1.0);
        priceService.addPrice(p, price1);
        
        

        productRepository.detach(p);

  
        final Product p1 = new Product();
        p1.setCode("test-sku");
        p1.setCatalog((Catalog) context.getCatalog());
        p1.setBuyable(true);


      
        //	b1.setPublished(1);



//        final Product p2 = productRepository.findOne(productRepository.makeSpecification(p1));
//        Assert.assertNotNull(p2);
//        Assert.assertEquals("test-sku", p2.getCode());
//        final List<Price> prices = priceService.findValidPricesforProduct(p2);
//        Assert.assertEquals(2, prices.size());

//        final Price _price = priceService.findProductPrice(p2);
//        Assert.assertEquals(new Double(9.0), _price.getCurrentPrice());
        // final Bundle b2 = bundleRepository.findOne(bundleRepository.makeSpecification(b1));
        // Assert.assertNotNull(b2);
        // Assert.assertEquals("bundle-sku", b2.getCode());


//        final AbstractProduct p3 = abstractProductRepository.findOne(abstractProductRepository.makeSpecification( (AbstractProduct) p1));
//        Assert.assertNotNull(p3);
//        Assert.assertEquals("test-sku", p3.getCode());



        // catalogService.deleteCatalogEntry(p3);

        final List<Category> l1 = categoryService.findRootCategory();
        Assert.assertEquals(topCategoriesInit + 1, l1.size());
        
        final List<Product> l2 = productRepository.findAll();
        Assert.assertEquals(productInit + 2, l2.size());
        
        final List<Product> l3 = productRepository.findAll();
        Assert.assertEquals(abstractProductInit + 2, l3.size());
        
        commerceEngine.connect("default-store");
        catalogService.initialize();
        
        final List<Product> l4 = productRepository.findAll();
        Assert.assertEquals(productInit + 1, l4.size());
        
        final List<Product> l5 = productRepository.findAll();
        Assert.assertEquals(abstractProductInit + 1, l5.size());
        
        // catalogService.deleteCatalog(catalog);
    }

}
