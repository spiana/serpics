package com.serpics.commerce.data.model;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.serpics.membership.data.model.Store;
import com.serpics.membership.data.model.User;

@Entity(name = "Cart")
@DiscriminatorValue(value = "1")
public class Cart extends AbstractOrder {

    public Cart() {
        super();
    }

    public Cart(final User user, final Store store, final String userCookie) {

        this.user = this.customer = user;
        this.store = store;
        this.orderAmount = new BigDecimal(0);
        this.status = "P";
        this.cookie = userCookie;

    }


    public Set<Cartitem> getCartitems() {
        return (Set<Cartitem>) super.getOrderitems();
    }

    public Set<AbstractOrderitem> setCartitems(Set<AbstractOrderitem> items) {
        return this.orderitems = items;
    }
}
