package com.serpics.membership.persistence;

import com.serpics.membership.MemberType;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-21T15:46:21.378+0100")
@StaticMetamodel(Member.class)
public class Member_ {
	public static volatile SingularAttribute<Member, Long> memberId;
	public static volatile SingularAttribute<Member, Date> created;
	public static volatile SingularAttribute<Member, String> field1;
	public static volatile SingularAttribute<Member, String> field2;
	public static volatile SingularAttribute<Member, Double> field3;
	public static volatile SingularAttribute<Member, MemberType> memberType;
	public static volatile SingularAttribute<Member, PrimaryAddress> primaryAddress;
	public static volatile SetAttribute<Member, PermanentAddress> permanentAddresses;
	public static volatile SetAttribute<Member, MemberAttribute> memberAttributes;
	public static volatile SetAttribute<Member, MembersRole> membersRoles;
	public static volatile SetAttribute<Member, Membergrouprel> memberGroupRel;
}
