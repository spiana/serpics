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

import com.serpics.base.data.model.MultilingualString;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.data.model.Brand;
import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.Ctentry;
import com.serpics.catalog.data.model.Media;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.data.repositories.ProductSpecification;
import com.serpics.catalog.facade.data.CategoryData;
import com.serpics.catalog.facade.data.CtentryData;
import com.serpics.catalog.facade.data.MediaData;
import com.serpics.catalog.facade.data.PriceData;
import com.serpics.catalog.facade.data.ProductData;
import com.serpics.catalog.services.BrandService;
import com.serpics.catalog.services.CategoryService;
import com.serpics.catalog.services.MediaService;
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
	MediaService mediaService;
	
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
	public void addCategoryParent(String parentUui, String childUuid) {
		Category parent = categoryService.findByUUID(parentUui);
		Category child = categoryService.findByUUID(childUuid);
		
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
	public ProductData create(ProductData product, String parentUuid) {
		Category parent = categoryService.findByUUID(parentUuid);
		Product entity = buildProduct(product, new Product());
		entity = productService.create(entity, parent);
		product = productConverter.convert(entity);
		return product;
	}
	
	@Override 
	@Transactional
	public ProductData updateProduct(ProductData product) {
		Product entity = productService.findByUUID(product.getUuid());
		entity = buildProduct(product, entity);
		entity =productService.update(entity);
		product = productConverter.convert(entity);
		return product;
	}
	
	@Override 
	@Transactional
	public void deleteProduct(String uuid) {
		Product entity = productService.findByUUID(uuid);
		productService.delete(entity);
	}
	
	
	private Product buildProduct(ProductData source, Product destination) {
		Brand b = null;
		if(source.getBrand() != null)
			b = brandService.findOne(source.getBrand().getId());
		String locale = "it";
		//if(engine.getCurrentContext() != null) locale = engine.getCurrentContext().getLocale().getLanguage();
		final MultilingualString description = new MultilingualString(locale, source.getDescription());
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
	
	@Transactional
	public void addEntryCategoryParent(String ctentryUuid, String categoryUuid) {
		Assert.notNull(ctentryUuid,"Entry is null");
		Assert.notNull(categoryUuid,"Category is null");
		Product product = productService.findByUUID(ctentryUuid);
		Category category = categoryService.findByUUID(categoryUuid);
		
		productService.addParentCategory(product, category);
	}
	
	@Transactional
	public void  addPrice(String  entryId, PriceData priceData) {
			AbstractProduct product = productService.findByUUID(entryId);
			Price price = new Price();
			price.setCurrentPrice(priceData.getCurrentPrice());
			price.setMinQty(new Double(priceData.getMinQty()).doubleValue());
			price.setPrecedence(new Double(priceData.getPrecedence()).doubleValue());
			price.setProductPrice(priceData.getProductPrice());
			price.setProductCost(priceData.getProductCost());
			product = priceService.addPrice(product, price);
	}
	
	
	public List<CategoryData> getParentCategory(ProductData productData) {
		Product product = productService.findByUUID(productData.getUuid());
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
	
	
	public ProductData findByName(final String name) {
		Product product = productService.findOne(ProductSpecification.findByName(name));
		ProductData p = productConverter.convert(product);
		return p; 
	}

	@Override
	@Transactional
	public void addMedia(String productUuid, MediaData mediaData) {
		Product product = productService.findByUUID(productUuid);
		Media media = new Media();
		media.setCtentry(product);
		media.setName(mediaData.getName());
		media.setSrc(mediaData.getSrc());
		media = mediaService.create(media);
		product = productService.addMedia(product, media);
	}
	
	
}
