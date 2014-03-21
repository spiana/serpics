package com.serpics.commerce.persistence;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-21T15:49:23.289+0100")
@StaticMetamodel(Suborder.class)
public class Suborder_ {
	public static volatile SingularAttribute<Suborder, Long> subordersId;
	public static volatile SingularAttribute<Suborder, Timestamp> created;
	public static volatile SingularAttribute<Suborder, Long> customerId;
	public static volatile SingularAttribute<Suborder, Long> userId;
	public static volatile SingularAttribute<Suborder, BigDecimal> discountAmount;
	public static volatile SingularAttribute<Suborder, BigDecimal> discountPerc;
	public static volatile SingularAttribute<Suborder, BigInteger> shippingAddressId;
	public static volatile SingularAttribute<Suborder, String> status;
	public static volatile SingularAttribute<Suborder, BigDecimal> subtotalAmount;
	public static volatile SingularAttribute<Suborder, BigDecimal> subtotalProduct;
	public static volatile SingularAttribute<Suborder, BigDecimal> subtotalShipping;
	public static volatile SingularAttribute<Suborder, BigDecimal> subtotalTax;
	public static volatile SetAttribute<Suborder, Orderitem> orderitems;
	public static volatile SingularAttribute<Suborder, AbstractOrder> order;
	public static volatile SingularAttribute<Suborder, Shipmode> shipmode;
}
