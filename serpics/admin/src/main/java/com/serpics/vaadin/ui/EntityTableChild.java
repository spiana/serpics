package com.serpics.vaadin.ui;

import org.springframework.util.Assert;

import com.serpics.vaadin.ui.EntityComponent.EntityComponentChild;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.data.util.filter.Compare;

public abstract class EntityTableChild<T, P> extends EntityTable<T> implements EntityComponentChild<T, P> {
    private static final long serialVersionUID = 5325235469617666772L;

    protected EntityItem<P> parent;
    protected Object parentPropertyId;

    public EntityTableChild(final Class<T> entityClass) {
        super(entityClass);
    }

    @Override
    public void setParentEntity(final EntityItem<P> parent) {
        this.parent = parent;
    }

    @Override
    public void setParentProperty(final Object parentPropertyId) {
        this.parentPropertyId = parentPropertyId;
    }

    @Override
    public void attach() {
        if (container != null){
            Assert.notNull(this.parentPropertyId);
            container.removeContainerFilters(parentPropertyId);
            container.addContainerFilter(new Compare.Equal(parentPropertyId, parent.getEntity()));
        }
        super.attach();
    }
}
