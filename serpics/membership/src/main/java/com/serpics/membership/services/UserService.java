package com.serpics.membership.services;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.serpics.core.service.EntityService;
import com.serpics.membership.data.model.Membergroup;
import com.serpics.membership.data.model.PermanentAddress;
import com.serpics.membership.data.model.PrimaryAddress;
import com.serpics.membership.data.model.Role;
import com.serpics.membership.data.model.Store;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.model.UsersReg;



public interface UserService extends MemberService<User, Long>, EntityService<User, Long>{

    public UsersReg registerUser(UsersReg reg, PrimaryAddress primaryAddress);
    public List<UsersReg> findByexample(UsersReg example);
    public Collection<Role> getUserRoles(User user , Store store);	
    public User findAnonymous();
    public Set<PermanentAddress> getUserAddress(User user);
    public Set<Membergroup> getUserGroups(User user);
    public User create(User user);
    public User update(User user);
    
    public User getCurrentUser();
}
