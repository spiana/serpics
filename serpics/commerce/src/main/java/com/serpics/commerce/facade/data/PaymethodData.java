package com.serpics.commerce.facade.data;

import com.serpics.core.facade.AbstractData;

public class PaymethodData extends AbstractData{
	
	private Long paymethodId;
	private String name;
	private String description;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getPaymethodId() {
		return paymethodId;
	}
	public void setPaymethodId(Long paymethodId) {
		this.paymethodId = paymethodId;
	}
	
}
