package com.serpics.catalog.facade;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.base.data.model.MultilingualText;
import com.serpics.base.facade.data.MediaData;
import com.serpics.catalog.data.model.Product;
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
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.Engine;
import com.serpics.core.facade.AbstractPopulatingConverter;
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
			product = priceService.addPrice(product, price);
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
	public Page<ProductData> listProductByCategory(final Long categoryId,Pageable page) {
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
	public Page<ProductData> listProductByBrand(Long brandId, Pageable page){
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
	@Transactional
	public void addMedia(Long productId, MediaData mediaData) {
		Product product = productService.findOne(productId);
		CtentryMedia media = new CtentryMedia();
		media.setCtentry(product);
		media.setName(mediaData.getName());
		media.setSource(mediaData.getSrc());
		media = mediaService.create(media);
		product = productService.addMedia(product, media);
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

		destination.setBuyable(source.getBuyable());
		//entity.setDownlodable(product.getDowloadable());
		destination.setManufacturerSku(source.getManufacturSku());
		destination.setPublished(source.getPublished());
		destination.setUnitMeas(source.getUnitMeas());
		destination.setWeight(source.getWeight());
		destination.setWeightMeas(source.getWeightMeas());
		//entity.setPrices(p);
		destination.setBrand(b);
		destination.setMetaDescription(source.getMetaDescription());
		destination.setMetaKeyword(source.getMetaKey());
		return destination;
	}
	
}
