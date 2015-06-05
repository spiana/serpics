package com.serpics.system.services;

import org.springframework.security.core.Authentication;

import com.serpics.membership.data.model.UsersReg;

public interface UserDetailsService extends org.springframework.security.core.userdetails.UserDetailsService {

    public void setCredentials(Authentication authentication);

    public void updateLastVisit(UsersReg user);
}
