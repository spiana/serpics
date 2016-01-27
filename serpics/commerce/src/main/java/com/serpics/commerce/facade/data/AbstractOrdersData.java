package com.serpics.commerce.facade.data;

import java.util.Set;
import com.serpics.core.facade.AbstractData;
import com.serpics.membership.facade.data.AddressData;

public abstract class AbstractOrdersData<T extends AbstractOrderItemData> extends AbstractData {

	protected PaymethodData paymethod;
	protected ShipmodeData shipmode;
	protected AddressData billingAddress;
	protected AddressData shippingAddress;

	protected Set<T> orderItems;
	protected Double discountAmount;
	protected Double discountPerc;
	protected Double orderAmount;
	protected Double totalProduct;
	protected Double totalService;
	protected Double totalShipping;
	protected Double totalTax;

	public ShipmodeData getShipmode() {
		return shipmode;
	}

	public void setShipmode(ShipmodeData shipmode) {
		this.shipmode = shipmode;
	}

	public AddressData getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(AddressData billingAddress) {
		this.billingAddress = billingAddress;
	}

	public AddressData getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(AddressData shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Double getDiscountAmount() {
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

	public Set<T> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<T> orderItems) {
		this.orderItems = orderItems;
	}

	public PaymethodData getPaymethod() {
		return paymethod;
	}

	public void setPaymethod(PaymethodData paymethod) {
		this.paymethod = paymethod;
	}

}
