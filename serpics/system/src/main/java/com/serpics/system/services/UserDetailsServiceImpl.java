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
package com.serpics.system.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.base.commerce.session.CommerceSessionContext;
import com.serpics.base.data.model.Store;
import com.serpics.core.SerpicsException;
import com.serpics.core.service.AbstractService;
import com.serpics.membership.UserType;
import com.serpics.membership.data.model.MembersRole;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.data.repositories.MembersRoleRepository;
import com.serpics.membership.data.repositories.UserregRepository;
import com.serpics.membership.services.MembershipService;

@Service("userDetailService")
public class UserDetailsServiceImpl extends AbstractService<CommerceSessionContext> implements UserDetailsService {
    @Autowired
    MembershipService membershipService;

    @Autowired
    UserregRepository userRegrepository;

    @Autowired
   UserregRepository userRegRepository;

    @Autowired
    MembersRoleRepository membersRoleRepository;

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
      try{
    	 UsersReg ur= membershipService.loadUserByUserName(userName); 
    	 return buildUser(ur);
        }catch(SerpicsException e){
        	e.printStackTrace();
    	 throw new UsernameNotFoundException(userName);
      }
         
    }    

    private UserDetails buildUser(final UsersReg user) {

        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(0);
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        final org.springframework.security.core.userdetails.User userDatail = new org.springframework.security.core.userdetails.User(
                user.getLogonid(), user.getPassword(), authorities);
        return userDatail;

    }

    @Override
    @Transactional(readOnly=true)
    public void setCredentials(final Authentication authentication) {
        final UsersReg reg = userRegRepository.findBylogonid(authentication.getName());

        final List<MembersRole> roles = membersRoleRepository.findUserRoles(reg, (Store) getCurrentContext()
                .getStoreRealm());
        final Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(0);
        for (final MembersRole role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" +role.getRole().getName().toUpperCase()));
        }
        
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        
        if(reg.getUserType() ==  UserType.SUPERSUSER){
        	authorities.add(new SimpleGrantedAuthority("ROLE_SUPERUSER"));	
        }
        
        final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                authentication.getPrincipal(), authentication.getCredentials(),
                authorities);

        SecurityContextHolder.getContext().setAuthentication(token);
    }

	@Override
	@Transactional
	public void updateLastVisit(UsersReg user) {
		Assert.notNull(user);
		user.setLastLogin(new Date());
        user.setLastVisit(user.getLastLogin());
        userRegRepository.save(user);
	
		
	}

	@Override
	@Transactional(readOnly=true)
	public String getDefaultStore(User princial , String preferred) {
		String defaultStore = "default-store";
		
		UsersReg user = userRegrepository.findBylogonid(princial.getUsername());
		if (user != null){
			Set<Store> stores = user.getStores();
			if ( !stores.isEmpty()){
				if (stores.contains(preferred))
					defaultStore = preferred;
				else	
					defaultStore = user.getStores().iterator().next().getName();
			}
		}
		
		return defaultStore;
	}
}
