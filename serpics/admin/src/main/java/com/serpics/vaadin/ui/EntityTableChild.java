package com.serpics.vaadin.ui;

import com.serpics.vaadin.ui.EntityComponent.EntityComponentChild;
import com.vaadin.addon.jpacontainer.EntityItem;

public abstract class EntityTableChild<T, P> extends EntityTable<T> implements EntityComponentChild<T, P> {
    private static final long serialVersionUID = 5325235469617666772L;

    protected EntityItem<P> parent;

    public EntityTableChild(final Class<T> entityClass) {
        super(entityClass);
    }

    @Override
    public void setParentEntity(final EntityItem<P> parent) {
        this.parent = parent;
    }
}
