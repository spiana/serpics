package com.serpics.vaadin.ui;

import org.springframework.util.Assert;

import com.serpics.vaadin.jpacontainer.provider.ServiceContainerFactory;
import com.serpics.vaadin.ui.EntityComponent.EntityComponentChild;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.util.filter.Compare;

public abstract class MasterDetailForm<T, P> extends MasterForm<T> implements EntityComponentChild<T, P> {
    private static final long serialVersionUID = 7628189100288027785L;

    protected EntityItem<P> parent;

    protected Object parentPropertyId;
   
    protected JPAContainer<P> container;

    public MasterDetailForm(final Class<T> clazz) {
        super(clazz);
    }


    @Override
    public void setParentEntity(final EntityItem<P> parent) {
        this.parent = parent;
        container =  (JPAContainer<P> )ServiceContainerFactory.make(parent.getEntity().getClass());
        
        
        
    }

    @Override
    public void setParentProperty(final Object parentPropertyId) {
        this.parentPropertyId = parentPropertyId;
    }

    @Override
    public void attach() {
        if (container != null && parent != null) {
            Assert.notNull(this.parentPropertyId);
            container.removeContainerFilters(parentPropertyId);
            container.addContainerFilter(new Compare.Equal(parentPropertyId, parent.getEntity()));
        }
        super.attach();
    }
    
    
}
