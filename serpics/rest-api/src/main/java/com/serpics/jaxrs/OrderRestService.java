package com.serpics.jaxrs;

import javax.ws.rs.core.Response;

import com.serpics.jaxrs.data.CartDataRequest;
import com.serpics.jaxrs.data.OrderPaymentDataRequest;

public interface OrderRestService {

	Response getOrders(int page, int size, String ssid);
	
	Response addPayment(Long orderId, OrderPaymentDataRequest paymentData,String ssid);

	Response placeOrder(String ssid);

	Response createOrder(CartDataRequest cartData,String ssid);
}
