package com.serpics.commerce.facade;

import com.serpics.commerce.facade.data.CartData;
import com.serpics.commerce.facade.data.CartItemData;
import com.serpics.commerce.facade.data.CartItemModification;
import com.serpics.membership.facade.data.AddressData;

public interface CartFacade {
	public CartItemModification cartAdd(String sku);
	public CartItemModification cartAdd(String sku, int qty);

	public CartData getCurrentCart();

	public CartItemModification cartItemDelete(Long id);
	
	public CartItemModification update(CartItemData cartItem);
	
	public CartData addAddress(AddressData shippingAddress, AddressData buildingAddress);
	
	public CartData addBillingAddress(AddressData billingAddress);
	public CartData addShippingAddress(AddressData shippingAddress);
}
