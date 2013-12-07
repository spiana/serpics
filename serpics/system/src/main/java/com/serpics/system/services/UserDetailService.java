package com.serpics.system.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.serpics.core.service.AbstractService;
import com.serpics.membership.persistence.Role;
import com.serpics.membership.persistence.Store;
import com.serpics.membership.persistence.User;
import com.serpics.membership.persistence.UsersReg;
import com.serpics.membership.repositories.MembersRoleRepository;
import com.serpics.membership.repositories.UserRegrepository;
import com.serpics.membership.services.MembershipService;
import com.serpics.membership.services.UserRegService;

@Service("userDetailService")
@Scope("store")
public class UserDetailService extends AbstractService implements UserDetailsService {
	@Autowired
	MembershipService membershipService;
	
	@Autowired
	UserRegrepository userRegrepository;
	
	@Autowired
	UserRegService userRegService;
	
	@Autowired
	MembersRoleRepository membersRoleRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
		UsersReg ur = userRegService.findByLoginid(arg0);
		if (ur == null)
			throw new UsernameNotFoundException("username :" + arg0 + " not found !");
		
		User u = ur.getUser();
		
		
		return null;
	}

	private UserDetails buildUser( User user){
		
		List<Role> roles = membersRoleRepository.findUserRoles(user , (Store) getCurrentContext().getStoreRealm()) ;
		List<GrantedAuthority> authorities = null;
		if (!roles.isEmpty()){
			authorities = new ArrayList<GrantedAuthority>(0);
			for (Role role : roles) {
				authorities.add(new SimpleGrantedAuthority(role.getName()));
			}
		}
		org.springframework.security.core.userdetails.User userDatail =
				new org.springframework.security.core.userdetails.User(user.getUserReg().getLogonid(), user.getUserReg().getPassword(), authorities);

		
		return userDatail;
		
	}
}
