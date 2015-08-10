package com.serpics.catalog.facade;

import org.springframework.beans.factory.annotation.Required;

import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.data.model.Brand;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.facade.data.BrandData;
import com.serpics.catalog.facade.data.PriceData;
import com.serpics.catalog.facade.data.ProductData;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.core.facade.Populator;

public class ProductPopulator implements Populator<AbstractProduct, ProductData> {
	private AbstractPopulatingConverter<Brand, BrandData> brandConverter;
	private AbstractPopulatingConverter<Price, PriceData> priceConverter;
	@Override
	public void populate(AbstractProduct source, ProductData target) {
		target.setBuyable(source.getBuyable());
		//target.setDowloadable(source.getDownlodable());
		target.setManufacturSku(source.getManufacturerSku());
		target.setPublished(source.getPublished());
		target.setUnitMeas(source.getUnitMeas());
		target.setWeight(source.getWeight());
		target.setWeightMeas(source.getWeightMeas());
		
		target.setMetaKey(source.getMetaKeyword());
		target.setMetaDescription(source.getMetaDescription());
		
		/*if(source.getPrices() != null) {
			Set<PriceData> pricesd = new HashSet<PriceData>();
			Set<Price> prices = source.getPrices();
			for (Price _p : prices) {
				PriceData priced = priceConverter.convert(_p);
				pricesd.add(priced);
			}
			target.setPrices(pricesd);
			
		} */
		if(source.getBrand() != null)
			target.setBrand(brandConverter.convert(source.getBrand()));
		
		target.setCode(source.getCode());
		target.setUuid(source.getUuid());
		target.setUrl(source.getUrl());
		if(source.getDescription() != null)
			target.setDescription(source.getDescription().getText("it"));
	
		
	}

		@Required
		public void setBrandConverter(AbstractPopulatingConverter<Brand, BrandData> brandConverter) {
			this.brandConverter = brandConverter;
		}
		
		/*@Required
		public void setPriceConverter(AbstractPopulatingConverter<Price, PriceData> priceConverter) {
			this.priceConverter = priceConverter;
		}*/
}
