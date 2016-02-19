package com.serpics.catalog.facade;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.serpics.base.data.model.Media;
import com.serpics.base.data.model.TaxCategory;
import com.serpics.base.facade.data.MediaData;
import com.serpics.base.facade.data.TaxCategoryData;
import com.serpics.catalog.PriceNotFoundException;
import com.serpics.catalog.data.model.Brand;
import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.CategoryProductRelation;
import com.serpics.catalog.data.model.CtentryMedia;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.facade.data.BrandData;
import com.serpics.catalog.facade.data.CategoryData;
import com.serpics.catalog.facade.data.PriceData;
import com.serpics.catalog.facade.data.ProductData;
import com.serpics.catalog.services.PriceService;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.core.facade.Populator;

public class ProductPopulator implements Populator<Product, ProductData> {
	private AbstractPopulatingConverter<Brand, BrandData> brandConverter;
	private AbstractPopulatingConverter<Price, PriceData> priceConverter;
	private AbstractPopulatingConverter<Media, MediaData> mediaConverter;
	private AbstractPopulatingConverter<Category, CategoryData> categoryConverter;
	private AbstractPopulatingConverter<TaxCategory, TaxCategoryData> taxcategoryConverter;
	
	private static Logger LOG = LoggerFactory.getLogger(ProductPopulator.class);
	
	@Autowired
	PriceService priceService;
	
	@Override
	public void populate(Product source, ProductData target) {
		
		target.setBuyable(source.isBuyable());
		//target.setDowloadable(source.getDownlodable());
		target.setManufacturSku(source.getManufacturerSku());
		target.setPublished(source.isPublished());
		target.setUnitMeas(source.getUnitMeas());
		target.setWeight(source.getWeight());
		target.setWeightMeas(source.getWeightMeas());
		
		try {
			Price price = priceService.findProductPrice(source);
			target.setPrice(priceConverter.convert(price));
		} catch (PriceNotFoundException e) {
			LOG.warn("Price not found for product {}", source.getCode());
		}
		
		if(source.getBrand() != null)
			target.setBrand(brandConverter.convert(source.getBrand()));
		
		if(source.getMedias() != null) {
			Set<MediaData> medias = new HashSet<MediaData>();
			Set<CtentryMedia> list = source.getMedias();
			for (Media media : list) {
				medias.add(mediaConverter.convert(media));
			}
			target.setMedias(medias);
		}
		
		
		if(source.getCategories() != null) {
			Set<CategoryData> categories = new HashSet<CategoryData>();
			Set<CategoryProductRelation> categoriesrelation = source.getCategories();
			for (CategoryProductRelation categoryProductRelation : categoriesrelation) {
				Category category = categoryProductRelation.getParentCategory();
				categories.add(categoryConverter.convert(category));
			}
			target.setCategories(categories);
		}
			
		if (source.getTaxcategory() != null){
			target.setTaxCategory(taxcategoryConverter.convert(source.getTaxcategory()));
		}
		
		
		
	}

		@Required
		public void setBrandConverter(AbstractPopulatingConverter<Brand, BrandData> brandConverter) {
			this.brandConverter = brandConverter;
		}
		
		@Required
		public void setPriceConverter(AbstractPopulatingConverter<Price, PriceData> priceConverter) {
			this.priceConverter = priceConverter;
		}
		
		
		@Required
		public void setMediaConverter(AbstractPopulatingConverter<Media, MediaData> mediaConverter) {
			this.mediaConverter = mediaConverter;
		}
		
		
		@Required
		public void setCategoryConverter(AbstractPopulatingConverter<Category, CategoryData> categoryConverter) {
			this.categoryConverter = categoryConverter;
		}

		public AbstractPopulatingConverter<TaxCategory, TaxCategoryData> getTaxcategoryConverter() {
			return taxcategoryConverter;
		}

		@Required
		public void setTaxcategoryConverter(
				AbstractPopulatingConverter<TaxCategory, TaxCategoryData> taxcategoryConverter) {
			this.taxcategoryConverter = taxcategoryConverter;
		}
		
}
