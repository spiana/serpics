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
package com.serpics.commerce.facade;

import java.util.List;

import com.serpics.base.data.model.Store;
import com.serpics.commerce.facade.data.CartData;
import com.serpics.commerce.facade.data.CartItemData;
import com.serpics.commerce.facade.data.CartModification;
import com.serpics.commerce.facade.data.PaymentData;
import com.serpics.commerce.facade.data.PaymethodData;
import com.serpics.commerce.facade.data.ShipmodeData;
import com.serpics.membership.data.model.Member;
import com.serpics.membership.facade.data.AddressData;

public interface CartFacade {
	public CartModification cartAdd(String sku);
	public CartModification cartAdd(String sku, int qty);

	public CartData getCurrentCart();

	public CartModification cartItemDelete(Long id);
	
	public CartModification update(CartItemData cartItem);
	
	public CartData addAddress(AddressData shippingAddress, AddressData buildingAddress);
	
	public CartData addBillingAddress(AddressData billingAddress);
	public CartData addShippingAddress(AddressData shippingAddress);

	public List<ShipmodeData> getShipmodeList();
	public CartData addShipmode(String shipmodeName);
	
	public CartData deleteCart();
	public CartData addPaymethod(String paymethodName);
	public List<PaymethodData> getPaymethodList();
	public void mergeCartAtLogin(Member member,Member customer, Store store,String ssid);
	public PaymentData createPayment();
	public void addPaymentInfo(PaymentData paymentData);
}
