package com.serpics.catalog.hooks;

import com.serpics.catalog.persistence.AbstractProduct;

public interface PriceHook {

	Double resolveProductPrice(AbstractProduct product, String Currency);

	Double resolveProductCost(AbstractProduct product, String Currency);

}
