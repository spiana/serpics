package com.serpics.commerce.services;

import java.util.Hashtable;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.data.model.Product;
import com.serpics.commerce.data.model.Cart;
import com.serpics.commerce.data.model.Cartitem;
import com.serpics.commerce.data.model.Shipmode;
import com.serpics.membership.data.model.Address;
import com.serpics.membership.data.model.BillingAddress;
import com.serpics.membership.data.model.PermanentAddress;
import com.serpics.warehouse.InventoryNotAvailableException;

public interface CartService {
	public final String SESSION_CART = "cart";
		
    public Cart createSessionCart();

    public Cart getSessionCart();

    public void cartDelete();

    public void cartDelete(Cart cart); 

    public Cartitem cartUpdate(Cartitem orderitem, Cart cart) throws InventoryNotAvailableException,
    ProductNotFoundException;
    
    public Cart cartUpdateProduct(Hashtable<Product, Double> cart) throws InventoryNotAvailableException,
    ProductNotFoundException;
    
    public Cartitem cartAdd(AbstractProduct product, double quantity, Cart cart, boolean merge)
            throws InventoryNotAvailableException, ProductNotFoundException;

    public Cartitem cartAdd(String sku, double quantity, Cart cart, boolean merge) throws InventoryNotAvailableException,
    ProductNotFoundException;

    public Cartitem cartAdd(String sku, double quantity, boolean merge) throws InventoryNotAvailableException,
    ProductNotFoundException;

    public Cartitem cartUpdate(Cartitem orderitem) throws InventoryNotAvailableException, ProductNotFoundException;

    public Cartitem cartAdd(AbstractProduct product, double quantity, boolean merge) throws InventoryNotAvailableException, 
    ProductNotFoundException;

    public Cart prepareCart() throws InventoryNotAvailableException, ProductNotFoundException;

    public Cart prepareCart(Cart cart) throws InventoryNotAvailableException, ProductNotFoundException;

    public Cart prepareCart(Cart cart, boolean updateInventory) throws InventoryNotAvailableException,
    ProductNotFoundException;
    
    public void cartItemDelete(Cartitem item);
    
    public void cartItemDelete(Long id);
    
    public void setBillingAddress(BillingAddress address);
    public void setDestinationAddress(PermanentAddress address);
    public void setBillingAddress(Address address);
    public void setDestinationAddress(Address address);
    public void setShippingMode(Shipmode shippingMode);
  

}
