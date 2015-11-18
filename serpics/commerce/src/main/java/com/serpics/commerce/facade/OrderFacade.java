package com.serpics.commerce.facade;

import java.util.List;

import com.serpics.commerce.facade.data.OrderData;
import com.serpics.commerce.facade.data.OrderPaymentData;

public interface OrderFacade {

	OrderData placeOrder();

	List<OrderData> getOrders();

	OrderData addPayment(Long orderId, OrderPaymentData paymentData);
	
}
