package com.serpics.commerce.strategies;

import javax.annotation.Resource;

import com.serpics.base.data.model.Currency;
import com.serpics.catalog.PriceNotFoundException;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.services.PriceService;
import com.serpics.commerce.strategies.AbstractStrategy;
import com.serpics.stereotype.StoreStrategy;

@StoreStrategy("priceStrategy")
public class PriceStrategyImpl extends AbstractStrategy implements PriceStrategy {

	@Resource(name="priceService")
	PriceService priceService;
	
	@Override
	public Double resolveProductPrice(AbstractProduct product, Currency currency) throws PriceNotFoundException{
		Price price = priceService.findProductPrice(product , currency);
		return price.getCurrentPrice();
	}

	@Override
	public Double resolveProductCost(AbstractProduct product, Currency currency) throws PriceNotFoundException{
		Price price = priceService.findProductPrice(product , currency);
		return price.getProductCost();
	}

}
