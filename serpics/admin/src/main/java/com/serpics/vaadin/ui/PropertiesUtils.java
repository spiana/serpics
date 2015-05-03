package com.serpics.vaadin.ui;


import java.util.HashMap;
import java.util.Map;

import com.serpics.core.EngineFactory;

public class PropertiesUtils {
	
	private static PropertiesUtils instance;
	
	private Map<Class<?>, String[]> editProperties = new HashMap<Class<?>, String[]>();	
	private Map<Class<?>, String[]> readOnlyProperties = new HashMap<Class<?>, String[]>();	
	private Map<Class<?>, String[]> tableProperties = new HashMap<Class<?>, String[]>();
	private Map<Class<?>, String> selectProperties = new HashMap<Class<?>, String>();	
	
	public static PropertiesUtils get(){
		if (instance == null)
			instance= EngineFactory.getCurrentApplicationContext().getBean(PropertiesUtils.class);
		return instance;
	}

	public String[] getEditProperties(Class<?> clazz){
		return editProperties.get(clazz);
	}
	public String[] getReadOnlyProperties(Class<?> clazz){
		return readOnlyProperties.get(clazz);
	}
	public String[] getTableProperties(Class<?> clazz){
		return tableProperties.get(clazz);
	}
	public String getSelectProperties(Class<?> clazz){
		return selectProperties.get(clazz);
	}

	

	public void setEditProperties(Map<Class<?>, String[]> editProperties) {
		this.editProperties = editProperties;
	}

	public void setReadOnlyProperties(Map<Class<?>, String[]> readOnlyProperties) {
		this.readOnlyProperties = readOnlyProperties;
	}

	public void setTableProperties(Map<Class<?>, String[]> tableProperties) {
		this.tableProperties = tableProperties;
	}

	public void setSelectProperties(Map<Class<?>, String> selectProperties) {
		this.selectProperties = selectProperties;
	}
}
