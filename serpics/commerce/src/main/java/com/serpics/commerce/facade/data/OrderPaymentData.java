package com.serpics.commerce.facade.data;

import com.serpics.commerce.data.model.Paymethod;
import com.serpics.core.facade.AbstractData;

public class OrderPaymentData extends AbstractData {
	
	private Double amount;

    private Paymethod paymethod;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Paymethod getPaymethod() {
		return paymethod;
	}

	public void setPaymethod(Paymethod paymethod) {
		this.paymethod = paymethod;
	}
    
    
}
