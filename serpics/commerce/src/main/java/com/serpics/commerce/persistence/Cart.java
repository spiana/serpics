package com.serpics.commerce.persistence;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.serpics.membership.persistence.Store;
import com.serpics.membership.persistence.User;

@Entity(name = "Cart")
@DiscriminatorValue(value = "1")
public class Cart extends AbstractOrder {

	public Cart() {
		super();
	}

	public Cart(User user, Store store, String userCookie) {

		this.user = this.customer = user;
		this.store = store;
		this.orderAmount = new BigDecimal(0);
		this.status = "P";
		this.cookie = userCookie;

	}
}
