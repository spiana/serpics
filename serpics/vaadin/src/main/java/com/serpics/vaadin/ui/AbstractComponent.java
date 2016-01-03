package com.serpics.vaadin.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;

public class AbstractComponent {

    @Autowired(required = true)
    private transient CommerceEngine commerceEngine;
    
    public CommerceEngine getCommerceEngine() {
        return commerceEngine;
    }

    public void setCommerceEngine(final CommerceEngine commerceEngine) {
        this.commerceEngine = commerceEngine;
    }


    protected CommerceSessionContext getCurrentContext() {
        return commerceEngine.getCurrentContext();
    }

    protected boolean hasRole( final String role){
        return hasRoles(new String[] {role});
    }

    protected boolean hasRoles(final String[] roles){
        //TODO: to implement 
        return true;
    }

    protected boolean isInGroup(final String group){

        return isInGroups(new String[] {group});
    }
    protected boolean isInGroups(final String[] groups){
        //TODO: to implement
        return true;
    }
}
