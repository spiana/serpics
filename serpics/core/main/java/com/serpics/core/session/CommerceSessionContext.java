package com.serpics.core.session;

import com.serpics.core.security.StoreRealm;
import com.serpics.core.security.UserPrincipal;

public class CommerceSessionContext extends SessionContext {
	private static final long serialVersionUID = 1L;

	private Long catalogId;
	private Long customerId;
	private String currency;
	StoreRealm storeRealm;
	String userCookie;

	public CommerceSessionContext() {
		super();
	}

	public CommerceSessionContext(StoreRealm realm) {
		super(realm.getRealm());
		this.storeRealm = realm;

	}

	public Long getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}

	public Long getCustomerId() {
		if (this.customerId != null)
			return customerId;
		else
			return getUserPrincipal().getUserId();
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Override
	public void setUserPrincipal(UserPrincipal user) {
		super.setUserPrincipal(user);
		this.customerId = user.getUserId();
	}

	public Long getStoreId() {
		return this.storeRealm.getStoreId();
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public StoreRealm getStoreRealm() {
		return storeRealm;
	}

	public void setStoreRealm(StoreRealm storeRealm) {
		this.storeRealm = storeRealm;
	}

	public String getUserCookie() {
		return userCookie;
	}

	public void setUserCookie(String userCookie) {
		this.userCookie = userCookie;
	}

}
