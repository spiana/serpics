package com.serpics.catalog.hooks;

import com.serpics.base.data.model.Currency;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.stereotype.Hook;

@Hook("priceHook")
public interface PriceHook {

	Double resolveProductPrice(AbstractProduct product, Currency Currency);

	Double resolveProductCost(AbstractProduct product, Currency Currency);

}
