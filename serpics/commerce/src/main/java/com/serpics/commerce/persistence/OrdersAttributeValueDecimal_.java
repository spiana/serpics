package com.serpics.commerce.persistence;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-21T15:49:23.268+0100")
@StaticMetamodel(OrdersAttributeValueDecimal.class)
public class OrdersAttributeValueDecimal_ {
	public static volatile SingularAttribute<OrdersAttributeValueDecimal, Long> attributeId;
	public static volatile SingularAttribute<OrdersAttributeValueDecimal, BigDecimal> value;
	public static volatile SingularAttribute<OrdersAttributeValueDecimal, OrdersAttribute> ordersAttribute;
}
