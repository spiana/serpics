package com.serpics.catalog.facade;

import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.facade.data.PriceData;
import com.serpics.core.facade.Populator;

public class PricePopulator implements Populator<Price, PriceData> {
	@Override
	public void populate(Price source, PriceData target) {
		if(source.getCurrentPrice() != null)
			target.setCurrentPrice(source.getCurrentPrice());
		if(source.getProductCost() != null)
			target.setProductCost(source.getProductCost());
		if(source.getProductPrice() != null)
			target.setProductPrice(source.getProductPrice());
		if(source.getPrecedence() != null)
			target.setPrecedence(source.getPrecedence().intValue());
	}
}
