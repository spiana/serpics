package com.serpics.commerce.facade;

import com.serpics.commerce.data.model.Order;
import com.serpics.commerce.facade.data.OrderData;
import com.serpics.core.facade.Populator;

public class OrderPopulator extends AbstractOrderPopulator implements Populator<Order,OrderData> {

	@Override
	public void populate(Order source, OrderData target) {
		super.populate(source, target);
		
	}

}
