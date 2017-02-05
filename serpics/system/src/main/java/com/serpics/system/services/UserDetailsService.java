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

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import com.serpics.membership.data.model.UsersReg;

public interface UserDetailsService extends org.springframework.security.core.userdetails.UserDetailsService {

    public void setCredentials(Authentication authentication);

    public void updateLastVisit(UsersReg user);
    public String getDefaultStore(User principal);
}
