package com.serpics.commerce.services;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.data.model.Product;
import com.serpics.commerce.data.model.Cart;
import com.serpics.commerce.data.model.Cartitem;
import com.serpics.commerce.data.model.Shipmode;
import com.serpics.membership.data.model.Address;
import com.serpics.membership.data.model.BillingAddress;
import com.serpics.membership.data.model.Member;
import com.serpics.membership.data.model.PermanentAddress;
import com.serpics.membership.data.model.Store;
import com.serpics.warehouse.InventoryNotAvailableException;

public interface CartService {
	public final String SESSION_CART = "cart";
		
    public Cart createSessionCart();

    public Cart getSessionCart();
    
    //Get cart from repository identified by user and customer
    public Cart getCartByUser(Member user, Member customer, Store store, String sessionId);
    
    public void mergeSessionRepositoryCart(Cart repositoryCart, Cart sessionCart) throws InventoryNotAvailableException, ProductNotFoundException;

    //Delete Commands
    public void cartDelete();

    //Delete repository and session cart
    public void cartDelete(Cart cart); 
    
    //Delete only repository Car
    public void cartRepositoryDelete(Cart cart);

    public void cartItemDelete(Cartitem item) throws InventoryNotAvailableException;
    
    public void cartItemDelete(Long id) throws InventoryNotAvailableException;
    
    // Add Commands with create new row, and if merge set true, merge evenutaly product in cart's row
    public Cart cartAdd(Product product, double quantity, Cart cart, boolean merge)
            throws InventoryNotAvailableException, ProductNotFoundException;

    public Cart cartAdd(String sku, double quantity, Cart cart, boolean merge) throws InventoryNotAvailableException,
    ProductNotFoundException;

    public Cart cartAdd(String sku, double quantity, boolean merge) throws InventoryNotAvailableException,
    ProductNotFoundException;

    public Cart cartAdd(Product product, double quantity, boolean merge) throws InventoryNotAvailableException, 
    ProductNotFoundException;
    
    //Update row in cart and remove if quantity is zero
    public Cart cartUpdate(Cartitem orderitem, Cart cart) throws InventoryNotAvailableException,
    ProductNotFoundException;
    
    public Cart cartUpdate(Cartitem orderitem) throws InventoryNotAvailableException, ProductNotFoundException;
    
    //Refresh cart
    public Cart prepareCart(Cart cart, boolean updateInventory) throws InventoryNotAvailableException,
    ProductNotFoundException;
    
    //Refresh cart withous update Inventory
    public Cart prepareCart() throws InventoryNotAvailableException, ProductNotFoundException;

    public Cart prepareCart(Cart cart) throws InventoryNotAvailableException, ProductNotFoundException;

    //Setting information commands to cart
    public void setBillingAddress(BillingAddress address);
    public void setDestinationAddress(PermanentAddress address);
    public void setBillingAddress(Address address);
    public void setDestinationAddress(Address address);
    public void setShippingMode(Shipmode shippingMode);

	public void removeCartFromSession();
      

}
