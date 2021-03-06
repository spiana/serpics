/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.commerce.services;

import java.util.List;

import com.serpics.base.data.model.Store;
import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.commerce.PaymentException;
import com.serpics.commerce.data.model.Cart;
import com.serpics.commerce.data.model.Cartitem;
import com.serpics.commerce.data.model.Payment;
import com.serpics.commerce.data.model.Paymethod;
import com.serpics.commerce.data.model.Shipmode;
import com.serpics.membership.data.model.Address;
import com.serpics.membership.data.model.BillingAddress;
import com.serpics.membership.data.model.Member;
import com.serpics.membership.data.model.PermanentAddress;
import com.serpics.warehouse.InventoryNotAvailableException;

public interface CartService {
	public final String SESSION_CART = "cart";
		
    public Cart createSessionCart();

    public Cart getSessionCart();
    
    //Get cart from repository identified by user and customer
    public Cart getCartByUser(Member user, Member customer, Store store, String sessionId);
    public void mergeCartAtLogin(Member user, Member customer, Store store, String sessionId) throws InventoryNotAvailableException, ProductNotFoundException;
    
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
    public Cart cartAdd(AbstractProduct product, double quantity, Cart cart, boolean merge)
            throws InventoryNotAvailableException, ProductNotFoundException;

    public Cart cartAdd(String sku, double quantity, Cart cart, boolean merge) throws InventoryNotAvailableException,
    ProductNotFoundException;

    public Cart cartAdd(String sku, double quantity, boolean merge) throws InventoryNotAvailableException,
    ProductNotFoundException;

    public Cart cartAdd(AbstractProduct product, double quantity, boolean merge) throws InventoryNotAvailableException, 
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


	public List<Shipmode> getShipmode();

	public void addShipmode(String shipmodeName);

	public List<Paymethod> getPaymethod();

	public void addPaymethod(String paymethodName);
	
	public Payment createPayment() throws PaymentException;
	
	public void addPaymentInfo(String paymentIdentifier, String PayerId) throws PaymentException;
      

}
