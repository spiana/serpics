package com.serpics.catalog.hooks;

import com.serpics.base.persistence.Currency;
import com.serpics.catalog.persistence.AbstractProduct;
import com.serpics.stereotype.Hook;

@Hook("priceHook")
public interface PriceHook {

	Double resolveProductPrice(AbstractProduct product, Currency Currency);

	Double resolveProductCost(AbstractProduct product, Currency Currency);

}
