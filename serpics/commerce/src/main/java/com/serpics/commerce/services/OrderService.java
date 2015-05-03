package com.serpics.commerce.services;

import com.serpics.commerce.data.model.Cart;
import com.serpics.commerce.data.model.Order;

public interface OrderService {

	public Order createOrder(Cart cart);
}
