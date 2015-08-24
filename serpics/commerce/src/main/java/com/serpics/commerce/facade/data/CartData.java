package com.serpics.commerce.facade.data;

import java.util.Set;

public class CartData extends AbstractOrdersData {
	
	@Override
	public Set<CartItemData> getOrderItems() {
		return orderItems;
	}
	
	
}
