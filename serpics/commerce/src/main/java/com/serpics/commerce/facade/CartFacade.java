package com.serpics.commerce.facade;

import com.serpics.commerce.facade.data.CartData;
import com.serpics.commerce.facade.data.CartItemData;
import com.serpics.commerce.facade.data.CartModification;
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
}
