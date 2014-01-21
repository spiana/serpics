package com.serpics.membership.services;

import java.util.Collection;
import java.util.List;

import com.serpics.core.service.EntityService;
import com.serpics.membership.persistence.PermanentAddress;
import com.serpics.membership.persistence.PrimaryAddress;
import com.serpics.membership.persistence.Role;
import com.serpics.membership.persistence.Store;
import com.serpics.membership.persistence.User;
import com.serpics.membership.persistence.UsersReg;



public interface UserService extends EntityService<User , Long> {

    public User registerUser(User user, UsersReg reg, PrimaryAddress primaryAddress);
    public List<UsersReg> findByexample(UsersReg example);

    public User addAddress(PermanentAddress address , User user);
    public User addAddress(PermanentAddress address , Long  userId);


    public User addRole(Role role , User user );
    public Collection<Role> getUserRoles(User user , Store store);	

}
