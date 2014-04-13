package com.serpics.membership.persistence;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-21T15:46:21.385+0100")
@StaticMetamodel(MemberAttributeValueText.class)
public class MemberAttributeValueText {
	public static volatile SingularAttribute<MemberAttributeValueText, Long> paramersId;
	public static volatile SingularAttribute<MemberAttributeValueText, Timestamp> updated;
	public static volatile SingularAttribute<MemberAttributeValueText, String> value;
	public static volatile SingularAttribute<MemberAttributeValueText, MemberAttribute> memberAttribute;
}
