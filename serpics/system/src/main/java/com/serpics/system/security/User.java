package com.serpics.system.security;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.serpics.membership.UserRegStatus;



public class User extends com.serpics.membership.persistence.User implements UserDetails , com.serpics.core.security.UserDetail{

    Collection<? extends GrantedAuthority> authorities = new HashSet<GrantedAuthority>(0);
    Collection<? extends String>  groups = new HashSet<String>(0); 

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return getUserReg().getPassword();
    }

    @Override
    public String getUsername() {
        return getUserReg().getLogonid();
    }

    @Override
    public boolean isAccountNonExpired() {
        return getUserReg().getStatus().equals(UserRegStatus.ACTIVE);
    }

    @Override
    public boolean isAccountNonLocked() {
        return getUserReg().getStatus().equals(UserRegStatus.ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return getUserReg().getStatus().equals(UserRegStatus.ACTIVE);
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
