package com.serpics.membership.services;

import static com.serpics.membership.repositories.UserSpecification.isUserInStore;
import static org.springframework.data.jpa.domain.Specifications.where;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.core.data.Repository;
import com.serpics.core.datatype.UserRegisterType;
import com.serpics.core.datatype.UserType;
import com.serpics.core.service.AbstractEntityService;
import com.serpics.core.service.AbstractService;
import com.serpics.core.service.EntityService;
import com.serpics.core.session.SessionContext;
import com.serpics.membership.persistence.MembersRole;
import com.serpics.membership.persistence.MembersRolePK;
import com.serpics.membership.persistence.PermanentAddress;
import com.serpics.membership.persistence.Role;
import com.serpics.membership.persistence.Store;
import com.serpics.membership.persistence.User;
import com.serpics.membership.persistence.UserStoreRelation;
import com.serpics.membership.persistence.UsersReg;
import com.serpics.membership.repositories.PermanentAddressRepository;
import com.serpics.membership.repositories.UserRegrepository;
import com.serpics.membership.repositories.UserRepository;

@Service("userService")
@Scope("store")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class UserServiceImpl extends AbstractEntityService<User, Long> implements UserService {

	@Resource
	UserRepository userRepository;

	@Resource
	UserRegrepository userRegrepository;

	@Resource(name = "permanentAddressRepository")
	PermanentAddressRepository addressRepository;

	private User mergeUserRoles(User user, Set<MembersRole> roles) {
		Store s = (Store) getCurrentContext().getStoreRealm();
		for (MembersRole memberRole : roles) {
			if (memberRole.getId() == null) {
				if (memberRole.getStore() == null)
					memberRole.setStore(s);

				memberRole.setMember(user);
				MembersRolePK pk = new MembersRolePK();
				pk.setStoresStoreId(s.getStoreId());
				pk.setMemberId(user.getMemberId());
				pk.setRoleId(memberRole.getRole().getRoleId());
				memberRole.setId(pk);
			}
			user.getMembersRoles().add(memberRole);
		}

		return user;
	}

	
	@Override
	@Transactional
	public User create(User user) {

		if (user.getUserType().equals(UserType.ANONYMOUS))
			user.setUserType(UserType.GUEST);


		for (PermanentAddress address : user.getPermanentAddresses()) {
			address.setMember(user);
		}	
		
		Set<MembersRole> roles = user.getMembersRoles();
		user.setMembersRoles(new HashSet<MembersRole>());
		
		user = userRepository.saveAndFlush(user);
		user = mergeUserRoles(user, roles);

		UserStoreRelation r = new UserStoreRelation((Store) getCurrentContext()
				.getStoreRealm(), user);
		r.setUpdated(new Date());
		user.getStoreRelation().add(r);
		return userRepository.saveAndFlush(user);

	}



	@Override
	@Transactional
	public User update(User user) {
		user = mergeUserRoles(user , user.getMembersRoles());
		for (PermanentAddress address : user.getPermanentAddresses()) {
			if (address.getMember() == null) address.setMember(user);
		}	
		if (user.getUserReg() != null)
			user.getUserReg().setUser(user);
		return userRepository.saveAndFlush(user);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public User registerUser(User user, UsersReg reg,
			PermanentAddress primaryAddress) {
		if (primaryAddress != null)
			user.setPrimaryAddress(primaryAddress);
		create(user);
		if (user.getUserType().equals(UserType.ANONYMOUS)
				|| user.getUserType().equals(UserType.GUEST))
			user.setUserType(UserType.REGISTERED);
		reg.setUserId(user.getMemberId());
		if (reg.getStatus() == null)
			reg.setStatus(UserRegisterType.ACTIVE);
		reg.setUser(user);
		user.setUserReg(reg);
		return userRepository.saveAndFlush(user);
	}

	
	private Specification storeFilterSpec(){
		return isUserInStore((Store) getCurrentContext()
				.getStoreRealm());
	}


	@Override
	public List<UsersReg> findByexample(UsersReg example) {
		return userRegrepository.findAll(where(userRegrepository
				.makeSpecification(example)));
	}



	@Override
	@Transactional
	public User addAddress(PermanentAddress address, User user) {
		Assert.notNull(user);
		Assert.notNull(address);
		address.setMember(user);
		user.getPermanentAddresses().add(address);
		return update(user);

	}

	@Override
	@Transactional
	public User addAddress(PermanentAddress address, Long userId) {
		User user = userRepository.findOne(userId);
		return addAddress(address, user);
	}

	@Override
	public User addRole(Role role, User user) {
		MembersRole membersRole = new MembersRole(user , role , (Store) getCurrentContext().getStoreRealm());
		user.getMembersRoles().add(membersRole);
		return userRepository.save(user);
	}





	
	@Override
	public Repository<User, Long> getEntityRepository() {
		return userRepository;
	}

	@Override
	public Specification<User> getBaseSpec() {
		return storeFilterSpec();
	}


}
