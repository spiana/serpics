package com.serpics.catalog.facade;

import org.springframework.beans.factory.annotation.Required;

import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.facade.data.PriceData;
import com.serpics.catalog.facade.data.ProductData;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.core.facade.Populator;

public class PricePopulator implements Populator<Price, PriceData> {

	private AbstractPopulatingConverter<Product, ProductData> productConverter;
	
	@Override
	public void populate(Price source, PriceData target) {
		target.setCurrentPrice(source.getCurrentPrice());
		target.setProductCost(source.getProductCost());
		target.setProductPrice(source.getProductPrice());
		target.setProduct(productConverter.convert((Product)source.getProduct()));
		
	}

	@Required
	public void setProductConverter(AbstractPopulatingConverter<Product, ProductData> productConverter) {
		this.productConverter = productConverter;
	}
}
