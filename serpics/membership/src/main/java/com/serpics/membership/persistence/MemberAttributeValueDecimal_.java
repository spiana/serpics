package com.serpics.membership.persistence;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-21T15:46:21.382+0100")
@StaticMetamodel(MemberAttributeValueDecimal.class)
public class MemberAttributeValueDecimal_ {
	public static volatile SingularAttribute<MemberAttributeValueDecimal, Long> paramersId;
	public static volatile SingularAttribute<MemberAttributeValueDecimal, Timestamp> updated;
	public static volatile SingularAttribute<MemberAttributeValueDecimal, BigDecimal> value;
	public static volatile SingularAttribute<MemberAttributeValueDecimal, MemberAttribute> memberAttribute;
}
