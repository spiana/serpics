package com.serpics.vaadin.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.core.CommerceEngine;
import com.serpics.core.security.UserDetail;
import com.serpics.core.session.CommerceSessionContext;
import com.serpics.membership.persistence.Role;
import com.serpics.membership.persistence.User;
import com.serpics.membership.services.UserService;

public class AbstractComponent {

	@Autowired(required = true)
	CommerceEngine commerceEngine;

	public CommerceEngine getCommerceEngine() {
		return commerceEngine;
	}

	public void setCommerceEngine(CommerceEngine commerceEngine) {
		this.commerceEngine = commerceEngine;
	}
	

	protected CommerceSessionContext getCurrentContext() {
		return commerceEngine.getCurrentContext();
	}
	
	protected boolean hasRole( String role){
		return hasRoles(new String[] {role});
	}
	
	protected boolean hasRoles(String[] roles){
		//TODO: to implement 
		return true;
	}
	
	protected boolean isInGroup(String group){
	
		return isInGroups(new String[] {group});
	}
	protected boolean isInGroups(String[] groups){
		//TODO: to implement
		return true;
	}
}
