package com.serpics.membership.persistence;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-21T15:46:21.383+0100")
@StaticMetamodel(MemberAttributeValueLong.class)
public class MemberAttributeValueLong_ {
	public static volatile SingularAttribute<MemberAttributeValueLong, Long> attributeId;
	public static volatile SingularAttribute<MemberAttributeValueLong, Timestamp> updated;
	public static volatile SingularAttribute<MemberAttributeValueLong, String> value;
	public static volatile SingularAttribute<MemberAttributeValueLong, MemberAttribute> memberAttribute;
}
