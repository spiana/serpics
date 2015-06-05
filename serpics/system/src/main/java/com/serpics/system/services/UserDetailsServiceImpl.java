package com.serpics.system.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.service.AbstractService;
import com.serpics.membership.data.model.MembersRole;
import com.serpics.membership.data.model.Store;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.data.repositories.MembersRoleRepository;
import com.serpics.membership.data.repositories.UserRegrepository;
import com.serpics.membership.services.MembershipService;

@Service("userDetailService")
public class UserDetailsServiceImpl extends AbstractService<CommerceSessionContext> implements UserDetailsService {
    @Autowired
    MembershipService membershipService;

    @Autowired
    UserRegrepository userRegrepository;

    @Autowired
   UserRegrepository userRegRepository;

    @Autowired
    MembersRoleRepository membersRoleRepository;

    @Override
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
      try{
        final UsersReg ur = userRegRepository.findBylogonid(userName);
        if (ur == null) {
            throw new UsernameNotFoundException("username :" + userName + " not found !");
        }
        return buildUser(ur);
  

      }catch(Throwable e){
    	  e.printStackTrace();
      }
      return null;
    }    

    private UserDetails buildUser(final UsersReg user) {

        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(0);
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        final org.springframework.security.core.userdetails.User userDatail = new org.springframework.security.core.userdetails.User(
                user.getLogonid(), user.getPassword(), authorities);
        return userDatail;

    }

    @Override
    public void setCredentials(final Authentication authentication) {
        final UsersReg reg = userRegRepository.findBylogonid(authentication.getName());

        final List<MembersRole> roles = membersRoleRepository.findUserRoles(reg, (Store) getCurrentContext()
                .getStoreRealm());
        final Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(0);
        for (final MembersRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole().getName()));
        }

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                authentication.getPrincipal(), authentication.getCredentials(),
                authorities);

        SecurityContextHolder.getContext().setAuthentication(token);
    }

	@Override
	public void updateLastVisit(UsersReg user) {
		Assert.notNull(user);
		user.setLastLogin(new Date());
        user.setLastVisit(user.getLastLogin());
        userRegRepository.save(user);
	
		
	}
}
