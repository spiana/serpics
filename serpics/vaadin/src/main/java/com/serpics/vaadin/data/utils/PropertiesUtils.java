package com.serpics.vaadin.data.utils;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
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
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;

public class PropertiesUtils implements ApplicationContextAware , InitializingBean{
	
	Logger LOG = LoggerFactory.getLogger(PropertiesUtils.class);
	
	
	public class SmcPropertyDef{
		
		private String propertyId;
		private boolean tableProperty = false;
		private boolean editProperty = true;
		private boolean readOnly =  false;
		private boolean searchProperty = false;
		private boolean selectProperty = false;
		
		private String style;
	
		private String width;
		
		private Resolution resolution;
	
		public SmcPropertyDef() {
			super();
		}
		
		public SmcPropertyDef(String propertyId) {
			super();
			this.propertyId = propertyId;
		}

		public String getPropertyId() {
			return propertyId;
		}
		public void setPropertyId(String propertyId) {
			this.propertyId = propertyId;
		}
		public boolean isTableProperty() {
			return tableProperty;
		}
		public void setTableProperty(boolean tableProperty) {
			this.tableProperty = tableProperty;
		}
		public boolean isEditProperty() {
			return editProperty;
		}
		public void setEditProperty(boolean editProperty) {
			this.editProperty = editProperty;
		}
		public boolean isReadOnly() {
			return readOnly;
		}
		public void setReadOnly(boolean readOnly) {
			this.readOnly = readOnly;
		}
		public boolean isSelectProperty() {
			return selectProperty;
		}
		public void setSelectProperty(boolean selectProperty) {
			this.selectProperty = selectProperty;
		}
		public String getStyle() {
			return style;
		}
		public void setStyle(String style) {
			this.style = style;
		}
		
		public String getWidth() {
			return width;
		}
		public void setWidth(String width) {
			this.width = width;
		}
		public boolean isSearchProperty() {
			return searchProperty;
		}
		public void setSearchProperty(boolean searchProperty) {
			this.searchProperty = searchProperty;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result
					+ ((propertyId == null) ? 0 : propertyId.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			SmcPropertyDef other = (SmcPropertyDef) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (propertyId == null) {
				if (other.propertyId != null)
					return false;
			} else if (!propertyId.equals(other.propertyId))
				return false;
			return true;
		}
		private PropertiesUtils getOuterType() {
			return PropertiesUtils.this;
		}

		public Resolution getResolution() {
			return resolution;
		}

		public void setResolution(String resolution) {
			this.resolution = Resolution.valueOf(resolution);
		}
	
		
		
	}
	
	private ApplicationContext applicationContext;
	
	private static PropertiesUtils instance;
	
	
	private Map<String, String[]> editProperties = new HashMap<String, String[]>();	
	private Map<String, String[]> readOnlyProperties = new HashMap<String, String[]>();	
	private Map<String, String[]> tableProperties = new HashMap<String, String[]>();
	private Map<String, String> selectProperties = new HashMap<String, String>();	
	private Map<String, String[]> searchProperties = new HashMap<String, String[]>();
	
	private Map<String , List<SmcPropertyDef>> properties = new HashMap<String, List<PropertiesUtils.SmcPropertyDef>>();
	
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
	        List<SmcPropertyDef> _p = readProperties(entity);
            this.properties.put(c.toLowerCase(), _p);
            loadTableProperty(c, _p);
            loadEditProperty(c, _p);
            loadReadonlyProperty(c, _p);
            loadSelectProperty(c, _p);
            loadSearchProperty(c, _p);
            
	}
	
	private void loadTableProperty ( String entity_name , List<SmcPropertyDef> properties){
			List<String> _l = new ArrayList<String>();
			for (SmcPropertyDef smcPropertyDef : properties) {
				if (smcPropertyDef.isTableProperty())
					_l.add(smcPropertyDef.getPropertyId());
			}
			tableProperties.put(entity_name, _l.toArray(new String[_l.size()]));
	}
	private void loadEditProperty ( String entity_name , List<SmcPropertyDef> properties){
		List<String> _l = new ArrayList<String>();
		for (SmcPropertyDef smcPropertyDef : properties) {
			if (smcPropertyDef.isEditProperty())
				_l.add(smcPropertyDef.getPropertyId());
		}
		editProperties.put(entity_name, _l.toArray(new String[_l.size()]));
	}
	
	private void loadReadonlyProperty ( String entity_name , List<SmcPropertyDef> properties){
		List<String> _l = new ArrayList<String>();
		for (SmcPropertyDef smcPropertyDef : properties) {
			if (smcPropertyDef.isReadOnly())
				_l.add(smcPropertyDef.getPropertyId());
		}
		readOnlyProperties.put(entity_name, _l.toArray(new String[_l.size()]));
	}
	private void loadSelectProperty ( String entity_name , List<SmcPropertyDef> properties){
		List<String> _l = new ArrayList<String>();
		for (SmcPropertyDef smcPropertyDef : properties) {
			if (smcPropertyDef.isSelectProperty())
				_l.add(smcPropertyDef.getPropertyId());
		}
		if (!_l.isEmpty())
			selectProperties.put(entity_name, _l.get(_l.size()-1));
	}
	
	private void loadSearchProperty ( String entity_name , List<SmcPropertyDef> properties){
		List<String> _l = new ArrayList<String>();
		for (SmcPropertyDef smcPropertyDef : properties) {
			if (smcPropertyDef.isSearchProperty())
				_l.add(smcPropertyDef.getPropertyId());
		}
		searchProperties.put(entity_name, _l.toArray(new String[_l.size()]));
	}
	
	private List<SmcPropertyDef> readProperties(Element element){
		List<SmcPropertyDef> properties = new ArrayList<SmcPropertyDef>();
		 for ( Iterator i = element.elementIterator("property"); i.hasNext(); ) {
			 Element property = (Element) i.next();
			 	SmcPropertyDef def = new SmcPropertyDef();
			 	if (property.attribute("name") != null)
			 		def.setPropertyId(property.attribute("name").getValue());
			 	if (property.attribute("edit") != null)
			 		def.setEditProperty(BooleanUtils.toBoolean(property.attribute("edit").getValue()));
			 	if (property.attribute("table") != null)
			 		def.setTableProperty(BooleanUtils.toBoolean(property.attribute("table").getValue()));
				if (property.attribute("readonly") != null)
			 		def.setReadOnly(BooleanUtils.toBoolean(property.attribute("readonly").getValue()));
				if (property.attribute("select") != null)
			 		def.setSelectProperty(BooleanUtils.toBoolean(property.attribute("select").getValue()));
				if (property.attribute("search") != null)
			 		def.setSearchProperty(BooleanUtils.toBoolean(property.attribute("search").getValue()));
				
				if (property.attribute("width") != null)
					def.setWidth((property.attribute("width").getValue()));
				if (property.attribute("style") != null)
					def.setStyle(property.attribute("style").getValue());
				if (property.attribute("resolution") != null)
					def.setResolution(property.attribute("resolution").getValue());
				
			 	
	           LOG.debug("found property {}" , def.getPropertyId() ); 
	           
	           properties.add(def);
	        }
		 
		
		 return  properties;
	}


	public Map<String, List<SmcPropertyDef>> getProperties() {
		return properties;
	}
	
	public List<SmcPropertyDef> getPropertyForEntity(String entity){
		List<SmcPropertyDef> _l = properties.get(entity.toLowerCase());
		if (_l == null)
			_l = new ArrayList<PropertiesUtils.SmcPropertyDef>();
		
		return _l;
	}
	
	public SmcPropertyDef getPropertyForEntity(String entity , String propertyId){
			List<SmcPropertyDef> properties = getPropertyForEntity(entity);
			if (!properties.isEmpty())
				if (properties.indexOf(new SmcPropertyDef(propertyId)) > -1)
					return properties.get(properties.indexOf(new SmcPropertyDef(propertyId)));
			
		return null;	
	}
	
	public void setFiledProperty(String entity, String propertyId , Field<?> field){
		SmcPropertyDef def = PropertiesUtils.get().getPropertyForEntity(entity, propertyId);
		 if (def != null){
			 String style = def.getStyle();
			 String width = def.getWidth();
			
			 
			 if (style != null)
				 field.addStyleName(style);
			if (width != null)
				field.setWidth(width);
			
			if (def.getResolution() != null){
				if (DateField.class.isAssignableFrom(field.getClass()));
					((DateField) field).setResolution(def.getResolution());
			}
		 }
	}
	
}
