package com.serpics.membership.persistence;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-21T15:46:21.391+0100")
@StaticMetamodel(MemberRelation.class)
public class MemberRelation_ {
	public static volatile SingularAttribute<MemberRelation, MemberRelationPK> id;
	public static volatile SingularAttribute<MemberRelation, String> field1;
	public static volatile SingularAttribute<MemberRelation, String> field2;
	public static volatile SingularAttribute<MemberRelation, BigDecimal> field3;
	public static volatile SingularAttribute<MemberRelation, Double> precedence;
}
