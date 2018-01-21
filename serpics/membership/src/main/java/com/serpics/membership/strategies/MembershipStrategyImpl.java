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
package com.serpics.membership.strategies;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import com.serpics.base.commerce.CommerceEngine;
import com.serpics.base.data.model.Store;
import com.serpics.core.SerpicsException;
import com.serpics.core.security.UserDetail;
import com.serpics.membership.MembershipException;
import com.serpics.membership.UserRegStatus;
import com.serpics.membership.UserType;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.data.repositories.UserregRepository;
import com.serpics.stereotype.StoreStrategy;

@StoreStrategy(value="membershipStrategy" )
public class MembershipStrategyImpl  implements MembershipStrategy {
    @Resource
    UserregRepository userRegRepository;

    @Resource
    CommerceEngine commerceEngine;
    
    @Override
    public UsersReg loadUserByUserName(final String userName) throws SerpicsException{
    	 UsersReg ur = userRegRepository.findBylogonid(userName);
    	 
    	// if not superuser test store
//         if (ur != null && !ur.getUserType().equals(UserType.SUPERSUSER)) {
//        	 boolean found = false;
//        	 for (Store store : ur.getStores()) {
//				if (store.equals((Store)commerceEngine.getCurrentContext().getStoreRealm() )){
//					found = true ;
//					break;
//				}
//        	 }
//			if (!found) {
//                 throw new MembershipException(String.format("Invalid store relation for userId %s and store %s",
//                         ur.getLogonid(), commerceEngine.getCurrentContext().getRealm()));
//             }
//			
//             
//         }
    	return ur;
    }
    
    @Override
    public UserDetail login( final String username, final char[] password) throws SerpicsException {
        UsersReg ur = userRegRepository.findBylogonid(username);
        
        if (ur != null) {
            if (ur.getUserType().equals(UserType.ADMINISTRATOR) || ur.getUserType().equals(UserType.REGISTERED)
                    || ur.getUserType().equals(UserType.SUPERSUSER))
                ur = login(ur, password);
            else
                throw new MembershipException(String.format("invalid type %s for userId [%d] !", ur.getUserType(),
                        ur.getId()));

            // if not superuser test store
            if (!ur.getUserType().equals(UserType.SUPERSUSER)) {
            	 boolean found = false;
            	 for (Store store : ur.getStores()) {
    				if (store.equals((Store)commerceEngine.getCurrentContext().getStoreRealm() )){
    					found = true ;
    					break;
    				}
            	 }
    			if (!found) {
                     throw new MembershipException(String.format("Invalid store relation for userId %s and store %s",
                             ur.getLogonid(), commerceEngine.getCurrentContext().getRealm()));
                 }
            }
        } else {
            throw new MembershipException("no user found for loginid [" + username + "] !");
        }
        return ur;
    }

    protected UsersReg login(final UsersReg ur, final char[] password) throws MembershipException {

        // TODO: verificare che la password sia corretta e valida
        // TODO: password deve essere salvata in MD5 quindi la stringa
        // ricevuta deve essere convertita

        if (!ur.getStatus().equals(UserRegStatus.ACTIVE)) {
            throw new MembershipException("wrong status [" + ur.getStatus() + "] for loginid [" + ur.getLogonid()
                    + "] !");
        }
        if (ur.getPassword().equals(new String(password))) {
            ur.setLastVisit(new Date());
            ur.setLastLogin(new Timestamp(ur.getLastVisit().getTime()));
        } else {
            throw new MembershipException("wrong password for loginid [" + ur.getLogonid() + "] !");
        }

        return userRegRepository.save(ur);
    }

	
}
