package com.serpics.vaadin.ui;

import com.serpics.vaadin.ui.EntityComponent.EntityComponentChild;
import com.vaadin.addon.jpacontainer.EntityItem;

public abstract class EntityFormChild<T, P> extends EntityForm<T> implements EntityComponentChild<T, P> {
    private static final long serialVersionUID = 7628189100288027785L;

    protected EntityItem<P> parent;


    public EntityFormChild(final Class<T> clazz) {
        super(clazz);
    }


    @Override
    public void setParentEntity(final EntityItem<P> parent) {
        this.parent = parent;

    }

}
