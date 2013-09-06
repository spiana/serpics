package com.serpics.membership.services;

import static com.serpics.membership.repositories.UserSpecification.isUserInStore;
import static org.springframework.data.jpa.domain.Specifications.where;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.core.datatype.UserRegisterType;
import com.serpics.core.datatype.UserType;
import com.serpics.core.service.AbstractService;
import com.serpics.core.session.SessionContext;

import com.serpics.membership.persistence.PermanentAddress;
import com.serpics.membership.persistence.Store;
import com.serpics.membership.persistence.User;
import com.serpics.membership.persistence.UserStoreRelation;
import com.serpics.membership.persistence.UsersReg;
import com.serpics.membership.repositories.UserRegrepository;
import com.serpics.membership.repositories.UserRepository;


@Service("userService")
@Scope("store")
@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
public class UserServiceImpl extends AbstractService implements UserService{

	@Resource
	UserRepository userRepository;
	
	@Resource
	UserRegrepository userRegrepository;
	
	@Override
	@Transactional
	public User create(User user) {
		
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
	@Transactional
	public void delete(User user) {
		userRepository.delete(user);
	}

	@Override
	public Page<User> findAll(Pageable page) {
		return userRepository.findAll(isUserInStore((Store) getCurrentContext().getStoreRealm()),page);
	}

	@Override
	@Transactional
	public User update(User user) {
		return userRepository.save(user);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public User registerUser(User user, UsersReg reg, PermanentAddress primaryAddress) {
		if (primaryAddress != null)
			user.setPrimaryAddress(primaryAddress);
		create(user);
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
	public List<User> findAll() {
		return userRepository.findAll(isUserInStore((Store) getCurrentContext().getStoreRealm()));
	}
	
	@Override
	public List<User> findByexample(User example) {
		return userRepository.findAll(where(userRepository.makeSpecification(example)));
	}

	@Override
	public List<UsersReg> findByexample(UsersReg example) {
		return userRegrepository.findAll(where(userRegrepository.makeSpecification(example)));
	}

	@Override
	public User findOne(Long id) {
		SessionContext c = getCurrentContext();
		Assert.notNull(c, "no session context");
	
		return userRepository.findOne(id);
	}
}
