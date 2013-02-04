package com.serpics.commerce.repositories;

import com.serpics.commerce.persistence.Cart;
import com.serpics.commerce.persistence.Order;

public interface OrderRepositoryCustom {

	public Order createOrderFromcart(Cart cart);
}
