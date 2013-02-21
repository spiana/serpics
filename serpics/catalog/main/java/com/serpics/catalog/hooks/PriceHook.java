package com.serpics.catalog.hooks;

import com.serpics.catalog.persistence.AbstractProduct;
import com.serpics.core.hook.Hook;

@Hook("priceHook")
public interface PriceHook {

	Double resolveProductPrice(AbstractProduct product, String Currency);

	Double resolveProductCost(AbstractProduct product, String Currency);

}
