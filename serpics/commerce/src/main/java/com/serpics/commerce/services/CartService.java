package com.serpics.commerce.services;

import java.util.Hashtable;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.data.model.Product;
import com.serpics.commerce.data.model.Cart;
import com.serpics.commerce.data.model.Cartitem;
import com.serpics.warehouse.InventoryNotAvailableException;

public interface CartService {
	public final String SESSION_CART = "cart";
		
    public Cart createSessionCart();

    public Cart getSessionCart();

    public void cartDelete();

    public void cartDelete(Cart cart); 

    public Cart cartUpdate(Cartitem orderitem, Cart cart) throws InventoryNotAvailableException,
    ProductNotFoundException;
    
   
    
    public Cart cartUpdateProduct(Hashtable<Product, Double> cart) throws InventoryNotAvailableException,
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
