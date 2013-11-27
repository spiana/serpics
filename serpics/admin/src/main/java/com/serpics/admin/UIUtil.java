package com.serpics.admin;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.serpics.core.CommerceEngineFactory;
import com.serpics.core.service.EntityService;
import com.serpics.vaadin.ui.EntityForm;

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
}
