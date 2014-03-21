package com.serpics.membership.persistence;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-21T15:46:21.381+0100")
@StaticMetamodel(MemberAttributeValueDatetime.class)
public class MemberAttributeValueDatetime_ {
	public static volatile SingularAttribute<MemberAttributeValueDatetime, Long> attrributeId;
	public static volatile SingularAttribute<MemberAttributeValueDatetime, Timestamp> value;
	public static volatile SingularAttribute<MemberAttributeValueDatetime, MemberAttribute> memberAttribute;
}
