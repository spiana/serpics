package com.serpics.membership.persistence;

import com.serpics.membership.UserType;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-21T15:46:21.401+0100")
@StaticMetamodel(User.class)
public class User_ extends Member_ {
	public static volatile SingularAttribute<User, String> firstname;
	public static volatile SingularAttribute<User, String> lastname;
	public static volatile SingularAttribute<User, String> phone;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, BigInteger> field4;
	public static volatile SingularAttribute<User, UserType> userType;
	public static volatile SingularAttribute<User, Date> lastVisit;
	public static volatile SingularAttribute<User, Long> storeId;
	public static volatile SetAttribute<User, UserStoreRelation> storeRelation;
}
