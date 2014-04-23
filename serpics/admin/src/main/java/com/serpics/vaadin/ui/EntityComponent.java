package com.serpics.vaadin.ui;

import com.serpics.core.service.EntityService;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Component;
import com.vaadin.ui.TableFieldFactory;

public interface EntityComponent<T> extends Component{

    public void init();

    public boolean isInitialized();

    public interface EntityTableComponent<T> extends EntityComponent<T>{
        public void setTableFieldFactory(TableFieldFactory factory);

        @SuppressWarnings("rawtypes")
        public EntityService getService();
    }

    public interface EntityComponentChild<T, P> extends EntityComponent<T> {
        public void setParentEntity(EntityItem<P> parent);

        public void setParentProperty(Object parentPropertyId);

        @SuppressWarnings("rawtypes")
        public EntityService getService();
    }
    public interface EntityFormComponent<T> extends EntityComponent<T>{
        public boolean isModifield();
        public boolean isValid();
        public void discard();
        public void save() throws CommitException;
        public void setEntityItem(EntityItem<T> entityItem);

    }


}
