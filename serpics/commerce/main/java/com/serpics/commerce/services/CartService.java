package com.serpics.commerce.services;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.persistence.AbstractProduct;
import com.serpics.commerce.persistence.Cart;
import com.serpics.commerce.persistence.Orderitem;
import com.serpics.warehouse.InventoryNotAvailableException;

public interface CartService {

	public Cart createSessionCart();

	public Cart findSessionCart();

	public void cartUpdate(Orderitem orderitem, Cart cart) throws InventoryNotAvailableException,
			ProductNotFoundException;

	public void cartAdd(AbstractProduct product, double quantity, Cart cart, boolean merge)
			throws InventoryNotAvailableException, ProductNotFoundException;

	public void cartAdd(String sku, double quantity, Cart cart, boolean merge) throws InventoryNotAvailableException,
			ProductNotFoundException;

	public void cartAdd(String sku, double quantity, boolean merge) throws InventoryNotAvailableException,
			ProductNotFoundException;

	public void cartUpdate(Orderitem orderitem) throws InventoryNotAvailableException, ProductNotFoundException;

	public void cartAdd(AbstractProduct product, double quantity, boolean merge) throws InventoryNotAvailableException,
			ProductNotFoundException;

	public void prepareCart() throws InventoryNotAvailableException, ProductNotFoundException;

	public void prepareCart(Cart cart) throws InventoryNotAvailableException, ProductNotFoundException;
}
