/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.membership.services;

import static org.springframework.data.jpa.domain.Specifications.where;

import java.nio.file.attribute.UserPrincipal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.base.data.model.Store;
import com.serpics.base.data.repositories.CountryRepository;
import com.serpics.base.data.repositories.StoreRepository;
import com.serpics.core.data.Repository;
import com.serpics.membership.UserRegStatus;
import com.serpics.membership.UserType;
import com.serpics.membership.data.model.BillingAddress;
import com.serpics.membership.data.model.Membergroup;
import com.serpics.membership.data.model.PermanentAddress;
import com.serpics.membership.data.model.PrimaryAddress;
import com.serpics.membership.data.model.Role;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.data.repositories.BillingAddressRepository;
import com.serpics.membership.data.repositories.MemberGroupRepository;
import com.serpics.membership.data.repositories.MembersRoleRepository;
import com.serpics.membership.data.repositories.PermanentAddressRepository;
import com.serpics.membership.data.repositories.PrimaryAddressRepository;
import com.serpics.membership.data.repositories.UserRepository;
import com.serpics.membership.data.repositories.UserSpecification;
import com.serpics.membership.data.repositories.UserregRepository;

//@StoreService("userService" )
@Service("userService")
@Scope("store")
@Transactional(readOnly = true)
public class UserServiceImpl extends AbstractMemberService<User, Long> implements UserService {

    @Resource
    UserRepository userRepository;
    @Resource
    UserregRepository userRegrepository;
    
    @Resource
    StoreRepository storeRepository;

    @Resource
    CountryRepository countryRepository;
    
    @Autowired
    MemberGroupRepository memberGroupRepository;

    @Resource(name = "primaryAddressRepository")
    PrimaryAddressRepository primaryAddressRepository;
    
    @Resource
    BillingAddressRepository billingAddressRepository;
    
    @Autowired
    MembersRoleRepository membersRoleRepository;
	@Resource(name = "permanentAddressRepository")
	PermanentAddressRepository addressRepository;


    @Override
    @Transactional
    public User create(User user) {

        if ((user.getUserType() == null) || (user.getUserType().equals(UserType.ANONYMOUS)))
            user.setUserType(UserType.GUEST);

        user = adjustAddresses(user);
        user = adjustMemberRoles(user);
        user.setCreated(new Date());
        final Store currentStore = (Store) getCurrentContext().getStoreRealm();
        final Store _s = storeRepository.findOne(currentStore.getId());
        user.getStores().add(_s);
        return userRepository.saveAndFlush(user);

    }

    @Override
    @Transactional
    public User update(User user) {
        user = adjustAddresses(user);
        user = adjustMemberRoles(user);

        return userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public BillingAddress updateBillingAddress(BillingAddress ba) {
    	return billingAddressRepository.saveAndFlush(ba);
    }
    
    @Override
    @Transactional
    public PrimaryAddress updatePrimaryAddress(PrimaryAddress pa) {
    	return primaryAddressRepository.saveAndFlush(pa);
    }
    
    @Override
    @Transactional
    public PermanentAddress updatePermanentAddress(PermanentAddress pe) {
    	return addressRepository.saveAndFlush(pe);
    }
    
    @Override
    @Transactional
    public UsersReg registerUser(final UsersReg reg, final PrimaryAddress primaryAddress) {
        final User u = create(reg);

        if (reg.getUserType().equals(UserType.ANONYMOUS) || reg.getUserType().equals(UserType.GUEST))
            reg.setUserType(UserType.REGISTERED);

        if (reg.getStatus() == null)
            reg.setStatus(UserRegStatus.ACTIVE);

        userRegrepository.saveAndFlush(reg);
        
        if (primaryAddress != null) {
            primaryAddress.setMember(u);
            primaryAddressRepository.saveAndFlush(primaryAddress);
            
            reg.setPrimaryAddress(primaryAddress);
        }
        
       
        return userRegrepository.saveAndFlush(reg);
    }

  

    @Override
    public List<UsersReg> findByexample(final UsersReg example) {
        return userRegrepository.findAll(where(userRegrepository.makeSpecification(example)));
    }

    @Override
    public UsersReg findByRegUUID(final String uuid) {
        return userRegrepository.findByUUID(uuid);
    }
    
    @Override
    public Collection<Role> getUserRoles(final User user, final Store store) {

        return null;
    }

    @Override
    public User findAnonymous() {
        final List<User> users = userRepository.findAnonymous();
        Assert.notEmpty(users);
        return users.get(0);
    }
    
    @Override
    public UsersReg findByLogonid(String logonid) {  
       UsersReg users = userRegrepository.findBylogonid(logonid);
       //Assert.notNull(users);
       return users;
    }

    @Override
    public Set<PermanentAddress> getUserAddress(final User user) {
        final User _u = userRegrepository.findOne(user.getId());
        _u.getPermanentAddresses().size();
        return _u.getPermanentAddresses();
    }

    @Override
    public Set<Membergroup> getUserGroups(final User user) {
        return memberGroupRepository.findMembergroupByUser(user, (Store) getCurrentContext().getStoreRealm());
    }

	@Override
	public Repository<User, Long> getEntityRepository() {
		return userRepository;
	}

	@Override
	public User getCurrentCustomer() {
		User u = (User) getCurrentContext().getCustomer();
		User _u  = userRepository.findOne(u.getId());
//		userRepository.detach(_u);
		return _u;
	}
	
	@Override
	public UserPrincipal getCurrentUser() {
		return (UserPrincipal) getCurrentContext().getUserPrincipal();
	}
	
	@Override
	public void setCurrentCustomer(User user) {
		getCurrentContext().setCustomer(user);
	}

	@Override
	@Transactional
	public BillingAddress addBillingAddress(BillingAddress address, User user) {
		Assert.notNull(user);
		Assert.notNull(address);
		address.setUser(user);
		if (user.getBillingAddress() != null){
			billingAddressRepository.delete(user.getBillingAddress());
		}
		user.setBillingAddress(address);
		return billingAddressRepository.saveAndFlush(address);
	}
	
	@Override
	@Transactional 
	public void addPermanentAddress(PermanentAddress address, User user) {
		Assert.notNull(user);
		Assert.notNull(address);
		address.setMember(user);
		addressRepository.saveAndFlush(address);
		user.getPermanentAddresses().add(address);
	}
	
	
	@Override
	@Transactional
	public void deleteBillingAddress(User user) {
		Assert.notNull(user);
		Assert.notNull(user.getBillingAddress());
		BillingAddress address = user.getBillingAddress();
		billingAddressRepository.delete(address);
		user.setBillingAddress(null);
	}
	
	
	@Override
	@Transactional
	public void deletePermanentAddress(User user, PermanentAddress address) {
		Assert.notNull(user);
		Assert.notNull(user.getPermanentAddresses().contains(address));
		addressRepository.delete(address);
		user.getPermanentAddresses().remove(address);
		
	}

	@Override
	public Page<User> findByEmail(final String email , final Pageable page) {
		return  userRepository.findAll(new Specification<User>() {
			@Override
			public Predicate toPredicate(final Root<User> root, final CriteriaQuery<?> query,
					final CriteriaBuilder cb) {
				Expression<String> e = null;
				Predicate nameLike = null;
				e = root.get("email");
				nameLike = cb.like(e, "%" + email + "%");

				return nameLike;
			}
		}, page);
	
	}

	@Override
	public Page<User> findByUserType(UserType type , Pageable page) {
		return userRepository.findAll(UserSpecification.findByUserType(type) , page);
		
	}

	@Override
	public List<User> findByexample(User example) {
		return userRepository.findAll(where(userRepository.makeSpecification(example)));
	}
}
