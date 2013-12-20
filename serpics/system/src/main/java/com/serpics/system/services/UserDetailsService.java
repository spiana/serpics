package com.serpics.system.services;

import org.springframework.security.core.Authentication;

public interface UserDetailsService extends org.springframework.security.core.userdetails.UserDetailsService {

    public void setCredentials(Authentication authentication);

}
