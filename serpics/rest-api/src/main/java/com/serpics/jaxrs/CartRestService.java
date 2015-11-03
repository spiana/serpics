package com.serpics.jaxrs;

import javax.ws.rs.core.Response;

import com.serpics.commerce.facade.data.CartData;
import com.serpics.commerce.facade.data.CartItemModification;
import com.serpics.membership.facade.data.AddressData;

public interface CartRestService {
	
	public CartData getCurrentCart();
	
	public CartItemModification carAdd(String sku , int quantity , double price);
	public CartItemModification carAdd(String sku , int quantity);
	
	public CartItemModification cartUpdate(Long cartUtemId , int quantity);
	
	public Response setBillingAddress(AddressData billingAddress);
	public Response setDestinationAddrress(AddressData destinationAddress);
	

}
