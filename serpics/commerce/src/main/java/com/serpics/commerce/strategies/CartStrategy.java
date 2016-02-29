package com.serpics.commerce.strategies;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.commerce.data.model.Cart;
import com.serpics.warehouse.InventoryNotAvailableException;

public interface CartStrategy {
	
	public void mergeCart (Cart repositoryCart, Cart sessionCart) throws InventoryNotAvailableException, ProductNotFoundException;

}
