package com.serpics.commerce.persistence;

import com.serpics.base.persistence.Currency;
import com.serpics.membership.persistence.Address;
import com.serpics.membership.persistence.Store;
import com.serpics.membership.persistence.User;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-21T15:49:23.201+0100")
@StaticMetamodel(AbstractOrder.class)
public class AbstractOrder_ {
	public static volatile SingularAttribute<AbstractOrder, Long> ordersId;
	public static volatile SingularAttribute<AbstractOrder, String> cookie;
	public static volatile SingularAttribute<AbstractOrder, Currency> currency;
	public static volatile SingularAttribute<AbstractOrder, User> customer;
	public static volatile SingularAttribute<AbstractOrder, User> user;
	public static volatile SingularAttribute<AbstractOrder, BigDecimal> discountAmount;
	public static volatile SingularAttribute<AbstractOrder, Double> discountPerc;
	public static volatile SingularAttribute<AbstractOrder, BigDecimal> orderAmount;
	public static volatile SingularAttribute<AbstractOrder, String> status;
	public static volatile SingularAttribute<AbstractOrder, Store> store;
	public static volatile SingularAttribute<AbstractOrder, BigDecimal> totalProduct;
	public static volatile SingularAttribute<AbstractOrder, BigDecimal> totalService;
	public static volatile SingularAttribute<AbstractOrder, BigDecimal> totalShipping;
	public static volatile SingularAttribute<AbstractOrder, BigDecimal> totalTax;
	public static volatile SetAttribute<AbstractOrder, Orderitem> orderitems;
	public static volatile SingularAttribute<AbstractOrder, Shipmode> shipmode;
	public static volatile SetAttribute<AbstractOrder, OrdersAttribute> ordersAttributes;
	public static volatile SetAttribute<AbstractOrder, Suborder> suborders;
	public static volatile SingularAttribute<AbstractOrder, Address> billingAddress;
	public static volatile SingularAttribute<AbstractOrder, Address> shippingAddress;
}
