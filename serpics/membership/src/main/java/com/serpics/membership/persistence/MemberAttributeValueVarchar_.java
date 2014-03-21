package com.serpics.membership.persistence;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-21T15:46:21.386+0100")
@StaticMetamodel(MemberAttributeValueVarchar.class)
public class MemberAttributeValueVarchar_ {
	public static volatile SingularAttribute<MemberAttributeValueVarchar, Long> paramersId;
	public static volatile SingularAttribute<MemberAttributeValueVarchar, Timestamp> updated;
	public static volatile SingularAttribute<MemberAttributeValueVarchar, String> value;
	public static volatile SingularAttribute<MemberAttributeValueVarchar, MemberAttribute> memberAttribute;
}
