package com.serpics.jaxrs;

import javax.ws.rs.core.Response;

import com.serpics.jaxrs.data.AddressDataRequest;
import com.serpics.jaxrs.data.CartItemDataRequest;

public interface CartRestService {
	
	public Response getCurrentCart();
	
	public Response cartAdd(String sku , int quantity);
	

	public Response cartUpdate(CartItemDataRequest cartItemRequest);
	
	public Response deleteItem(Long itemId);
	
	public Response addBillingAddress(AddressDataRequest billingAddress);
	public Response addShippingAddress(AddressDataRequest shippingAddress);

	public Response addShipmode(Long shipmodeId);

	public Response getShipmodeList();

	public Response deleteCart();

}
