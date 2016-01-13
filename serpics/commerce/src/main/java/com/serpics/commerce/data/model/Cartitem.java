package com.serpics.commerce.data.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value = "1")
public class Cartitem extends AbstractOrderitem {

	private static final long serialVersionUID = -346014887001626903L;

	public Cartitem() {
		super();
	
	}

	public Cartitem(String sku, String sku_description, double quantity,
			Double price) {
		super(sku, sku_description, quantity, price);
		
	}

	 @ManyToOne
	 @JoinColumn(name = "order_id", nullable = false)
	 protected Cart cart;

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

}
