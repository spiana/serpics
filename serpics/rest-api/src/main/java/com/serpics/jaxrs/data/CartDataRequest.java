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
