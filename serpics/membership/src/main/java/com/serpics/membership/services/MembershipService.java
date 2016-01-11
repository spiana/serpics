package com.serpics.membership.services;

import com.serpics.base.data.model.Store;
import com.serpics.core.SerpicsException;
import com.serpics.core.service.Membership;
import com.serpics.membership.data.model.PrimaryAddress;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.model.UsersReg;

public interface MembershipService extends Membership {

    public Store createStore(Store store);

    public User createUser(User user);

    public User createUser(User user, PrimaryAddress primaryAddress);

    public UsersReg registerUser(UsersReg reg, PrimaryAddress primaryAddress);
    
    public UsersReg loadUserByUserName(String userName) throws SerpicsException;
    
}
