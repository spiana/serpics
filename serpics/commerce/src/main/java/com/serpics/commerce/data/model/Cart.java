package com.serpics.commerce.data.model;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.serpics.base.data.model.Store;
import com.serpics.membership.data.model.User;

@Entity(name = "Cart")
@DiscriminatorValue(value = "1")
public class Cart extends AbstractOrder {

	private static final long serialVersionUID = 1366756693313998623L;

	public Cart() {
        super();
    }

    public Cart(final User user, final Store store, final String userCookie) {

        this.user = user;
        this.store = store;
        this.orderAmount = 0D;
        this.status = "P";
        this.cookie = userCookie;

    }


    @SuppressWarnings("unchecked")
	public Set<Cartitem> getCartitems() {
        return (Set<Cartitem>) super.getOrderitems();
    }

}
