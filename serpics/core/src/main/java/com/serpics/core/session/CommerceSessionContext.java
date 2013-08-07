package com.serpics.core.session;

import com.serpics.core.persistence.Catalog;
import com.serpics.core.persistence.Currency;
import com.serpics.core.persistence.Locale;
import com.serpics.core.security.StoreRealm;
import com.serpics.core.security.UserDetail;

public class CommerceSessionContext extends SessionContext {
	private static final long serialVersionUID = 1L;

	private Catalog catalog;
	private UserDetail customer;
	private Currency currency;
	private Locale locale;
	private StoreRealm storeRealm;
	private String userCookie;

	public CommerceSessionContext() {
		super();
	}

	public CommerceSessionContext(final StoreRealm realm) {
		super(realm.getRealm());
		this.storeRealm = realm;

	}

	@Override
	public void setUserPrincipal(final UserDetail user) {
		super.setUserPrincipal(user);
		this.customer = user;
	}

	public Long getStoreId() {
		return this.storeRealm.getStoreId();
	}

	public StoreRealm getStoreRealm() {
		return storeRealm;
	}

	public void setStoreRealm(final StoreRealm storeRealm) {
		this.storeRealm = storeRealm;
	}

	public String getUserCookie() {
		if (userCookie == null)
			this.userCookie = getSessionId();

		return userCookie;
	}

	public void setUserCookie(final String userCookie) {
		this.userCookie = userCookie;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(final Currency currency) {
		this.currency = currency;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(final Locale locale) {
		this.locale = locale;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	public UserDetail getCustomer() {
		return customer;
	}

	public void setCustomer(UserDetail customer) {
		this.customer = customer;
	}

}
