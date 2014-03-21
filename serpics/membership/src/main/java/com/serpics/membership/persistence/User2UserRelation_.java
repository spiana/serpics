package com.serpics.membership.persistence;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-21T15:46:21.402+0100")
@StaticMetamodel(User2UserRelation.class)
public class User2UserRelation_ extends MemberRelation_ {
	public static volatile SingularAttribute<User2UserRelation, Integer> relationType;
	public static volatile SingularAttribute<User2UserRelation, User> user;
	public static volatile SingularAttribute<User2UserRelation, User> customer;
}
