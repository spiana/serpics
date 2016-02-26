package com.serpics.jaxrs;

import javax.ws.rs.core.Response;

import com.serpics.jaxrs.data.AddressDataRequest;
import com.serpics.jaxrs.data.CartItemDataRequest;

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

}
