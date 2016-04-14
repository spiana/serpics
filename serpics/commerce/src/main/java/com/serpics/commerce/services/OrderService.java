package com.serpics.commerce.services;

import java.util.List;

import com.serpics.commerce.EmptyCartException;
import com.serpics.commerce.data.model.Cart;
import com.serpics.commerce.data.model.Order;
import com.serpics.commerce.data.model.Orderpayment;
import com.serpics.membership.data.model.User;

public interface OrderService {

	public Order createOrder(Cart cart) throws EmptyCartException;
	public Order addPayment( Order order,Orderpayment payment );
	public Order getOrder(Long id);
	public List<Order> getOrdersByUser(User user);
}
