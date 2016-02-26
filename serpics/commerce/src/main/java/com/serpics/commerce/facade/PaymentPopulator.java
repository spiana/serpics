package com.serpics.commerce.facade;

import com.serpics.commerce.data.model.Payment;
import com.serpics.commerce.facade.data.PaymentData;
import com.serpics.core.facade.Populator;

public class PaymentPopulator implements Populator<Payment, PaymentData>{
		
	@Override 
	public void populate(Payment source, PaymentData target) {		
	
		target.setId(source.getId());
		target.setCreated(source.getCreated());

		target.setIntent(source.getIntent().toString());
		
		target.setPaymentIdentifier(source.getPaymentIdentifier());
		target.setAmount(source.getAmount());
		target.setAuthorizedAmount(source.getAuthorizedAmount());
		target.setCaptureAmount(source.getCaptureAmount());
		target.setRefoundAmount(source.getRefoundAmount());
		target.setPayerID(source.getPayerId());
		target.setState(source.getState().toString());
		target.setPayment_method(source.getPayment_method());
		target.setAuthorizedURL(source.getAuthorizedURL());
		
		
		
	}

	
}
