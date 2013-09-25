package com.serpics.catalog.hooks;

import com.serpics.base.persistence.Currency;
import com.serpics.catalog.persistence.AbstractProduct;
import com.serpics.core.hook.AbstractHook;
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
