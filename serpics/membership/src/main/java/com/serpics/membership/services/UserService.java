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

import java.nio.file.attribute.UserPrincipal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.base.data.model.Store;
import com.serpics.core.service.EntityService;
import com.serpics.membership.UserType;
import com.serpics.membership.data.model.BillingAddress;
import com.serpics.membership.data.model.Membergroup;
import com.serpics.membership.data.model.PermanentAddress;
import com.serpics.membership.data.model.PrimaryAddress;
import com.serpics.membership.data.model.Role;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.model.UsersReg;



public interface UserService extends MemberService<User, Long>, EntityService<User, Long>{

    public UsersReg registerUser(UsersReg reg, PrimaryAddress primaryAddress);
    public List<UsersReg> findByexample(UsersReg example);
    public List<User> findByexample(User example);
    public UsersReg findByRegUUID(String uuid);
    public Collection<Role> getUserRoles(User user , Store store);
    
    public User findAnonymous();
    public UsersReg findByLogonid(String logonid);
    public Page<User> findByEmail(String email , Pageable page);
    public Page<User> findByUserType(UserType type , Pageable page);
    
    public Set<PermanentAddress> getUserAddress(User user);
    public Set<Membergroup> getUserGroups(User user);
    
    public User create(User user);
    public User update(User user);
    
    public BillingAddress addBillingAddress(BillingAddress address , User user);
    public void addPermanentAddress(PermanentAddress address , User user);
    
    public void deleteBillingAddress(User user);
    public void deletePermanentAddress(User user, PermanentAddress address) ;
    
    public BillingAddress updateBillingAddress(BillingAddress address);
    public PrimaryAddress updatePrimaryAddress(PrimaryAddress address);
    public PermanentAddress updatePermanentAddress(PermanentAddress address);
     
    public User getCurrentCustomer();
    public void setCurrentCustomer(User user);
    public UserPrincipal getCurrentUser();
    
   
}
