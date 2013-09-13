package com.serpics.admin.vaadin.container;

import com.vaadin.addon.jpacontainer.JPAContainer;

public class SerpicsPersistentContainer<T> extends JPAContainer<T> {

	private static final long serialVersionUID = 1135352329112858933L;

	public SerpicsPersistentContainer(Class<T> entityClass) {
		super(entityClass);
	}

	@Override
	public Object addItem() throws UnsupportedOperationException {
        try {
            T newInstance = getEntityClass().newInstance();
            Object id = addEntity(newInstance);
            return id;
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        }
        throw new UnsupportedOperationException();
    }
	
}
