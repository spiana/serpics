package com.serpics.commerce.facade.data;

import java.io.Serializable;

public class CartModification implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4603170717467708916L;
	
	
	private CartModificationStatus modificationStatus;
	private CartData cart;
	private String errorMessage;
	
	public CartModification(CartModificationStatus modificationStatus,
			CartData cart, String errorMessage) {
		super();
		this.modificationStatus = modificationStatus;
		this.cart = cart;
		this.errorMessage = errorMessage;
	}
	
	public CartModification(CartModificationStatus modificationStatus,
			CartData cart) {
		super();
		this.modificationStatus = modificationStatus;
		this.cart = cart;
	}

	public CartModificationStatus getModificationStatus() {
		return modificationStatus;
	}
	public void setModificationStatus(CartModificationStatus modificationStatus) {
		this.modificationStatus = modificationStatus;
	}
	
	public CartData getCart() {
		return cart;
	}

	public void setCart(CartData cart) {
		this.cart = cart;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
