package com.serpics.membership.persistence;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-21T15:46:21.388+0100")
@StaticMetamodel(Membergroup.class)
public class Membergroup_ {
	public static volatile SingularAttribute<Membergroup, Long> membergroupsId;
	public static volatile SingularAttribute<Membergroup, String> name;
	public static volatile SingularAttribute<Membergroup, String> description;
	public static volatile SingularAttribute<Membergroup, Store> store;
	public static volatile SetAttribute<Membergroup, Membergrouprel> groupRelation;
}
