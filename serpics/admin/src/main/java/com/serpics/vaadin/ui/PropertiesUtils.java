package com.serpics.vaadin.ui;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

import com.serpics.core.EngineFactory;

public class PropertiesUtils implements ApplicationContextAware , InitializingBean{
	
	Logger LOG = LoggerFactory.getLogger(PropertiesUtils.class);
	
	private ApplicationContext applicationContext;
	
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

	

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.applicationContext= arg0;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
			Resource[] resources =this.applicationContext.getResources("classpath*:META-INF/*-smc.xml");
			
			for (Resource resource : resources) {
				LOG.info("found smc definition file : {}" , resource.getFilename()) ;
			}
			
			
	}
}
