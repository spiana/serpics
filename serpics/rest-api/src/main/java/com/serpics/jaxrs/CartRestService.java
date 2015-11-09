package com.serpics.jaxrs;

import com.serpics.commerce.facade.data.CartData;
import com.serpics.commerce.facade.data.CartItemData;
import com.serpics.commerce.facade.data.CartItemModification;
import com.serpics.membership.facade.data.AddressData;

public interface CartRestService {
	
	public CartData getCurrentCart();
	
	public CartItemModification cartAdd(String sku , int quantity);
	
	public CartItemModification cartUpdate(CartItemData cartItem);
	
	public CartItemModification deleteItem(Long itemId);
	
	public CartData addBillingAddress(AddressData billingAddress);
	public CartData addShippingAddress(AddressData shippingAddress);
	

}
