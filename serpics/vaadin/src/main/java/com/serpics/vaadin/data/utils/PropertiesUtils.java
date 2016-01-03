package com.serpics.vaadin.data.utils;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
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
	
	private Map<String, String[]> editProperties = new HashMap<String, String[]>();	
	private Map<String, String[]> readOnlyProperties = new HashMap<String, String[]>();	
	private Map<String, String[]> tableProperties = new HashMap<String, String[]>();
	private Map<String, String> selectProperties = new HashMap<String, String>();	
	private Map<String, String[]> searchProperties = new HashMap<String, String[]>();
	
	public Map<String, String[]> getSearchProperties() {
		return searchProperties;
	}


	public void setSearchProperties(Map<String, String[]> searchProperties) {
		this.searchProperties = searchProperties;
	}


	public Map<String, String[]> getEditProperties() {
		return editProperties;
	}


	public void setEditProperties(Map<String, String[]> editProperties) {
		this.editProperties = editProperties;
	}


	public Map<String, String[]> getReadOnlyProperties() {
		return readOnlyProperties;
	}


	public void setReadOnlyProperties(Map<String, String[]> readOnlyProperties) {
		this.readOnlyProperties = readOnlyProperties;
	}


	public Map<String, String[]> getTableProperties() {
		return tableProperties;
	}

	public void setTableProperties(Map<String, String[]> tableProperties) {
		this.tableProperties = tableProperties;
	}

	public Map<String, String> getSelectProperties() {
		return selectProperties;
	}

	public void setSelectProperties(Map<String, String> selectProperties) {
		this.selectProperties = selectProperties;
	}


	public static PropertiesUtils get(){
		if (instance == null)
			instance= EngineFactory.getCurrentApplicationContext().getBean(PropertiesUtils.class);
		return instance;
	}

	public String[] getTableProperty(String entity){
		return tableProperties.get(entity.toLowerCase());
	}
	
	public String[] getEditProperty(String entity){
		return editProperties.get(entity.toLowerCase());
	}
	
	public String[] getReadOnlyProperty(String entity){
		return readOnlyProperties.get(entity.toLowerCase());
	}

	public String getSelectProperty(String entity){
		return selectProperties.get(entity.toLowerCase());
	}
	
	public String[] getSearchProperty(String entity){
		return searchProperties.get(entity.toLowerCase());
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
				LOG.info("found smc definition file : {} with URL {}" , resource.getFilename(), resource.getURL()) ;
				loadSmcDefinition(resource.getURL());
			}
			
	}
	
	public void loadSmcDefinition(URL  smcdefinition) throws DocumentException, IOException{
		
		 SAXReader reader = new SAXReader();
	     Document document = reader.read(smcdefinition);
	     
	     Element root = document.getRootElement();
	     for ( Iterator i = root.elementIterator( "entity" ); i.hasNext(); ) {
	            Element clazz = (Element) i.next();
	            addEntity(clazz);
	        }
	}
	
	private void addEntity(Element entity){
			String c = entity.attributeValue("name");
			LOG.info("create smc configuration form entity : {}" , c);
			 for ( Iterator i = entity.elementIterator(); i.hasNext(); ) {
		            Element type = (Element) i.next();
		            if (type.getName().equals("table")){
		            	addTable(type, c);
		            }
		            else if (type.getName().equals("editor")) {
		            	addEdit(type, c);
		           
		            }else if (type.getName().equals("select")) {
		            	addSelect(type, c);
		            }
		            
		        }
	}
	private void addTable(Element table , String clazz ){
			LOG.info("add table element for entity {}",clazz);
			for ( Iterator i = table.elementIterator(); i.hasNext(); ) {
	            Element type = (Element) i.next();
	            if (type.getName().equals("display")) {
	            	LOG.info("add display property for entity {}", clazz);
	            	tableProperties.put(clazz.toLowerCase(),addProperties(type));
	            }else if (type.getName().equals("search")) {
	            	LOG.info("add search property for entity {}", clazz);
	            	searchProperties.put(clazz.toLowerCase(), addProperties(type));
	            }
	            
	        }
		
	}
	private void addEdit(Element editor , String clazz) {
		LOG.info("add editor element for entity {}", clazz);
		for ( Iterator i = editor.elementIterator(); i.hasNext(); ) {
            Element type = (Element) i.next();
            if (type.getName().equals("display")) {
            	LOG.info("add display property for entity {}", clazz);
            	editProperties.put(clazz.toLowerCase(),addProperties(type));
            }else if (type.getName().equals("readonly")) {
            	LOG.info("add readonly property for entity {}", clazz);
            	readOnlyProperties.put(clazz.toLowerCase() ,addProperties(type));
            }
            
        }
	}

	private String[] addProperties(Element editor) {
		return readProperties(editor);
	}
	
	private void addSelect(Element select , String clazz) {
		LOG.info("add select element for entity {}", clazz);
		String[] properties = readProperties(select);
		if (properties.length > 0)
			selectProperties.put(clazz, properties[0]);
	}


	private String[] readProperties(Element element){
		List<String> properties = new ArrayList<String>();
		 for ( Iterator i = element.elementIterator("property"); i.hasNext(); ) {
			 Element property = (Element) i.next();
			 	Attribute name = property.attribute("name");
	           LOG.debug("found property {}" , name.getValue() ); 
	           properties.add(name.getValue());
	        }
		 
		 String[] propArr = new String[properties.size()];
		 
		 return  properties.toArray(propArr);
	}
	
}
