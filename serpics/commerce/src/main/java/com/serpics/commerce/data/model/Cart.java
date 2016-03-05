package com.serpics.commerce.data.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

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
        this.cookie = userCookie;

    }

    // bi-directional many-to-one association to Orderitem
    @OneToMany(mappedBy = "cart", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, orphanRemoval = true, targetEntity=Cartitem.class)
    @OrderBy
    protected Set<Cartitem> cartitems = new LinkedHashSet<Cartitem>(0);

	public Set<Cartitem> getCartitems() {
		return cartitems;
	}

	public void setCartItems(Set<Cartitem> items) {
		this.cartitems = items;
	}

	@Override
	public Set<? extends AbstractOrderitem> getItems() {
		return getCartitems();
	}


}
