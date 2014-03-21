package com.serpics.commerce.persistence;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-21T15:49:23.229+0100")
@StaticMetamodel(Orderpayment.class)
public class Orderpayment_ {
	public static volatile SingularAttribute<Orderpayment, Long> orderpaymentId;
	public static volatile SingularAttribute<Orderpayment, BigDecimal> amount;
	public static volatile SingularAttribute<Orderpayment, Long> paymethod;
	public static volatile SingularAttribute<Orderpayment, Order> order;
}
