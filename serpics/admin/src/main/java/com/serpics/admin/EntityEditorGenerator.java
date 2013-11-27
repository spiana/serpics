package com.serpics.admin;

import java.util.List;

import com.serpics.vaadin.ui.PropertyList;
import com.vaadin.addon.jpacontainer.SortBy;
import com.vaadin.addon.jpacontainer.metadata.EntityClassMetadata;
import com.vaadin.addon.jpacontainer.metadata.MetadataFactory;

public class EntityEditorGenerator<T> {
	
	private Class<T> entityClass;
	
	 private EntityClassMetadata<T> entityClassMetadata;
	 private List<SortBy> sortByList;
	 private PropertyList<T> propertyList;
	    
	public EntityEditorGenerator(Class<T> clazz){
		this.entityClass = clazz;
		 this.entityClassMetadata = MetadataFactory.getInstance()
	                .getEntityClassMetadata(entityClass);
	     this.propertyList = new PropertyList<T>(entityClassMetadata);
	}
	
	private Class<T> getEntityClass(){
		return entityClass;
		
	}
}
