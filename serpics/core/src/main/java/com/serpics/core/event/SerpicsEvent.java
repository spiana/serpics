package com.serpics.core.event;

import org.springframework.context.ApplicationEvent;

import com.serpics.core.data.model.Catalog;
import com.serpics.core.security.StoreRealm;

public abstract class SerpicsEvent extends ApplicationEvent {

	private static final long serialVersionUID = -4819807126083042202L;
	
	private String sessionId;
    private String realm;
	private StoreRealm storeRealm;
	private Catalog catalog;
	
	public SerpicsEvent(Object source) {
		super(source);
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getRealm() {
		return realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

	public StoreRealm getStoreRealm() {
		return storeRealm;
	}

	public void setStoreRealm(StoreRealm storeRealm) {
		this.storeRealm = storeRealm;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}
	
}
