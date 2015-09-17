package com.serpics.jaxrs;

import com.serpics.commerce.facade.data.CartData;
import com.serpics.commerce.facade.data.CartItemModification;

public interface CartRestService {
	
	public CartData getCurrentCart();
	
	public CartItemModification carAdd(String sku , int quantity , double price);
	public CartItemModification carAdd(String sku , int quantity);
	
	public CartItemModification cartUpdate(Long cartUtemID , int quantity);
	

}
