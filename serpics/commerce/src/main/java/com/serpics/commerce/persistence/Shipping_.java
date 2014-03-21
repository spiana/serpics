package com.serpics.commerce.persistence;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-21T15:49:23.286+0100")
@StaticMetamodel(Shipping.class)
public class Shipping_ {
	public static volatile SingularAttribute<Shipping, Long> shippingId;
	public static volatile SingularAttribute<Shipping, String> currency;
	public static volatile SingularAttribute<Shipping, BigDecimal> rangestart;
	public static volatile SingularAttribute<Shipping, BigDecimal> value;
	public static volatile SingularAttribute<Shipping, Shipmode> shipmode;
}
