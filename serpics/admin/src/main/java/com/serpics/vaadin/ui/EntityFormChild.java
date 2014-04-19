package com.serpics.vaadin.ui;

import com.serpics.vaadin.ui.EntityComponent.EntityComponentChild;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.provider.ServiceContainerFactory;

public abstract class EntityFormChild<T, P> extends EntityForm<T> implements EntityComponentChild<T, P> {
    private static final long serialVersionUID = 7628189100288027785L;

    protected EntityItem<P> parent;

    protected JPAContainer<T> container;

    public EntityFormChild(final Class<T> clazz) {
        super(clazz);
    }


    @Override
    public void init() {
        container = ServiceContainerFactory.make(entityClass, getService());
        super.init();
    }

    @Override
    public void setParentEntity(final EntityItem<P> parent) {
        this.parent = parent;
    }

}
