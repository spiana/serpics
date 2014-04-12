package com.serpics.catalog.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.AttributeType;
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
import com.serpics.catalog.services.CategoryService;
import com.serpics.catalog.services.ProductService;
import com.serpics.core.SerpicsException;


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
    BundleRepository bundleRepository;

    @Resource
    AbstractProductRepository abstractProductRepository;

    @Resource
    AttributeService attributeService;


    @Test
    public void AttributeTest() throws SerpicsException{

        final BaseAttribute attribute = new BaseAttribute();
        attribute.setAttributeType(AttributeType.INTEGER);
        attribute.setAvailablefor(AvailableforType.USER);
        attribute.setName("test");
        attributeService.create(attribute);

        final List<BaseAttribute> al = attributeService.findAll();
        Assert.assertEquals(1, al.size());

        baseService.createStore("test-store");
        context = commerceEngine.connect("test-store");

        final BaseAttribute attribute1 = new BaseAttribute();
        attribute1.setAttributeType(AttributeType.TEXT);
        attribute1.setAvailablefor(AvailableforType.USER);
        attribute1.setName("test");
        attributeService.create(attribute1);

        final BaseAttribute attribute2 = new BaseAttribute();
        attribute2.setAttributeType(AttributeType.TEXT);
        attribute2.setAvailablefor(AvailableforType.FEATURE);
        attribute2.setName("test1");
        attributeService.create(attribute2);

        final List<BaseAttribute> al1 = attributeService.findAll();
        Assert.assertEquals(2, al1.size());

        final List<BaseAttribute> al2 = attributeService.findbyAvailablefor(AvailableforType.FEATURE, new PageRequest(0, 10));
        Assert.assertEquals(1, al2.size());
    }


    @Test
    @Transactional
    public void test() throws SerpicsException {


        final List<Catalog> l = catalogRepository.findAll();
        Assert.assertEquals(1, l.size());

        final List<Catalog> _l = catalogRepository.findPublished();
        Assert.assertEquals(1, _l.size());


        Category category = new Category();
        category.setCode("main");
        category = categoryService.create(category);

        Category category1 = new Category();
        category1.setCode("main1");
        category1 = categoryService.create(category1, category);


        final Product p = new Product();
        p.setCode("test-sku");
        p.setCatalog((Catalog)context.getCatalog());
        p.setBuyable(1);
        productService.create(p);
        //
        // final Bundle b = new Bundle();
        // b.setCode("bundle-sku");
        // b.setCatalog(catalog);
        // b.setBuyable(1);
        // productService.create(b);

        final Product p1 = new Product();
        p1.setCode("test-sku");
        p1.setCatalog((Catalog)context.getCatalog());
        p1.setBuyable(1);


        final Bundle b1 = new Bundle();
        b1.setCode("bundle-sku");
        b1.setCatalog((Catalog)context.getCatalog());
        b1.setBuyable(1);
        //	b1.setPublished(1);

        final Product p2 = productRepository.findOne(productRepository.makeSpecification(p1));
        Assert.assertNotNull(p2);
        Assert.assertEquals("test-sku", p2.getCode());

        // final Bundle b2 = bundleRepository.findOne(bundleRepository.makeSpecification(b1));
        // Assert.assertNotNull(b2);
        // Assert.assertEquals("bundle-sku", b2.getCode());


        final AbstractProduct p3 = abstractProductRepository.findOne(abstractProductRepository.makeSpecification( (AbstractProduct) p1));
        Assert.assertNotNull(p3);
        Assert.assertEquals("test-sku", p3.getCode());



        // catalogService.deleteCatalogEntry(p3);

        final List<Category> l1 = categoryService.findRootCategory();
        Assert.assertEquals(1, l1.size());
        final List<Product> l2 = productRepository.findAll();
        Assert.assertEquals(1, l2.size());
        final List<AbstractProduct> l3 = abstractProductRepository.findAll();
        Assert.assertEquals(1, l3.size());
        // catalogService.deleteCatalog(catalog);

    }
}
