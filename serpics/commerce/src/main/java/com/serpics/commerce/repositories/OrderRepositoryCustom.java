package com.serpics.commerce.repositories;

import com.serpics.commerce.data.model.Cart;
import com.serpics.commerce.data.model.Order;

public interface OrderRepositoryCustom {

	public Order createOrderFromcart(Cart cart);
}
