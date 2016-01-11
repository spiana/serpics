package com.serpics.commerce.strategies;

import javax.annotation.Resource;

import com.serpics.base.data.model.Currency;
import com.serpics.catalog.PriceNotFoundException;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.services.PriceService;
import com.serpics.stereotype.StoreStrategy;

@StoreStrategy("priceStrategy")
public class PriceStrategyImpl extends AbstractStrategy implements PriceStrategy {

	@Resource(name="priceService")
	PriceService priceService;
	
	@Override
	public Double resolveProductPrice(Product product, Currency currency) throws PriceNotFoundException{
		Price price = priceService.findProductPrice(product , currency);
		return price.getCurrentPrice() != null ? price.getCurrentPrice() : new Double(0);
	}

	@Override
	public Double resolveProductCost(Product product, Currency currency) throws PriceNotFoundException{
		Price price = priceService.findProductPrice(product , currency);
		return price.getProductCost() != null ? price.getProductCost() : new Double(0);
	}

}
