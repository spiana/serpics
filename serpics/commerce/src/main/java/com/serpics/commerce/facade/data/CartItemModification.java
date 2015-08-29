package com.serpics.commerce.facade.data;

public class CartItemModification {

	CartModificationStatus modificationStatus;
	CartItemData cartItem;
	String errorMessage;
	
	public CartItemModification(CartModificationStatus modificationStatus,
			CartItemData cartItem, String errorMessage) {
		super();
		this.modificationStatus = modificationStatus;
		this.cartItem = cartItem;
		this.errorMessage = errorMessage;
	}
	
	public CartItemModification(CartModificationStatus modificationStatus,
			CartItemData cartItem) {
		super();
		this.modificationStatus = modificationStatus;
		this.cartItem = cartItem;
	}

	public CartModificationStatus getModificationStatus() {
		return modificationStatus;
	}
	public void setModificationStatus(CartModificationStatus modificationStatus) {
		this.modificationStatus = modificationStatus;
	}
	public CartItemData getCartItem() {
		return cartItem;
	}
	public void setCartItem(CartItemData cartItem) {
		this.cartItem = cartItem;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
