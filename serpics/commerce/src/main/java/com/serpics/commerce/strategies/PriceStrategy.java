package com.serpics.commerce.strategies;

import com.serpics.base.data.model.Currency;
import com.serpics.catalog.PriceNotFoundException;
import com.serpics.catalog.data.model.AbstractProduct;


public interface PriceStrategy {

	Double resolveProductPrice(AbstractProduct product, Currency Currency) throws PriceNotFoundException;

	Double resolveProductCost(AbstractProduct product, Currency Currency) throws PriceNotFoundException;

}
