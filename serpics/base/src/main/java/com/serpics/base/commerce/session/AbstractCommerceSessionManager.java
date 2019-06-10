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
package com.serpics.base.commerce.session;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.serpics.base.commerce.security.StoreRealm;
import com.serpics.core.scope.SessionScopeAttributes;
import com.serpics.core.scope.SessionScopeContextHolder;
import com.serpics.core.security.Realm;
import com.serpics.core.session.AbstractSessionManager;
import com.serpics.core.session.SessionContext;
import com.serpics.core.session.SessionManager;

public abstract class AbstractCommerceSessionManager extends AbstractSessionManager implements SessionManager {
    private static transient Logger logger = LoggerFactory.getLogger(AbstractCommerceSessionManager.class);

    @Override
    protected SessionContext makeSessionContext(String sessionId , Realm realm){
    	   Assert.notNull(sessionId, "sessionId can non be null !");
    	   Assert.isInstanceOf(StoreRealm.class, realm);
    	   	
    	  	final SessionScopeAttributes commerceScopeAttributes = new SessionScopeAttributes();

    	  	commerceScopeAttributes.setConversationId(sessionId);
    	  
    	  	SessionScopeContextHolder.setSessionScopeAttributes(commerceScopeAttributes);
           final CommerceSessionContext context = new CommerceSessionContext();
           context.setRealm(realm);
           context.setSessionId(sessionId);
           context.setUserCookie(sessionId);
           context.setLastAccess(new Date());

           context.setCommerceScopeAttribute(commerceScopeAttributes);

           sessionList.put(sessionId, context);
           logger.info("create new session with id [{}]", sessionId);
           return context;
    	
    }

  }
