package com.serpics.commerce.persistence;

import com.serpics.catalog.persistence.AbstractProduct;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-21T15:49:23.212+0100")
@StaticMetamodel(Orderitem.class)
public class Orderitem_ {
	public static volatile SingularAttribute<Orderitem, Long> orderitemsId;
	public static volatile SingularAttribute<Orderitem, Double> discountAmount;
	public static volatile SingularAttribute<Orderitem, Double> discountPerc;
	public static volatile SingularAttribute<Orderitem, Double> discountPerc1;
	public static volatile SingularAttribute<Orderitem, Double> discountPerc2;
	public static volatile SingularAttribute<Orderitem, Double> quantity;
	public static volatile SingularAttribute<Orderitem, Long> shippingAddressId;
	public static volatile SingularAttribute<Orderitem, AbstractProduct> product;
	public static volatile SingularAttribute<Orderitem, String> sku;
	public static volatile SingularAttribute<Orderitem, Double> skuCost;
	public static volatile SingularAttribute<Orderitem, String> skuDescription;
	public static volatile SingularAttribute<Orderitem, Double> skuNetPrice;
	public static volatile SingularAttribute<Orderitem, Double> skuPrice;
	public static volatile SingularAttribute<Orderitem, Double> shippingCost;
	public static volatile SingularAttribute<Orderitem, AbstractOrder> order;
	public static volatile SingularAttribute<Orderitem, Shipmode> shipmode;
	public static volatile SingularAttribute<Orderitem, Suborder> suborder;
	public static volatile SetAttribute<Orderitem, OrderitemsAttribute> orderitemsAttributes;
}
