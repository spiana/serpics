package com.serpics.commerce.persistence;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-21T15:49:23.284+0100")
@StaticMetamodel(Shipmodelookup.class)
public class Shipmodelookup_ {
	public static volatile SingularAttribute<Shipmodelookup, Long> shipmodelookupId;
	public static volatile SingularAttribute<Shipmodelookup, BigInteger> countriesId;
	public static volatile SingularAttribute<Shipmodelookup, BigInteger> geocodeId;
	public static volatile SingularAttribute<Shipmodelookup, BigInteger> regionsId;
	public static volatile SingularAttribute<Shipmodelookup, BigInteger> storeId;
	public static volatile SingularAttribute<Shipmodelookup, String> zipcode;
	public static volatile SingularAttribute<Shipmodelookup, Shipmode> shipmode;
}
