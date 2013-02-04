package com.serpics.commerce.services;

import com.serpics.commerce.persistence.Cart;
import com.serpics.commerce.persistence.Order;

public interface OrderService {

	public Order createOrder(Cart cart);
}
