package com.serpics.commerce.strategies;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;


public abstract class AbstractStrategy {

    @Autowired
    CommerceEngine commerceEngine;

    protected CommerceSessionContext currentContext;

    public CommerceSessionContext getCurrentContext() {
        return currentContext != null ? currentContext : commerceEngine.getCurrentContext();
    }

    public void setCurrentContext(final CommerceSessionContext sessionContext) {
        this.currentContext = (CommerceSessionContext) sessionContext;

    }

}
