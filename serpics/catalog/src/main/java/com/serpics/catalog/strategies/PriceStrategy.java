package com.serpics.catalog.strategies;

import com.serpics.base.data.model.Currency;
import com.serpics.catalog.data.model.AbstractProduct;


public interface PriceStrategy {

	Double resolveProductPrice(AbstractProduct product, Currency Currency);

	Double resolveProductCost(AbstractProduct product, Currency Currency);

}
