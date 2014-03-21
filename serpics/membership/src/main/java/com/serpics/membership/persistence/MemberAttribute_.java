package com.serpics.membership.persistence;

import com.serpics.base.persistence.BaseAttribute;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-21T15:46:21.379+0100")
@StaticMetamodel(MemberAttribute.class)
public class MemberAttribute_ {
	public static volatile SingularAttribute<MemberAttribute, Long> attributeId;
	public static volatile SingularAttribute<MemberAttribute, BaseAttribute> baseAttribute;
	public static volatile SingularAttribute<MemberAttribute, Double> sequence;
	public static volatile SingularAttribute<MemberAttribute, Member> member;
	public static volatile SingularAttribute<MemberAttribute, MemberAttributeValueDatetime> memberAttributeValueDatetime;
	public static volatile SingularAttribute<MemberAttribute, MemberAttributeValueDecimal> memberAttributeValueDecimal;
	public static volatile SingularAttribute<MemberAttribute, MemberAttributeValueLong> memberAttributeValueLong;
	public static volatile SingularAttribute<MemberAttribute, MemberAttributeValueText> memberAttributeValueText;
	public static volatile SingularAttribute<MemberAttribute, MemberAttributeValueVarchar> memberAttributeValueVarchar;
}
