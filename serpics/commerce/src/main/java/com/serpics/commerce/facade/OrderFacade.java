package com.serpics.commerce.facade;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.commerce.facade.data.CartData;
import com.serpics.commerce.facade.data.OrderData;
import com.serpics.commerce.facade.data.OrderPaymentData;

public interface OrderFacade {
	
	OrderData createOrder(CartData cartData);

	OrderData placeOrder();

	List<OrderData> getOrders();
	Page<OrderData> getPagedOrders(Pageable page);

	OrderData addPayment(Long orderId, OrderPaymentData paymentData);
	
}
