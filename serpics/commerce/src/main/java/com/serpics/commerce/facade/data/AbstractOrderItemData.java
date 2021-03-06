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
package com.serpics.commerce.facade.data;

import com.serpics.catalog.facade.data.AbstractProductData;
import com.serpics.core.facade.AbstractData;


public abstract  class AbstractOrderItemData extends AbstractData{
	protected Double discountAmount;
	protected Double discountPerc;
	
	protected double quantity;
	
	protected AbstractProductData product;
	protected String sku;
	protected Double skuCost;
	protected String skuDescription;
	protected Double skuNetPrice;
	protected Double skuPrice;
	
	protected Double shippingCost;
	//protected ShipmodeData shipmode;
	protected long shippingAddressId;
	
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
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public AbstractProductData getProduct() {
		return product;
	}
	public void setProduct(AbstractProductData product) {
		this.product = product;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public Double getSkuCost() {
		return skuCost;
	}
	public void setSkuCost(Double skuCost) {
		this.skuCost = skuCost;
	}
	public String getSkuDescription() {
		return skuDescription;
	}
	public void setSkuDescription(String skuDescription) {
		this.skuDescription = skuDescription;
	}
	public Double getSkuNetPrice() {
		return skuNetPrice;
	}
	public void setSkuNetPrice(Double skuNetPrice) {
		this.skuNetPrice = skuNetPrice;
	}
	public Double getSkuPrice() {
		return skuPrice;
	}
	public void setSkuPrice(Double skuPrice) {
		this.skuPrice = skuPrice;
	}
	public Double getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(Double shippingCost) {
		this.shippingCost = shippingCost;
	}
	public long getShippingAddressId() {
		return shippingAddressId;
	}
	public void setShippingAddressId(long shippingAddressId) {
		this.shippingAddressId = shippingAddressId;
	}
	
	
	
}

 