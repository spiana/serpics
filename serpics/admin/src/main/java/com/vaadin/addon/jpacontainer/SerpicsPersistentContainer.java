package com.vaadin.addon.jpacontainer;


public class SerpicsPersistentContainer<T> extends JPAContainer<T> {

	private static final long serialVersionUID = 1135352329112858933L;

	public SerpicsPersistentContainer(Class<T> entityClass) {
		super(entityClass);
		
	}

	
	public PropertyList<T> getPropertyList(){
		return super.getPropertyList();
	}

    
	
	
	
}
