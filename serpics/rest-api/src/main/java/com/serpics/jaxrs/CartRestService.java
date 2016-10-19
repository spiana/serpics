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
package com.serpics.jaxrs;

import javax.ws.rs.core.Response;

import com.serpics.jaxrs.data.AddressDataRequest;
import com.serpics.jaxrs.data.CartItemDataRequest;
import com.serpics.jaxrs.data.PaidDataRequest;

public interface CartRestService {
	
	public Response getCurrentCart(String ssid);
	
	public Response cartAdd(String sku , int quantity,String ssid);
	

	public Response cartUpdate(Long cartItemId, CartItemDataRequest cartItemDataRequest,String ssid);
	
	public Response deleteItem(Long itemId,String ssid);
	
	public Response addBillingAddress(AddressDataRequest billingAddress,String ssid);
	public Response addShippingAddress(AddressDataRequest shippingAddress,String ssid);

	public Response addShipmode(String shipmodeName,String ssid);

	public Response getShipmodeList(String ssid);

	public Response deleteCart(String ssid);

	public Response getPaymethodList(String ssid);

	public Response addPaymethod(String paymethodName,String ssid);

	public Response createPayment(String ssid);

	public Response addPaymentInfo(PaidDataRequest paidDataRequest, String ssid);
	
}
