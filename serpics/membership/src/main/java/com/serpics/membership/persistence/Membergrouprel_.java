package com.serpics.membership.persistence;

import com.serpics.membership.Member2GroupRelType;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-21T15:46:21.389+0100")
@StaticMetamodel(Membergrouprel.class)
public class Membergrouprel_ {
	public static volatile SingularAttribute<Membergrouprel, MembgrouprelPK> id;
	public static volatile SingularAttribute<Membergrouprel, Member2GroupRelType> status;
	public static volatile SingularAttribute<Membergrouprel, Date> validFrom;
	public static volatile SingularAttribute<Membergrouprel, Date> validTo;
	public static volatile SingularAttribute<Membergrouprel, Membergroup> membergroup;
	public static volatile SingularAttribute<Membergrouprel, Member> member;
}
