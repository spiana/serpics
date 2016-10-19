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
