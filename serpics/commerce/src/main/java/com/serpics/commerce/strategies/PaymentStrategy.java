package com.serpics.commerce.strategies;

import com.serpics.commerce.PaymentException;
import com.serpics.commerce.PaymentIntent;
import com.serpics.commerce.data.model.AbstractOrder;
import com.serpics.commerce.data.model.Payment;

public interface PaymentStrategy {

	public Payment createPayment(AbstractOrder order , PaymentIntent intent)  throws PaymentException;
	public Payment authorizePayment(Payment payment)  throws PaymentException;
	public Payment capturePayment(Payment payment)  throws PaymentException;
	public Payment voidPayment(Payment payment)  throws PaymentException;
	public Payment refoundPayment(Payment payment)  throws PaymentException;
	
}
