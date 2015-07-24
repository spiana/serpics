package com.serpics.membership.services;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.serpics.core.service.EntityService;
import com.serpics.membership.data.model.BillingAddress;
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
    public UsersReg findByRegUUID(String uuid);
    public Collection<Role> getUserRoles(User user , Store store);
    
    public User findAnonymous();
    public UsersReg findByLogonid(String logonid);
    
    public Set<PermanentAddress> getUserAddress(User user);
    public Set<Membergroup> getUserGroups(User user);
    
    public User create(User user);
    public User update(User user);
    
    public void addBillingAddress(BillingAddress address , User user);
    public void addPermanentAddress(PermanentAddress address , User user);
    
    public void deleteBillingAddress(User user);
    
    public BillingAddress updateBillingAddress(BillingAddress address);
    public PrimaryAddress updatePrimaryAddress(PrimaryAddress address);
    public PermanentAddress updatePermanentAddress(PermanentAddress address);
     
    public User getCurrentCustomer();
    public void setCurrentCustomer(User user);
    public User getCurrentUser();
    
   
}
