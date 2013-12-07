package com.serpics.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.serpics.core.CommerceEngine;
import com.serpics.core.CommerceEngineFactory;
import com.serpics.core.service.EntityService;
import com.serpics.core.session.CommerceSessionContext;
import com.serpics.stereotype.StoreComponent;
import com.serpics.vaadin.ui.EntityForm;

@StoreComponent("UIUtil")
public class UIUtil {

	public static EntityService getEntityService(Class<?> clazz){
		
		String requiredType = clazz.getSimpleName();
		requiredType =StringUtils.uncapitalize(requiredType);
		requiredType += "Service";
		
		EntityService service = (EntityService) CommerceEngineFactory.getCurrentApplicationContext().getBean(requiredType);
		Assert.notNull(service , String.format("Service for entity %s not found!", clazz.getSimpleName()));
		
	return service;
	}
	
	public static EntityForm getEntityEditor(Class<?> clazz){
		
		String requiredEditor = clazz.getSimpleName();
		requiredEditor =StringUtils.uncapitalize(requiredEditor);
		requiredEditor += "EditorComponent";
		
		EntityForm component = (EntityForm) CommerceEngineFactory.getCurrentApplicationContext().getBean(requiredEditor);
		Assert.notNull(component , String.format("component for entity %s not found!", clazz.getSimpleName()));
	
		return component;
	}
	
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
	
	public static boolean hasRole( String role){
		return hasRoles(new String[] {role});
	}
	
	public static boolean hasRoles(String[] roles){
		//TODO: to implement 
		return true;
	}
	
	public static boolean isInGroup(String group){
	
		return isInGroups(new String[] {group});
	}
	public static boolean isInGroups(String[] groups){
		//TODO: to implement
		return true;
	}
}
