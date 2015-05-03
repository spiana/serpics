package com.serpics.commerce.repositories;

import com.serpics.commerce.data.model.Cart;
import com.serpics.core.data.Repository;

public interface CartRepository extends Repository<Cart, Long> {

	public Cart findByCookie(String cookie);

}
