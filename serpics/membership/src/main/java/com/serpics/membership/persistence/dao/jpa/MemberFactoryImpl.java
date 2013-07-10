package com.serpics.membership.persistence.dao.jpa;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.serpics.core.datatype.AddressType;
import com.serpics.core.datatype.MemberType;
import com.serpics.core.datatype.UserType;
import com.serpics.core.persistence.dao.jpa.AbstractFactory;
import com.serpics.membership.persistence.AbstractAddress;
import com.serpics.membership.persistence.Member;
import com.serpics.membership.persistence.PermanentAddress;
import com.serpics.membership.persistence.Store;
import com.serpics.membership.persistence.User;
import com.serpics.membership.persistence.UserStoreRelation;
import com.serpics.membership.persistence.UsersReg;
import com.serpics.membership.persistence.dao.MemberFactory;

@Deprecated
@Repository(value = "memberFactory")
public class MemberFactoryImpl extends AbstractFactory implements MemberFactory {

	private static transient Logger logger = LoggerFactory.getLogger(MemberFactoryImpl.class);

	@Override
	public User createUser(User user, Long storeId) {
		Store store = em.find(Store.class, storeId);
		user = createUser(user);
		user.setStoreId(storeId);
		UserStoreRelation mrel = new UserStoreRelation(store, user);
		mrel.setUpdated(new Timestamp(new Date().getTime()));
		user.getStoreRelation().add(mrel);

		return em.merge(user);
	}

	@Override
	public User createUser(User user) {
		user.setMemberType(MemberType.USER);
		user.setCreated(new Timestamp(new Date().getTime()));
		if (user.getUuid() == null)
			user.setUuid(UUID.randomUUID().toString());
		if (user.getUserType() == null)
			user.setUserType(UserType.ANONYMOUS);

		return em.merge(user);

	}

	@Override
	public Store createStore(Store store) {
		store.setMemberType(MemberType.STORE);
		store.setCreated(new Timestamp(new Date().getTime()));
		store.setUpdated(new Timestamp(new Date().getTime()));
		em.persist(store);
		return store;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> fetchAllMember() {
		Query q = em.createQuery("from Member");
		return q.getResultList();
	}

	@Override
	public List<User> fetchAllUser(Long storeId) {
		return fetchAllUser(storeId, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> fetchAllUser(Long storeId, int firstRow, int maxResult) {
		Query q = em.createNativeQuery("SELECT u.* FROM users u where u.parent_member_id=:store_id", User.class);
		q.setParameter("store_id", storeId);

		if (firstRow > -1) {
			q.setFirstResult(firstRow);
			q.setMaxResults(maxResult);
		}

		return q.getResultList();
	}

	@Override
	public List<User> findUser(User user) {

		try {
			return null; // findByExample(User.class, user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ArrayList<User>();

	}

	@SuppressWarnings("unchecked")
	@Override
	public User fetchUser(User user) {

		return em.find(User.class, user.getUserId());
	}

	@Override
	public PermanentAddress addUserAddress(User user, PermanentAddress address) {

		user = em.find(User.class, user.getUserId());
		try {
			Query q = em.createQuery("select a from Address a where a.nickname = :nickname and a.flag='P'");
			q.setParameter("nickname", address.getNickname());
			// q.setParameter("member_id", u.getUserId());
			AbstractAddress oldAddress = (AbstractAddress) q.getSingleResult();
			if (oldAddress != null) {
				oldAddress.setFlag(AddressType.TEMPORARY);
				em.merge(oldAddress);
			}
		} catch (NoResultException e) {

		}
		address.setMember(user);
		em.persist(address);
		return address;
	}

	@Override
	public AbstractAddress updateUserAddress(AbstractAddress address, boolean force) {
		if (force) {
			AbstractAddress oldAddress = em.find(AbstractAddress.class, address.getAddressId());
			oldAddress.setFlag(AddressType.TEMPORARY);
			em.merge(oldAddress);
			address.setFlag(AddressType.PERMANENT);
			address.setAddressId(null);
			em.persist(address);
		} else
			em.merge(address);

		return address;
	}

	@Override
	public User fetchUserbylogin(String login) {
		Query q = em.createQuery("from UsersReg where logonid=:logonid");
		q.setParameter("logonid", login);
		UsersReg ur = (UsersReg) q.getSingleResult();
		if (ur != null) {
			return ur.getUser();
		}

		return null;
	}

	@Override
	public Store fetchStoreByUUID(String uuid) {
		Query q = em.createQuery("select s from Store s where s.uuid = :uuid");
		q.setParameter("uuid", uuid);

		return (Store) q.getSingleResult();
	}

	@Override
	public User fetchUserByUUID(String uuid) {
		Query q = em.createQuery("select u from User u where u.uuid = :uuid");
		q.setParameter("uuid", uuid);

		return (User) q.getSingleResult();
	}

	@Override
	public List<User> findByname(String lastName, long storeId) {
		return null;
	}

}
