package com.serpics.core.session;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import com.serpics.core.scope.CommerceScopeAttributes;
import com.serpics.core.security.UserPrincipal;

public abstract class SessionContext implements Serializable {

	private static final long serialVersionUID = 1L;

	private UserPrincipal userPrincipal;
	private String sessionId;
	private String realm;
	private Date lastAccess;
	private CommerceScopeAttributes commerceScopeAttribute;

	public Date getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}

	public SessionContext(String realm) {
		super();
		this.realm = realm;

	}

	public SessionContext() {
		super();
	}

	public String getRealm() {
		return realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

	private HashMap<String, Object> attribute;

	public void setAttribute(String key, Object value) {
		attribute.put(key, value);
	}

	public Object getAttribute(String key) {
		return attribute.get(key);
	}

	public UserPrincipal getUserPrincipal() {
		return userPrincipal;
	}

	public void setUserPrincipal(UserPrincipal user) {
		this.userPrincipal = user;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public CommerceScopeAttributes getCommerceScopeAttribute() {
		return commerceScopeAttribute;
	}

	public void setCommerceScopeAttribute(CommerceScopeAttributes commerceScopeAttribute) {
		this.commerceScopeAttribute = commerceScopeAttribute;
	}

}
