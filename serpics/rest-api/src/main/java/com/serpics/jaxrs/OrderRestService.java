package com.serpics.jaxrs;

import javax.ws.rs.core.Response;

import com.serpics.commerce.facade.data.CartData;
import com.serpics.commerce.facade.data.OrderPaymentData;

public interface OrderRestService {

	Response getOrders();
	
	Response addPayment(Long orderId, OrderPaymentData paymentData);

	Response placeOrder();

	Response createOrder(CartData cartData);
}
