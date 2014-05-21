package com.serpics.commerce.services;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.persistence.AbstractProduct;
import com.serpics.commerce.persistence.Cart;
import com.serpics.commerce.persistence.Cartitem;
import com.serpics.warehouse.InventoryNotAvailableException;

public interface CartService {

    public Cart createSessionCart();

    public Cart findSessionCart();

    public void cartDelete();

    public void cartDelete(Cart cart);

    public Cart cartUpdate(Cartitem orderitem, Cart cart) throws InventoryNotAvailableException,
    ProductNotFoundException;

    public Cart cartAdd(AbstractProduct product, double quantity, Cart cart, boolean merge)
            throws InventoryNotAvailableException, ProductNotFoundException;

    public Cart cartAdd(String sku, double quantity, Cart cart, boolean merge) throws InventoryNotAvailableException,
    ProductNotFoundException;

    public Cart cartAdd(String sku, double quantity, boolean merge) throws InventoryNotAvailableException,
    ProductNotFoundException;

    public Cart cartUpdate(Cartitem orderitem) throws InventoryNotAvailableException, ProductNotFoundException;

    public Cart cartAdd(AbstractProduct product, double quantity, boolean merge) throws InventoryNotAvailableException,
    ProductNotFoundException;

    public Cart prepareCart() throws InventoryNotAvailableException, ProductNotFoundException;

    public Cart prepareCart(Cart cart) throws InventoryNotAvailableException, ProductNotFoundException;

    public Cart prepareCart(Cart cart, boolean updateInventory) throws InventoryNotAvailableException,
    ProductNotFoundException;

}
