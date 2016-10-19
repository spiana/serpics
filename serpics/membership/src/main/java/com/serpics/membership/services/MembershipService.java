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

import com.serpics.base.data.model.Store;
import com.serpics.core.SerpicsException;
import com.serpics.core.service.Membership;
import com.serpics.membership.data.model.PrimaryAddress;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.model.UsersReg;

public interface MembershipService extends Membership {

    public Store createStore(Store store);

    public User createUser(User user);

    public User createUser(User user, PrimaryAddress primaryAddress);

    public UsersReg registerUser(UsersReg reg, PrimaryAddress primaryAddress);
    
    public UsersReg loadUserByUserName(String userName) throws SerpicsException;
    
}
