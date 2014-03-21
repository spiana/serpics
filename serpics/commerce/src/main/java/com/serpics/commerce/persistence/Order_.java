package com.serpics.commerce.persistence;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-21T15:49:23.206+0100")
@StaticMetamodel(Order.class)
public class Order_ extends AbstractOrder_ {
	public static volatile SingularAttribute<Order, String> orderNumber;
	public static volatile SingularAttribute<Order, Date> created;
	public static volatile SingularAttribute<Order, BigDecimal> payAmount;
	public static volatile SetAttribute<Order, Orderpayment> orderpayments;
}
