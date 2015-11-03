package com.serpics.commerce.facade;

import com.serpics.commerce.data.model.Orderitem;
import com.serpics.commerce.facade.data.OrderItemData;
import com.serpics.core.facade.Populator;

public class OrderItemPopulator extends AbstractOrderItemPopulator implements Populator<Orderitem, OrderItemData> {

	@Override
	public void populate(Orderitem source, OrderItemData target) {
		super.populate(source,target);
	}

}
