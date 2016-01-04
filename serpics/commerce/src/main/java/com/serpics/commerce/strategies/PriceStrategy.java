package com.serpics.commerce.strategies;

import com.serpics.base.data.model.Currency;
import com.serpics.catalog.PriceNotFoundException;
import com.serpics.catalog.data.model.BaseProduct;


public interface PriceStrategy {

	Double resolveProductPrice(BaseProduct product, Currency Currency) throws PriceNotFoundException;

	Double resolveProductCost(BaseProduct product, Currency Currency) throws PriceNotFoundException;

}
