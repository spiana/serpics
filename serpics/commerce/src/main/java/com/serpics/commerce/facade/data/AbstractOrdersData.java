package com.serpics.commerce.facade.data;

import java.math.BigDecimal;
import java.util.Set;

import com.serpics.commerce.data.model.Shipmode;
import com.serpics.membership.data.model.Address;
import com.serpics.membership.data.model.BillingAddress;


public abstract class AbstractOrdersData<T>{
	protected Long id;
	protected Shipmode shipmode;
	protected BillingAddress billingAddress;
	protected Address shippingAddress;
		
	protected Set<AbstractOrderItemData> orderItems; 
	protected BigDecimal discountAmount;
	protected BigDecimal discountPerc;
	protected BigDecimal orderAmount;
	protected BigDecimal totalProduct;
	protected BigDecimal totalService;
	protected BigDecimal totalShipping;
	protected BigDecimal totalTax;
	
	
	
	public Shipmode getShipmode() {
		return shipmode;
	}
	public void setShipmode(Shipmode shipmode) {
		this.shipmode = shipmode;
	}
	public BillingAddress getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(BillingAddress billingAddress) {
		this.billingAddress = billingAddress;
	}
	public Address getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}
	public BigDecimal getDiscountPerc() {
		return discountPerc;
	}
	public void setDiscountPerc(BigDecimal discountPerc) {
		this.discountPerc = discountPerc;
	}
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}
	public BigDecimal getTotalProduct() {
		return totalProduct;
	}
	public void setTotalProduct(BigDecimal totalProduct) {
		this.totalProduct = totalProduct;
	}
	public BigDecimal getTotalService() {
		return totalService;
	}
	public void setTotalService(BigDecimal totalService) {
		this.totalService = totalService;
	}
	public BigDecimal getTotalShipping() {
		return totalShipping;
	}
	public void setTotalShipping(BigDecimal totalShipping) {
		this.totalShipping = totalShipping;
	}
	public BigDecimal getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
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
