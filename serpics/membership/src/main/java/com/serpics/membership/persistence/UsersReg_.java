package com.serpics.membership.persistence;

import com.serpics.membership.UserRegStatus;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-21T15:46:21.404+0100")
@StaticMetamodel(UsersReg.class)
public class UsersReg_ extends User_ {
	public static volatile SingularAttribute<UsersReg, String> alternateEmail;
	public static volatile SingularAttribute<UsersReg, String> changeanswer;
	public static volatile SingularAttribute<UsersReg, String> changequestion;
	public static volatile SingularAttribute<UsersReg, Timestamp> created;
	public static volatile SingularAttribute<UsersReg, String> dn;
	public static volatile SingularAttribute<UsersReg, String> field1;
	public static volatile SingularAttribute<UsersReg, String> field2;
	public static volatile SingularAttribute<UsersReg, Date> lastLogin;
	public static volatile SingularAttribute<UsersReg, BigInteger> localeId;
	public static volatile SingularAttribute<UsersReg, String> logonid;
	public static volatile SingularAttribute<UsersReg, String> password;
	public static volatile SingularAttribute<UsersReg, Date> passwordChange;
	public static volatile SingularAttribute<UsersReg, UserRegStatus> status;
}
