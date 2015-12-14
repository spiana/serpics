package com.serpics.commerce.strategies;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.membership.data.model.Member;
import com.serpics.warehouse.InventoryNotAvailableException;

public interface CartStrategy {
	
	public void mergeCart (Member user, Member Customer) throws InventoryNotAvailableException, ProductNotFoundException;

}
