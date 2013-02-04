package com.serpics.commerce.persistence;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Cart")
@DiscriminatorValue(value = "1")
public class Cart extends AbstractOrder {

	public Cart(Long user_id, Long store_id, String userCookie) {

		this.userId = this.customerId = user_id;
		this.storeId = store_id;
		this.orderAmount = new BigDecimal(0);
		this.status = "P";
		this.cookie = userCookie;

	}
}
