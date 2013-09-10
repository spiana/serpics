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


	@Resource(name="userService")
	private UserService userService;

	@Resource
	private MembershipHook membershipHook;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public User createUser(User user) {
		return userService.create(user);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<User> fetchAllUser(Pageable pageable) {
		return userService.findAll(pageable);
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
		return userService.update(user);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED)
	public Store fetchStoreByName(String name) {
		return storeRepository.findByname(name);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public User registerUser(User user, UsersReg reg, PermanentAddress primaryAddress) {
		return userService.registerUser(user, reg, primaryAddress);
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
		return userService.findByexample(example);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<UsersReg> findbyexample(UsersReg example) {
		return userService.findByexample(example);
	}

	@Override
	public List<User> findAll() {
		return userService.findAll();
	}

	@Override
	public UserDetail createAnonymous() {
		List<User> ulist = userService.findByexample(new User());
		return ulist.get(0);
	}

}
