package com.serpics.vaadin.ui;

import java.io.Serializable;

import com.serpics.admin.SerpicsContainerFactory;
import com.serpics.core.service.EntityService;
import com.serpics.vaadin.ui.EntityComponent.EntityComponentChild;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.SerpicsPersistentContainer;

public abstract class EntityFormChild<T, P> extends EntityForm<T> implements EntityComponentChild<T, P> {
    private static final long serialVersionUID = 7628189100288027785L;

    protected EntityService<T, Serializable> service;

    protected EntityItem<P> parent;

    protected SerpicsPersistentContainer<T> container;

    public EntityFormChild(final Class<T> clazz) {
        super(clazz);
    }


    @Override
    public void init() {
        container = SerpicsContainerFactory.make(entityClass, service);
        super.init();
    }

    @Override
    public void setParentEntity(final EntityItem<P> parent) {
        this.parent = parent;


    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void setService(final EntityService service) {
        this.service = service;
    }
}
