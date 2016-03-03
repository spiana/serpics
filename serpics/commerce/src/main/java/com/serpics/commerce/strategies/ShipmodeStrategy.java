package com.serpics.commerce.strategies;

import com.serpics.commerce.data.model.AbstractOrder;

public interface ShipmodeStrategy {
	
	public void calculateShippingCost(AbstractOrder order);

}
