package com.serpics.core.security;

import com.serpics.core.data.model.Currency;

public interface StoreRealm {
    public Long getId();
    public String getName();
    public Currency getCurrency();
   
}
