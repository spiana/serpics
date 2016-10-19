/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.jaxrs.data;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class OrderDataRequest {
	
	private ShipmodeDataRequest shipmode;
	private AddressDataRequest billingAddress;
	private AddressDataRequest shippingAddress;
	private Set<OrderItemDataRequest> orderItems;
	private Double discountAmount;
	private Double discountPerc;
	private Double orderAmount;
	private Double totalProduct;
	private Double totalService;
	private Double totalShipping;
	private Double totalTax;
	
	public ShipmodeDataRequest getShipmode() {
		return shipmode;
	}
	public void setShipmode(ShipmodeDataRequest shipmode) {
		this.shipmode = shipmode;
	}
	public AddressDataRequest getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(AddressDataRequest billingAddress) {
		this.billingAddress = billingAddress;
	}
	public AddressDataRequest getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(AddressDataRequest shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public Set<OrderItemDataRequest> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItemDataRequest> orderItems) {
		this.orderItems = orderItems;
	}
	public Double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public Double getDiscountPerc() {
		return discountPerc;
	}
	public void setDiscountPerc(Double discountPerc) {
		this.discountPerc = discountPerc;
	}
	public Double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}
	public Double getTotalProduct() {
		return totalProduct;
	}
	public void setTotalProduct(Double totalProduct) {
		this.totalProduct = totalProduct;
	}
	public Double getTotalService() {
		return totalService;
	}
	public void setTotalService(Double totalService) {
		this.totalService = totalService;
	}
	public Double getTotalShipping() {
		return totalShipping;
	}
	public void setTotalShipping(Double totalShipping) {
		this.totalShipping = totalShipping;
	}
	public Double getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(Double totalTax) {
		this.totalTax = totalTax;
	}

}
