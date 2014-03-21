package com.serpics.membership.persistence;

import com.serpics.base.persistence.Currency;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-21T15:46:21.400+0100")
@StaticMetamodel(Store.class)
public class Store_ extends Member_ {
	public static volatile SingularAttribute<Store, String> name;
	public static volatile SingularAttribute<Store, Currency> currency;
}
