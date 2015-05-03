package com.serpics.system.security;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.serpics.membership.UserRegStatus;



public class User extends com.serpics.membership.data.model.UsersReg implements UserDetails,
com.serpics.core.security.UserDetail {

	private static final long serialVersionUID = -5717668598546054329L;

	Collection<? extends GrantedAuthority> authorities = new HashSet<GrantedAuthority>(0);
    Collection<? extends String>  groups = new HashSet<String>(0); 

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return getPassword();
    }

    @Override
    public String getUsername() {
        return getLogonid();
    }

    @Override
    public boolean isAccountNonExpired() {
        return getStatus().equals(UserRegStatus.ACTIVE);
    }

    @Override
    public boolean isAccountNonLocked() {
        return getStatus().equals(UserRegStatus.ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return getStatus().equals(UserRegStatus.ACTIVE);
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        authorities = authorities;
    }

    public Collection<? extends String> getGroups() {
        return groups;
    }

    public void setGroups(final Collection<? extends String> groups) {
        this.groups = groups;
    }
}
