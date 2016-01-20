package com.serpics.commerce.facade.data;

import java.util.Set;

import com.serpics.core.facade.AbstractData;

public class ShipmodeData extends AbstractData{
	
	private String name;
	private String description;
	private Set<ShippingData> shippings;
	
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
	public Set<ShippingData> getShippings() {
		return shippings;
	}
	public void setShippings(Set<ShippingData> shippings) {
		this.shippings = shippings;
	}

}
