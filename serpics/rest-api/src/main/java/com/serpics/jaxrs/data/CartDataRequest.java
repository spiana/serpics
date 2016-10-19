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
public class CartDataRequest {

	private Double discountAmount;
	private Double discountPerc;
	private Double orderAmount;
	private Double totalProduct;
	private Double totalService;
	private Double totalShipping;
	private Double totalTax;
	private String shipmodeDataName;
	
	private AddressDataRequest billingAddressDataRequest;
	private AddressDataRequest shippingAddressDataRequest;

	private Set<OrderItemDataRequest> orderItemsDataRequest;
	
	

	public String getShipmodeDataName() {
		return shipmodeDataName;
	}

	public void setShipmodeDataName(String shipmodeDataName) {
		this.shipmodeDataName = shipmodeDataName;
	}

	public AddressDataRequest getBillingAddress() {
		return billingAddressDataRequest;
	}

	public void setBillingAddress(AddressDataRequest billingAddress) {
		this.billingAddressDataRequest = billingAddress;
	}

	public AddressDataRequest getShippingAddress() {
		return shippingAddressDataRequest;
	}

	public void setShippingAddress(AddressDataRequest shippingAddress) {
		this.shippingAddressDataRequest = shippingAddress;
	}

	public Set<OrderItemDataRequest> getOrderItems() {
		return orderItemsDataRequest;
	}

	public void setOrderItems(Set<OrderItemDataRequest> orderItems) {
		this.orderItemsDataRequest = orderItems;
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

	public double getTotalProduct() {
		return totalProduct;
	}

	public void setTotalProduct(Double totalProduct) {
		this.totalProduct = totalProduct;
	}
	
	

}
