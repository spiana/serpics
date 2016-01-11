package com.serpics.commerce.strategies;

import com.serpics.base.data.model.Currency;
import com.serpics.catalog.PriceNotFoundException;
import com.serpics.catalog.data.model.Product;


public interface PriceStrategy {

	Double resolveProductPrice(Product product, Currency Currency) throws PriceNotFoundException;

	Double resolveProductCost(Product product, Currency Currency) throws PriceNotFoundException;

}
