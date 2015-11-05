package com.serpics.commerce.facade;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.commerce.facade.data.CartData;
import com.serpics.commerce.facade.data.CartItemData;
import com.serpics.commerce.facade.data.CartItemModification;
import com.serpics.membership.facade.data.AddressData;
import com.serpics.warehouse.InventoryNotAvailableException;

public interface CartFacade {
	public CartItemModification cartAdd(String sku);
	public CartItemModification cartAdd(String sku, int qty);
	
	
	public CartData getCurrentCart();
	
	
	public CartItemModification cartItemDelete(Long id);
	
	public CartData update(CartItemData cartItem) throws InventoryNotAvailableException, ProductNotFoundException;
	
	public CartData addAddress(AddressData shippingAddress, AddressData buildingAddress);
	
	public CartData addBillingAddress(AddressData billingAddress);
	public CartData addShippingAddress(AddressData shippingAddress);
}
