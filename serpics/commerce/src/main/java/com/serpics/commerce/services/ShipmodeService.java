package com.serpics.commerce.services;

import com.serpics.commerce.ShipmodeException;
import com.serpics.commerce.data.model.AbstractOrder;

public interface ShipmodeService {

	public void calculateShippingCost(AbstractOrder order) throws ShipmodeException;

}
