package com.serpics.membership.services;

import java.security.Principal;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.base.data.model.Store;
import com.serpics.commerce.service.AbstractCommerceService;
import com.serpics.core.SerpicsException;
import com.serpics.core.security.UserDetail;
import com.serpics.core.service.Membership;
import com.serpics.membership.data.model.PrimaryAddress;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.data.repositories.StoreRepository;
import com.serpics.membership.data.repositories.UserregRepository;
import com.serpics.membership.strategies.MembershipStrategy;

@Transactional(readOnly = true)
public class MembershipServiceImpl extends AbstractCommerceService implements MembershipService, Membership {

    @Resource
    private StoreRepository storeRepository;


    @Resource(name="userService")
    private UserService userService;
    
      
    @Autowired
    private UserregRepository userRegRepository;

    @Resource
    private MembershipStrategy membershipStrategy;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public User createUser(User user) {
    	 return userService.create(user);
    }

   
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public User createUser(final User user, final PrimaryAddress primaryAddress) {
        user.setPrimaryAddress(primaryAddress);
        return createUser(user);
    }

   
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED)
    public Store fetchStoreByName(final String name) {
        return storeRepository.findByname(name);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public UsersReg registerUser(final UsersReg reg, final PrimaryAddress primaryAddress) {
        return userService.registerUser(reg, primaryAddress);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Store createStore(final Store store) {
        return storeRepository.saveAndFlush(store);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public UserDetail login(final String username, final char[] password) throws SerpicsException {
        return membershipStrategy.login(username, password);
    }

    @Override
    public UserDetail connect(final Principal principal) {
        final UsersReg ur = userRegRepository.findBylogonid(principal.getName());
        Assert.notNull(ur);
      
        return ur;
    }
  
    @Override
    public UserDetail createAnonymous() {
        return userService.findAnonymous();
    }


	@Override
	public UsersReg loadUserByUserName(String userName) throws SerpicsException {
		return membershipStrategy.loadUserByUserName(userName);
	}


	
}
