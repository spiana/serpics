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
package com.serpics.commerce.session;

import com.serpics.commerce.core.Customer;
import com.serpics.core.data.model.Catalog;
import com.serpics.core.data.model.Currency;
import com.serpics.core.data.model.Locale;
import com.serpics.core.security.StoreRealm;
import com.serpics.core.session.SessionContext;

public class CommerceSessionContext extends SessionContext {
    private static final long serialVersionUID = 1L;

    private Catalog catalog;
    private Customer customer;
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

    public Customer getCustomer() {
    	if (this.customer != null)
    		return this.customer;
    	else
    		return (Customer) getUserPrincipal();
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

}
