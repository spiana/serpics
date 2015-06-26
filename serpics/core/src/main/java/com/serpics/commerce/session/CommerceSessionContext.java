package com.serpics.commerce.session;

import com.serpics.core.data.model.Catalog;
import com.serpics.core.data.model.Currency;
import com.serpics.core.data.model.Locale;
import com.serpics.core.security.StoreRealm;
import com.serpics.core.security.UserDetail;
import com.serpics.core.session.SessionContext;

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
        super(realm.getName());
        this.storeRealm = realm;

    }

    @Override
    public void setUserPrincipal(final UserDetail user) {
        super.setUserPrincipal(user);
        this.customer = user;
    }

    public Long getStoreId() {
        return this.storeRealm.getId();
    }

    public StoreRealm getStoreRealm() {
        return storeRealm;
    }

    public void setStoreRealm(final StoreRealm storeRealm) {
        this.storeRealm = storeRealm;
        super.setRealm(storeRealm.getName());
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

    public void setCatalog(final Catalog catalog) {
        this.catalog = catalog;
    }

    public UserDetail getCustomer() {
        return customer;
    }

    public void setCustomer(final UserDetail customer) {
        this.customer = customer;
    }

}
