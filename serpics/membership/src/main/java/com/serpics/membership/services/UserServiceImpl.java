package com.serpics.membership.services;

import static org.springframework.data.jpa.domain.Specifications.where;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.base.data.model.Country;
import com.serpics.base.data.repositories.CountryRepository;
import com.serpics.base.facade.data.CountryData;
import com.serpics.core.data.Repository;
import com.serpics.membership.UserRegStatus;
import com.serpics.membership.UserType;
import com.serpics.membership.data.model.BillingAddress;
import com.serpics.membership.data.model.Membergroup;
import com.serpics.membership.data.model.PermanentAddress;
import com.serpics.membership.data.model.PrimaryAddress;
import com.serpics.membership.data.model.Role;
import com.serpics.membership.data.model.Store;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.data.repositories.BillingAddressRepository;
import com.serpics.membership.data.repositories.MemberGroupRepository;
import com.serpics.membership.data.repositories.MembersRoleRepository;
import com.serpics.membership.data.repositories.PermanentAddressRepository;
import com.serpics.membership.data.repositories.PrimaryAddressRepository;
import com.serpics.membership.data.repositories.StoreRepository;
import com.serpics.membership.data.repositories.UserRegrepository;
import com.serpics.membership.data.repositories.UserRepository;

//@StoreService("userService" )
@Service("userService")
@Scope("store")
@Transactional(readOnly = true)
public class UserServiceImpl extends AbstractMemberService<User, Long> implements UserService {

    @Resource
    UserRepository userRepository;
    @Resource
    UserRegrepository userRegrepository;
    
    @Resource
    StoreRepository storeRepository;

    @Resource
    CountryRepository countryRepository;
    
    @Autowired
    MemberGroupRepository memberGroupRepository;

    @Resource(name = "permanentAddressRepository")
    PermanentAddressRepository addressRepository;
    
    @Resource(name = "primaryAddressRepository")
    PrimaryAddressRepository primaryAddressRepository;
    
    @Resource
    BillingAddressRepository billingAddressRepository;
    
    @Autowired
    MembersRoleRepository membersRoleRepository;


    @Override
    @Transactional
    public User create(User user) {

        if (user.getUserType().equals(UserType.ANONYMOUS))
            user.setUserType(UserType.GUEST);

        user = adjustAddresses(user);
        user = adjustMemberRoles(user);
        user.setCreated(new Date());
        final Store currentStore = (Store) getCurrentContext().getStoreRealm();
        final Store _s = storeRepository.findOne(currentStore.getId());
        user.getStores().add(_s);
        return userRepository.create(user);

    }

    @Override
    @Transactional
    public User update(User user) {
        user = adjustAddresses(user);
        user = adjustMemberRoles(user);

        return userRepository.create(user);
    }

    @Override
    @Transactional
    public BillingAddress updateBillingAddress(BillingAddress ba) {
    	return billingAddressRepository.update(ba);
    }
    
    @Override
    @Transactional
    public PrimaryAddress updatePrimaryAddress(PrimaryAddress pa) {
    	return primaryAddressRepository.update(pa);
    }
    
    @Override
    @Transactional
    public PermanentAddress updatePermanentAddress(PermanentAddress pe) {
    	return addressRepository.update(pe);
    }
    
    @Override
    @Transactional
    public UsersReg registerUser(final UsersReg reg, final PrimaryAddress primaryAddress) {

        final User u = create(reg);

        if (reg.getUserType().equals(UserType.ANONYMOUS) || reg.getUserType().equals(UserType.GUEST))
            reg.setUserType(UserType.REGISTERED);

        if (reg.getStatus() == null)
            reg.setStatus(UserRegStatus.ACTIVE);

        if (primaryAddress != null) {
            primaryAddress.setMember(u);
            reg.setPrimaryAddress(primaryAddress);
        }
        return userRegrepository.create(reg);
    }

  

    @Override
    public List<UsersReg> findByexample(final UsersReg example) {
        return userRegrepository.findAll(where(userRegrepository.makeSpecification(example)));
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
		return (User) getCurrentContext().getCustomer();
	}
	
	@Override
	public User getCurrentUser() {
		return (User) getCurrentContext().getUserPrincipal();
	}
	
	@Override
	public void setCurrentCustomer(User user) {
		getCurrentContext().setCustomer(user);
	}

	@Override
	@Transactional
	public void addBillingAddress(BillingAddress address, User user) {
		Assert.notNull(user);
		Assert.notNull(address);
		address.setMember(user);
		user.setBillingAddress(address);
		billingAddressRepository.saveAndFlush(address);
	}
	
	@Override
	public Country getCountry(CountryData d) {
		Country c = new Country();
		
		//Country  c = countryRepository.findOne(countryRepository.makeSpecification(ce));
		return c;
	}
}
