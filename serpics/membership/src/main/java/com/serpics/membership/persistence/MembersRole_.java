package com.serpics.membership.persistence;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-21T15:46:21.393+0100")
@StaticMetamodel(MembersRole.class)
public class MembersRole_ {
	public static volatile SingularAttribute<MembersRole, MembersRolePK> id;
	public static volatile SingularAttribute<MembersRole, Member> member;
	public static volatile SingularAttribute<MembersRole, Role> role;
	public static volatile SingularAttribute<MembersRole, Store> store;
}
