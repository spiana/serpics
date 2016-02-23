package com.serpics.commerce.services;

import com.serpics.commerce.PaymentException;
import com.serpics.commerce.PaymentIntent;
import com.serpics.commerce.data.model.AbstractOrder;
import com.serpics.commerce.data.model.Payment;
import com.serpics.commerce.data.model.Paymethod;
import com.serpics.commerce.data.model.Paymethodlookup;

public interface PaymentService {

	public Payment createPayment(AbstractOrder order , PaymentIntent intent) throws PaymentException;
	public Payment authorizePayment(Payment	 payment)  throws PaymentException;
	public Payment capturePayment(Payment payment)  throws PaymentException;
	public Payment voidPayment(Payment payment) throws PaymentException;
	public Payment refoundPayment(Payment payment)  throws PaymentException;
	public Payment findCurrentPendingPayment(AbstractOrder order)  throws PaymentException;
	public Paymethodlookup findPaymethodInfo(Paymethod paymethod);
	
}
