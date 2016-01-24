package com.serpics.jaxrs;

import javax.ws.rs.core.Response;

import com.serpics.commerce.facade.data.CartItemData;
import com.serpics.membership.facade.data.AddressData;

public interface CartRestService {
	
	public Response getCurrentCart();
	
	public Response cartAdd(String sku , int quantity);
	

	public Response cartUpdate(CartItemData cartItem);
	
	public Response deleteItem(Long itemId);
	
	public Response addBillingAddress(AddressData billingAddress);
	public Response addShippingAddress(AddressData shippingAddress);

	

}
