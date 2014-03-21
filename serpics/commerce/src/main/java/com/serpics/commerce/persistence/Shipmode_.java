package com.serpics.commerce.persistence;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-21T15:49:23.276+0100")
@StaticMetamodel(Shipmode.class)
public class Shipmode_ {
	public static volatile SingularAttribute<Shipmode, Long> shipmodeId;
	public static volatile SingularAttribute<Shipmode, String> description;
	public static volatile SingularAttribute<Shipmode, String> name;
	public static volatile SetAttribute<Shipmode, Orderitem> orderitems;
	public static volatile SetAttribute<Shipmode, AbstractOrder> orders;
	public static volatile SetAttribute<Shipmode, ShipmodeDescr> shipmodeDescrs;
	public static volatile SetAttribute<Shipmode, Shipmodelookup> shipmodelookups;
	public static volatile SetAttribute<Shipmode, Shipping> shippings;
	public static volatile SetAttribute<Shipmode, Suborder> suborders;
}
