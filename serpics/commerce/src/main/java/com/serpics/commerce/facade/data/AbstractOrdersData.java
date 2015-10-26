package com.serpics.commerce.facade.data;

import java.util.Set;

import com.serpics.commerce.data.model.Shipmode;
import com.serpics.membership.data.model.Address;


public abstract class AbstractOrdersData<T>{
	protected Long id;
	protected Shipmode shipmode;
	protected Address billingAddress;
	protected Address shippingAddress;
		
	protected Set<AbstractOrderItemData> orderItems; 
	protected Double discountAmount;
	protected Double discountPerc;
	protected Double orderAmount;
	protected Double totalProduct;
	protected Double totalService;
	protected Double totalShipping;
	protected Double totalTax;

	
	
	
	public Shipmode getShipmode() {
		return shipmode;
	}
	public void setShipmode(Shipmode shipmode) {
		this.shipmode = shipmode;
	}
	public Address getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}
	public Address getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public double getDiscountAmount() {
		return discountAmount;
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
	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public Set<AbstractOrderItemData> getOrderItems() {
		return orderItems;
	} 
	public void setOrderItems(Set<AbstractOrderItemData> orderItems) {
		this.orderItems = orderItems;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	
}
