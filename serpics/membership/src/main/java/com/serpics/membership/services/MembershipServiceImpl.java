package com.serpics.membership.services;

import static com.serpics.membership.repositories.UserSpecification.isUserInStore;
import static org.springframework.data.jpa.domain.Specifications.where;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.core.SerpicsException;
import com.serpics.core.datatype.UserRegisterType;
import com.serpics.core.datatype.UserType;
import com.serpics.core.security.UserDetail;
import com.serpics.core.service.AbstractService;
import com.serpics.core.service.Membership;
import com.serpics.membership.hooks.MembershipHook;
import com.serpics.membership.persistence.PermanentAddress;
import com.serpics.membership.persistence.Store;
import com.serpics.membership.persistence.User;
import com.serpics.membership.persistence.UserStoreRelation;
import com.serpics.membership.persistence.UsersReg;
import com.serpics.membership.repositories.StoreRepository;
import com.serpics.membership.repositories.UserRegrepository;
import com.serpics.membership.repositories.UserRepository;

@Service("memberService")
public class MembershipServiceImpl extends AbstractService implements MembershipService, Membership {

	@Resource
	private StoreRepository storeRepository;

	@Resource
	private UserRepository userRepository;

	@Resource
	private UserRegrepository userRegRepository;

	@Resource
	private MembershipHook membershipHook;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public User createUser(User user) {
		if (user.getUserType().equals(UserType.ANONYMOUS))
			user.setUserType(UserType.GUEST);
		if (user.getUuid() == null)
			user.setUuid(UUID.randomUUID().toString());
		user = userRepository.saveAndFlush(user);
		UserStoreRelation r = new UserStoreRelation((Store) getCurrentContext().getStoreRealm(), user);
		r.setUpdated(new Date());
		user.getStoreRelation().add(r);
		return userRepository.saveAndFlush(user);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<User> fetchAllUser(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public User createUser(User user, PermanentAddress primaryAddress) {
		user.setPrimaryAddress(primaryAddress);
		return createUser(user);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public User updateUser(User user) {
		return userRepository.saveAndFlush(user);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED)
	public Store fetchStoreByUUID(String uuid) {
		return storeRepository.findByuuid(uuid);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public User registerUser(User user, UsersReg reg, PermanentAddress primaryAddress) {
		user = createUser(user, primaryAddress);
		if (user.getUserType().equals(UserType.ANONYMOUS) || user.getUserType().equals(UserType.GUEST))
			user.setUserType(UserType.REGISTERED);

		reg.setUserId(user.getMemberId());
		if (reg.getStatus() == null)
			reg.setStatus(UserRegisterType.ACTIVE);
		reg.setUser(user);
		user.setUserReg(reg);
		return userRepository.saveAndFlush(user);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Store createStore(Store store) {
		return storeRepository.saveAndFlush(store);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserDetail login(String username, char[] password) throws SerpicsException {

		return membershipHook.login((Store) getCurrentContext().getStoreRealm(), username, password);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<User> findbyexample(User example) {

		try {
			return userRepository.findAll(where(userRepository.makeSpecification(example)).and(
					isUserInStore((Store) getCurrentContext().getStoreRealm())));
		} catch (Exception e) {
			return new ArrayList<User>(0);
		}

	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<UsersReg> findbyexample(UsersReg example) {
		try {
			return userRegRepository.findAll(where(userRegRepository.makeSpecification(example)));
		} catch (Exception e) {
			return new ArrayList<UsersReg>(0);
		}
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll(isUserInStore((Store) getCurrentContext().getStoreRealm()));
	}

	@Override
	public UserDetail createAnonymous() {
		List<User> ulist = userRepository.findAll(where(userRepository.makeSpecification(new User())));
		return ulist.get(0);
	}

}
