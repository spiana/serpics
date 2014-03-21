package com.serpics.commerce.persistence;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-21T15:49:23.231+0100")
@StaticMetamodel(OrdersAttribute.class)
public class OrdersAttribute_ {
	public static volatile SingularAttribute<OrdersAttribute, Long> attributeId;
	public static volatile SingularAttribute<OrdersAttribute, BigInteger> baseAttributesId;
	public static volatile SingularAttribute<OrdersAttribute, Double> sequence;
	public static volatile SingularAttribute<OrdersAttribute, AbstractOrder> order;
	public static volatile SingularAttribute<OrdersAttribute, OrdersAttributeValueDatetime> ordersAttributeValueDatetime;
	public static volatile SingularAttribute<OrdersAttribute, OrdersAttributeValueDecimal> ordersAttributeValueDecimal;
	public static volatile SingularAttribute<OrdersAttribute, OrdersAttributeValueLong> ordersAttributeValueLong;
	public static volatile SingularAttribute<OrdersAttribute, OrdersAttributeValueText> ordersAttributeValueText;
	public static volatile SingularAttribute<OrdersAttribute, OrdersAttributeValueVarchar> ordersAttributeValueVarchar;
}
