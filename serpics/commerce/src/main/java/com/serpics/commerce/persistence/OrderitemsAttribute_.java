package com.serpics.commerce.persistence;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-21T15:49:23.214+0100")
@StaticMetamodel(OrderitemsAttribute.class)
public class OrderitemsAttribute_ {
	public static volatile SingularAttribute<OrderitemsAttribute, Long> attributeId;
	public static volatile SingularAttribute<OrderitemsAttribute, BigInteger> baseAttributesId;
	public static volatile SingularAttribute<OrderitemsAttribute, Double> sequence;
	public static volatile SingularAttribute<OrderitemsAttribute, Orderitem> orderitem;
	public static volatile SingularAttribute<OrderitemsAttribute, OrderitemsAttributeValueDatetime> orderitemsAttributeValueDatetime;
	public static volatile SingularAttribute<OrderitemsAttribute, OrderitemsAttributeValueDecimal> orderitemsAttributeValueDecimal;
	public static volatile SingularAttribute<OrderitemsAttribute, OrderitemsAttributeValueLong> orderitemsAttributeValueLong;
	public static volatile SingularAttribute<OrderitemsAttribute, OrderitemsAttributeValueText> orderitemsAttributeValueText;
	public static volatile SingularAttribute<OrderitemsAttribute, OrderitemsAttributeValueVarchar> orderitemsAttributeValueVarchar;
}
