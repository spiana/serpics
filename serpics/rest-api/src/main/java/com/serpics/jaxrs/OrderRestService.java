package com.serpics.jaxrs;

import javax.ws.rs.core.Response;

import com.serpics.jaxrs.data.CartDataRequest;
import com.serpics.jaxrs.data.OrderPaymentDataRequest;

public interface OrderRestService {

	Response getOrders();
	
	Response addPayment(Long orderId, OrderPaymentDataRequest paymentData);

	Response placeOrder();

	Response createOrder(CartDataRequest cartDataRequest);
}
