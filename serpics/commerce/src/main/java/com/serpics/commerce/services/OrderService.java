package com.serpics.commerce.services;

import com.serpics.commerce.data.model.Cart;
import com.serpics.commerce.data.model.Order;
import com.serpics.commerce.data.model.Orderpayment;

public interface OrderService {

	public Order createOrder(Cart cart);
	public Order addPayment( Order order,Orderpayment payment );
}
