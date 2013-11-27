package com.vaadin.addon.jpacontainer;

import com.vaadin.data.Validator.InvalidValueException;


public class SerpicsPersistentContainer<T> extends JPAContainer<T> {

	private static final long serialVersionUID = 1135352329112858933L;

	public SerpicsPersistentContainer(Class<T> entityClass) {
		super(entityClass);
		
	}

	
	public PropertyList<T> getPropertyList(){
		return super.getPropertyList();
	}

    
	@Override
	public void commit() throws SourceException, InvalidValueException {
		// TODO Auto-generated method stub
		super.commit();
	}
	
	
}
