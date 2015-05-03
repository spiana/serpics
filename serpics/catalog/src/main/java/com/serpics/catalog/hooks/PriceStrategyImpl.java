package com.serpics.catalog.hooks;

import com.serpics.base.data.model.Currency;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.commerce.strategies.AbstractStrategy;
import com.serpics.stereotype.StoreStrategy;

@StoreStrategy("priceHook")
public class PriceStrategyImpl extends AbstractStrategy implements PriceStrategy {

	@Override
	public Double resolveProductPrice(AbstractProduct product, Currency currency) {
		// TODO Auto-generated method stub
		return Double.valueOf(100.00);
	}

	@Override
	public Double resolveProductCost(AbstractProduct product, Currency currency) {
		// TODO Auto-generated method stub
		return Double.valueOf(80.50);
	}

}
