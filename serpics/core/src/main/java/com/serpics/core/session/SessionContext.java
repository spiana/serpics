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
package com.serpics.core.session;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.serpics.core.scope.SessionScopeAttributes;
import com.serpics.core.security.UserDetail;

public abstract class SessionContext implements Serializable {

    private static final long serialVersionUID = 1L;

    private UserDetail userDetail;
    private String sessionId;
    private String realm;
    private Date lastAccess = new Date();
    private SessionScopeAttributes sessionScopeAttribute;

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(final Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public SessionContext(final String realm) {
        super();
        this.realm = realm;

    }

    public SessionContext() {
        super();
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(final String realm) {
        this.realm = realm;
    }

    private Map<String, Serializable> attribute;

    public void setAttribute(final String key, final Serializable value) {
        if(attribute == null)
            attribute = new HashMap<String, Serializable>();

        attribute.put(key, value);
    }

    public Serializable getAttribute(final String key) {
    	if(attribute == null)
    		  return null;
        return attribute.get(key);
    }

    public UserDetail getUserPrincipal() {
        return userDetail;
    }

    public void setUserPrincipal(final UserDetail user) {
        this.userDetail = user;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(final String sessionId) {
        this.sessionId = sessionId;
    }

    public SessionScopeAttributes getCommerceScopeAttribute() {
        return sessionScopeAttribute;
    }

    public void setCommerceScopeAttribute(final SessionScopeAttributes commerceScopeAttribute) {
        this.sessionScopeAttribute = commerceScopeAttribute;
    }

}
