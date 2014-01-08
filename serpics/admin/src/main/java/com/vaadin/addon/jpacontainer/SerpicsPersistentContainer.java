package com.vaadin.addon.jpacontainer;

import com.vaadin.data.Validator.InvalidValueException;


public class SerpicsPersistentContainer<T> extends JPAContainer<T> {

    private static final long serialVersionUID = 1135352329112858933L;

    public SerpicsPersistentContainer(final Class<T> entityClass) {
        super(entityClass);

    }


    @Override
    public PropertyList<T> getPropertyList(){
        return super.getPropertyList();
    }


    @Override
    public void commit() throws SourceException, InvalidValueException {
        // TODO Auto-generated method stub
        super.commit();
    }

    @Override
    public boolean removeItem(final Object itemId) throws UnsupportedOperationException {
        // TODO Auto-generated method stub
        return super.removeItem(itemId);
    }
}
