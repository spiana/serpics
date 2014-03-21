package com.serpics.membership.persistence;

import com.serpics.membership.AddressType;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-21T15:46:21.336+0100")
@StaticMetamodel(AbstractAddress.class)
public class AbstractAddress_ {
	public static volatile SingularAttribute<AbstractAddress, Long> addressId;
	public static volatile SingularAttribute<AbstractAddress, String> firstname;
	public static volatile SingularAttribute<AbstractAddress, String> lastname;
	public static volatile SingularAttribute<AbstractAddress, String> company;
	public static volatile SingularAttribute<AbstractAddress, String> email;
	public static volatile SingularAttribute<AbstractAddress, String> address1;
	public static volatile SingularAttribute<AbstractAddress, String> address2;
	public static volatile SingularAttribute<AbstractAddress, String> address3;
	public static volatile SingularAttribute<AbstractAddress, String> zipcode;
	public static volatile SingularAttribute<AbstractAddress, String> city;
	public static volatile SingularAttribute<AbstractAddress, String> region;
	public static volatile SingularAttribute<AbstractAddress, String> country;
	public static volatile SingularAttribute<AbstractAddress, String> vatcode;
	public static volatile SingularAttribute<AbstractAddress, String> phone;
	public static volatile SingularAttribute<AbstractAddress, String> mobile;
	public static volatile SingularAttribute<AbstractAddress, String> fax;
	public static volatile SingularAttribute<AbstractAddress, String> field1;
	public static volatile SingularAttribute<AbstractAddress, String> field2;
	public static volatile SingularAttribute<AbstractAddress, Float> field3;
	public static volatile SingularAttribute<AbstractAddress, Long> field4;
	public static volatile SingularAttribute<AbstractAddress, AddressType> flag;
}
