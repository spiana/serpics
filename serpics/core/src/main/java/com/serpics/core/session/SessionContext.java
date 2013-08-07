package com.serpics.core.session;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import com.serpics.core.scope.SessionScopeAttributes;
import com.serpics.core.security.UserDetail;

public abstract class SessionContext implements Serializable {

	private static final long serialVersionUID = 1L;

	private UserDetail userDetail;
	private String sessionId;
	private String realm;
	private Date lastAccess;
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

	private HashMap<String, Object> attribute;

	public void setAttribute(final String key, final Object value) {
		attribute.put(key, value);
	}

	public Object getAttribute(final String key) {
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
