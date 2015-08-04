package com.serpics.catalog.facade;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.data.model.MultilingualString;
import com.serpics.catalog.data.model.Brand;
import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.Ctentry;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.facade.data.CategoryData;
import com.serpics.catalog.facade.data.CtentryData;
import com.serpics.catalog.facade.data.ProductData;
import com.serpics.catalog.services.BrandService;
import com.serpics.catalog.services.CategoryService;
import com.serpics.catalog.services.ProductService;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.Engine;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.stereotype.StoreFacade;

@StoreFacade("ctentryFacade")
@Transactional(readOnly=true)
public class CtentryFacadeImpl implements CtentryFacade {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	BrandService brandService;
	
	@Resource(name="ctentryConverter")
	AbstractPopulatingConverter<Ctentry, CtentryData> ctentryConverter;
	
	@Resource(name="categoryConverter")
	AbstractPopulatingConverter<Category, CategoryData> categoryConverter;
	
	@Resource(name="productConverter")
	AbstractPopulatingConverter<Product, ProductData> productConverter;
	
	@Autowired
	Engine<CommerceSessionContext> engine;
	@Override
	public Page<CategoryData> listCategory(Pageable page) {
		Page<Category> categories = categoryService.findAll(page);
		List<CategoryData> l = new ArrayList<CategoryData>();
		for (Category category : categories.getContent()) {
			l.add(categoryConverter.convert(category));
		}
		Page<CategoryData> list = new PageImpl<CategoryData>(l, page, categories.getTotalElements());
		return list;
	}
	
	
	@Override 
	public CategoryData findCategoryByCode(final String code){ 
	Category category = categoryService.findOne(new Specification<Category>() {
            @Override
            public Predicate toPredicate(final Root<Category> root, final CriteriaQuery<?> query,
                    final CriteriaBuilder cb) {
            	Expression<String> e = root.get("code");
            		
                return cb.like(	e, code);
            }
		});
		CategoryData entity = categoryConverter.convert(category);
		return entity;
	}
	
	
	@Override
	public CategoryData addCategory(CategoryData category) {
		Category  entity = buildCategory(category);
		entity = categoryService.create(entity); 
		category = categoryConverter.convert(entity);
		return category;
	}
	
	@Override
	public CategoryData addCategory(CategoryData category, String parentUuid) {
		Category parent = categoryService.findByUUID(parentUuid);
		Category entity = buildCategory(category);
		entity = categoryService.create(entity, parent);
		category = categoryConverter.convert(entity);
		return category;
	}
	
	private Category buildCategory(CategoryData category){
		Category entity = new Category();
		entity.setCode(category.getCode());
		entity.setUrl(category.getUrl());
		return entity;
	}
	
	
	public void addCategoryParent(String parentUui, String childUuid) {
		Category parent = categoryService.findByUUID(parentUui);
		Category child = categoryService.findByUUID(childUuid);
		
		Assert.assertNotNull("parent not found", parent);
		Assert.assertNotNull("child not found", child);
		
		categoryService.addRelationCategory(child, parent);
	}
	
	@Override
	public ProductData addProduct(ProductData product) {
		Product entity = buildProduct(product);
		entity = productService.create(entity);
		product = productConverter.convert(entity);
		return product;
	}
	
	@Override
	public ProductData addProduct(ProductData product, String parentUuid) {
		Category parent = categoryService.findByUUID(parentUuid);
		Product entity = buildProduct(product);
		entity = productService.create(entity, parent);
		product = productConverter.convert(entity);
		return product;
	}
	
	private Product buildProduct(ProductData product) {
		Brand b = null;
		if(product.getBrand() != null)
			b = brandService.findOne(product.getBrand().getId());
		String locale = "it";
		//if(engine.getCurrentContext() != null) locale = engine.getCurrentContext().getLocale().getLanguage();
		final MultilingualString description = new MultilingualString(locale, product.getDescription());
		Product entity = new Product();
		entity.setCode(product.getCode());
		entity.setDescription(description);
		entity.setUrl(product.getUrl());

		entity.setBuyable(product.getBuyable());
		//entity.setDownlodable(product.getDowloadable());
		entity.setManufacturerSku(product.getManufacturSku());
		entity.setPublished(product.getPublished());
		entity.setUnitMeas(product.getUnitMeas());
		entity.setWeight(product.getWeight());
		entity.setWeightMeas(product.getWeightMeas());
		//entity.setPrices(p);
		entity.setBrand(b);
		entity.setMetaDescription(product.getMetaDescription());
		entity.setMetaKeyword(product.getMetaKey());
		return entity;
	}
	
	
	public void addEntryCategoryParent(String ctentryUuid, String categoryUuid) {
		Assert.assertNotNull("Entry is null", ctentryUuid);
		Assert.assertNotNull("Category is null", categoryUuid);
		Product product = productService.findByUUID(ctentryUuid);
		Category category = categoryService.findByUUID(categoryUuid);
		
		productService.addParentCategory(product, category);
	}
	
	
	@Override
	public Page<ProductData> listProduct(Pageable page) {
		Page<Product> products = productService.findAll(page);
		List<ProductData> l = new ArrayList<ProductData>();
		for (Product product : products.getContent()) {
			l.add(productConverter.convert(product));
		}
		Page<ProductData> list = new PageImpl<ProductData>(l, page, products.getTotalElements());
		return list;
	}
	
	
	public Page<ProductData> listProductByCategory(final String cUuid,Pageable page) {
		Category category = categoryService.findByUUID(cUuid);
		List<Product> products = productService.findProductByCategory(category);
		List<ProductData> l = new ArrayList<ProductData>();
		for (Product product : products) {
			l.add(productConverter.convert(product));
		}
		Page<ProductData> list = new PageImpl<ProductData>(l, page, products.size());
		return list;
	}
}
