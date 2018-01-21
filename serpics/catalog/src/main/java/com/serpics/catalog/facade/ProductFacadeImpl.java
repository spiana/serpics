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
package com.serpics.catalog.facade;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.base.commerce.session.CommerceSessionContext;
import com.serpics.base.facade.data.MediaData;
import com.serpics.catalog.data.model.Brand;
import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.Ctentry;
import com.serpics.catalog.data.model.CtentryMedia;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.facade.data.CategoryData;
import com.serpics.catalog.facade.data.CtentryData;
import com.serpics.catalog.facade.data.PriceData;
import com.serpics.catalog.facade.data.ProductData;
import com.serpics.catalog.services.BrandService;
import com.serpics.catalog.services.CatalogMediaService;
import com.serpics.catalog.services.CategoryService;
import com.serpics.catalog.services.PriceService;
import com.serpics.catalog.services.ProductService;
import com.serpics.core.Engine;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.i18n.data.model.MultilingualString;
import com.serpics.i18n.data.model.MultilingualText;
import com.serpics.stereotype.StoreFacade;

@StoreFacade("productFacade")
@Transactional(readOnly=true)
public class ProductFacadeImpl implements ProductFacade {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CatalogMediaService mediaService;
	
	@Autowired
	BrandService brandService;
	
	@Autowired
	PriceService priceService;
	
	@Resource(name="ctentryConverter")
	AbstractPopulatingConverter<Ctentry, CtentryData> ctentryConverter;
	
	@Resource(name="categoryConverter")
	AbstractPopulatingConverter<Category, CategoryData> categoryConverter;
	
	@Resource(name="productConverter")
	AbstractPopulatingConverter<Product, ProductData> productConverter;
	
	
	
	@Autowired
	Engine<CommerceSessionContext> engine;
	
	
	
	@Transactional
	public void addCategoryParent(Long parentId, Long childId) {
		Category parent = categoryService.findOne(parentId);
		Category child = categoryService.findOne(childId);
		
		Assert.notNull(parent,"parent not found");
		Assert.notNull(child,"child not found");
		
		categoryService.addRelationCategory(child, parent);
	}
	
	@Override
	@Transactional
	public ProductData create(ProductData product) {
		Product entity = buildProduct(product, new Product());
		entity = productService.create(entity);
		product = productConverter.convert(entity);
		return product;
	}
	
	@Override
	@Transactional
	public ProductData create(ProductData product, Long parentId, Long brandId) {
		Category parent = categoryService.findOne(parentId);
		Brand brand = brandService.findOne(brandId);
		Product entity = buildProduct(product, new Product());
		entity = productService.create(entity, parent, brand);
		product = productConverter.convert(entity);
		return product;
	}
	
	@Override
	@Transactional
	public ProductData createWithCategory(ProductData product, Long parentId) {
		Category parent = categoryService.findOne(parentId);
		Product entity = buildProduct(product, new Product());
		entity = productService.create(entity, parent, null);
		product = productConverter.convert(entity);
		return product;
	}
	
	@Override
	@Transactional
	public ProductData createWithBrand(ProductData product, Long brandId) {
		Brand brand = brandService.findOne(brandId);
		Product entity = buildProduct(product, new Product());
		entity = productService.create(entity, null, brand);
		product = productConverter.convert(entity);
		return product;
	}
	
	@Override 
	@Transactional
	public ProductData updateProduct(ProductData product) {
		Product entity = productService.findOne(product.getId());
		entity = buildProduct(product, entity);
		entity =productService.update(entity);
		product = productConverter.convert(entity);
		return product;
	}
	
	@Override 
	@Transactional
	public void deleteProduct(Long id) {
		productService.delete(id);
	}
	
	@Transactional
	public void addEntryCategoryParent(Long ctentryId, Long categoryId) {
		Assert.notNull(ctentryId,"Entry is null");
		Assert.notNull(categoryId,"Category is null");
		Product product = productService.findOne(ctentryId);
		Category category = categoryService.findOne(categoryId);
		
		productService.addParentCategory(product, category);
	}
	
	@Transactional
	public void  addPrice(Long  entryId, PriceData priceData) {
			Product product = productService.findOne(entryId);
			Price price = new Price();
			price.setCurrentPrice(priceData.getCurrentPrice());
			price.setMinQty(new Double(priceData.getMinQty()).doubleValue());
			price.setPrecedence(new Double(priceData.getPrecedence()).doubleValue());
			price.setProductPrice(priceData.getProductPrice());
			price.setProductCost(priceData.getProductCost());
			product = (Product)priceService.addPrice(product, price);
	}
	
	
	public List<CategoryData> getParentCategory(ProductData productData) {
		Product product = productService.findOne(productData.getId());
		List<Category> list = categoryService.getCategoriesByProduct(product);
		List<CategoryData> categories = new ArrayList<CategoryData>();
		for (Category category : list) {
			categories.add(categoryConverter.convert(category));
		}
		return categories;
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
	
	@Override
	public Page<ProductData> searchProducts(Pageable page, String searchText) {
		Page<Product> products = productService.findProductsBySearch(searchText, page);
		List<ProductData> l = new ArrayList<ProductData>();
		for (Product product : products.getContent()) {
			l.add(productConverter.convert(product));
		}
		Page<ProductData> list = new PageImpl<ProductData>(l, page, products.getTotalElements());
		return list;
	}
	
	@Override
	public Page<ProductData> pageProductByCategoryId(final Long categoryId,Pageable page) {
		Category category = categoryService.findOne(categoryId);
		List<ProductData> l = new ArrayList<ProductData>();
		long totalElements = 0 ;
		if(category!=null){
			Page<Product> products = productService.findProductByCategory(category,page);
			
			totalElements = products.getTotalElements();
			
			for (Product product : products.getContent()) {
				l.add(productConverter.convert(product));
			}
		}
		
		Page<ProductData> list = new PageImpl<ProductData>(l, page, totalElements);
		
		return list; 
	}
	
	@Override
	public Page<ProductData> pageProductByCategoryCode(final String categoryCode,Pageable page) {
		Category category = categoryService.findByCode(categoryCode);
		List<ProductData> l = new ArrayList<ProductData>();
		long totalElements = 0 ;
		if(category!=null){
			Page<Product> products = productService.findProductByCategory(category,page);
			
			totalElements = products.getTotalElements();
			
			for (Product product : products.getContent()) {
				l.add(productConverter.convert(product));
			}
		}
		
		Page<ProductData> list = new PageImpl<ProductData>(l, page, totalElements);
		
		return list; 
	}
	
	@Override
	public Page<ProductData> pageProductByBrandId(Long brandId, Pageable page){
		Brand brand = brandService.findOne(brandId);
		List<ProductData> productDataList = new ArrayList<ProductData>();
		long totalElements = 0 ;
		if(brand!=null){
			Page<Product> products = productService.findProductByBrand(brand, page);
			
			totalElements = products.getTotalElements();
			
			for (Product product : products.getContent()) {
				productDataList.add(productConverter.convert(product));
			}
		}		
		Page<ProductData> pageProduct = new PageImpl<ProductData>(productDataList, page, totalElements);
		return pageProduct;
	}
	
	@Override
	public Page<ProductData> pageProductByBrandCode(String brandCode, Pageable page){
		Brand brand = brandService.findOneByCode(brandCode);
		List<ProductData> productDataList = new ArrayList<ProductData>();
		long totalElements = 0 ;
		if(brand!=null){
			Page<Product> products = productService.findProductByBrand(brand, page);
			
			totalElements = products.getTotalElements();
			
			for (Product product : products.getContent()) {
				productDataList.add(productConverter.convert(product));
			}
		}		
		Page<ProductData> pageProduct = new PageImpl<ProductData>(productDataList, page, totalElements);
		return pageProduct;
	}
	
	@Override
	public ProductData findByName(final String name) {
		Product product = productService.findByName(name);
		ProductData p = null;
		if(product !=null){
			p = productConverter.convert(product);
		}
		return p; 
	}
	
	@Override
	public ProductData findById(final Long id) {
		Product product = productService.findOne(id);
		ProductData p = null;
		if(product !=null){
			p = productConverter.convert(product);
		}
		return p; 
	}
	
	@Override
	public ProductData findByCode(final String code) {
		Product product = productService.findByCode(code);
		ProductData p = null;
		if(product !=null){
			p = productConverter.convert(product);
		}
		return p; 
	}

	@Override
	@Transactional
	public void addMedia(Long productId, MediaData mediaData) {
		Product product = productService.findOne(productId);
		CtentryMedia media = new CtentryMedia();
		media.setCtentry(product);
		media.setName(mediaData.getName());
		media.setSource(mediaData.getSource());
		try{
			media = mediaService.create(media );
			product = productService.addMedia(product, media);
		}catch(IOException e){
			//TODO:at least log an error
			;
		}
	}
	
	@Override
	@Transactional
	public ProductData addBrand(Long productId, Long brandId){
		Assert.notNull(productId);
		Assert.notNull(brandId);
		Product product = productService.findOne(productId);
		Brand brand = brandService.findOne(brandId);		
		product = productService.addBrand(product, brand);
		ProductData productData = null;
		if(product !=null){
			productData = productConverter.convert(product);
		}
		return productData; 
	}
	
	/**
	 * Convert ProductData into Product entity for saving
	 * @param source
	 * @param destination
	 * @return
	 */
	protected Product buildProduct(ProductData source, Product destination) {
		Brand b = null;
		if(source.getBrand() != null)
			b = brandService.findOne(source.getBrand().getId());
		
//		String locale = "it";
		String locale = engine.getCurrentContext().getLocale().getLanguage();
		final MultilingualText description = new MultilingualText(locale, source.getDescription());
		destination.setCode(source.getCode());
		destination.setDescription(description);
		destination.setUrl(source.getUrl());

		destination.setBuyable(source.isBuyable());
		//entity.setDownlodable(product.getDowloadable());
		destination.setManufacturerSku(source.getManufacturSku());
		destination.setPublished(source.isPublished());
		destination.setUnitMeas(source.getUnitMeas());
		destination.setWeight(source.getWeight());
		destination.setWeightMeas(source.getWeightMeas());
		//entity.setPrices(p);
		destination.setBrand(b);
		destination.setMetaDescription(new MultilingualString(locale, source.getMetaDescription()));
		destination.setMetaKeyword(new MultilingualString(locale, source.getMetaKey()));
		destination.setName(new MultilingualString(locale, source.getName()));
		return destination;
	}

	@Override
	public List<ProductData> listProductByCategoryId(Long categoryId) {
		Category category = categoryService.findOne(categoryId);
		List<ProductData> list = new ArrayList<ProductData>();
		if(category!=null){
			List<Product> products = productService.listProductByCategory(category);
			
			for (Product product : products) {
				list.add(productConverter.convert(product));
			}
		}
		return list; 
	}

	@Override
	public List<ProductData> listProductByCategoryCode(String categoryCode) {
		Category category = categoryService.findByCode(categoryCode);
		List<ProductData> list = new ArrayList<ProductData>();
		if(category!=null){
			List<Product> products = productService.listProductByCategory(category);
			
			for (Product product : products) {
				list.add(productConverter.convert(product));
			}
		}
		return list; 
	}

	@Override
	public List<ProductData> listProductByBrandId(Long brandId) {
		Brand brand = brandService.findOne(brandId);
		List<ProductData> productDataList = new ArrayList<ProductData>();
		if(brand!=null){
			List<Product> products = productService.listProductByBrand(brand);
			
			for (Product product : products) {
				productDataList.add(productConverter.convert(product));
			}
		}		
		return productDataList;
	}

	@Override
	public List<ProductData> listProductByBrandCode(String brandCode) {
		Brand brand = brandService.findOneByCode(brandCode);
		List<ProductData> productDataList = new ArrayList<ProductData>();
		if(brand!=null){
			List<Product> products = productService.listProductByBrand(brand);
			
			for (Product product : products) {
				productDataList.add(productConverter.convert(product));
			}
		}		
		return productDataList;
	}
	
}
