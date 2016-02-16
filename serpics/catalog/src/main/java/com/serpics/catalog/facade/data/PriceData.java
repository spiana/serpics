package com.serpics.catalog.facade.data;

import java.util.Date;

import com.serpics.core.facade.AbstractData;

public class PriceData extends AbstractData{
	protected Double productCost;

	protected Double currentPrice;
	protected int minQty;
	protected Double productPrice;
	protected Date validFrom;
	protected Date validTo;
	protected int precedence;

	public Double getProductCost() {
		return productCost;
	}
	public void setProductCost(Double productCost) {
		this.productCost = productCost;
	}
	public Double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}
	public int getMinQty() {
		return minQty;
	}
	public void setMinQty(int minQty) {
		this.minQty = minQty;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	public Date getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	public Date getValidTo() {
		return validTo;
	}
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public int getPrecedence() {
		return precedence;
	}
	public void setPrecedence(int precedence) {
		this.precedence = precedence;
	}
}
