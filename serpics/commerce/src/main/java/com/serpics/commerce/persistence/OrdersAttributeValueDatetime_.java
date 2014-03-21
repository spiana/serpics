package com.serpics.commerce.persistence;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-21T15:49:23.234+0100")
@StaticMetamodel(OrdersAttributeValueDatetime.class)
public class OrdersAttributeValueDatetime_ {
	public static volatile SingularAttribute<OrdersAttributeValueDatetime, Long> attributeId;
	public static volatile SingularAttribute<OrdersAttributeValueDatetime, Timestamp> value;
	public static volatile SingularAttribute<OrdersAttributeValueDatetime, OrdersAttribute> ordersAttribute;
}
