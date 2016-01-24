package com.serpics.commerce.facade.data;

import com.serpics.core.facade.AbstractData;

public class ShippingData extends AbstractData {

	private Double ragestart;
	private Double value;
	
	public Double getRagestart() {
		return ragestart;
	}
	public void setRagestart(Double ragestart) {
		this.ragestart = ragestart;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
}