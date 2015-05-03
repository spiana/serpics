package com.serpics.catalog.hooks;

import com.serpics.base.data.model.Currency;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.commerce.hook.AbstractHook;
import com.serpics.stereotype.StoreHook;

@StoreHook("priceHook")
public class PriceHookImpl extends AbstractHook implements PriceHook {

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
