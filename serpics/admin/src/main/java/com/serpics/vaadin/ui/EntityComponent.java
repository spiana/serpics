package com.serpics.vaadin.ui;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Component;
import com.vaadin.ui.TableFieldFactory;

public interface EntityComponent<T> extends Component{

    public void init();

    public boolean isInitialized();

    public interface EntityTableComponent<T> extends EntityComponent<T>{
        public void setTableFieldFactory(TableFieldFactory factory);
    }

    public interface EntityComponentChild<T, P> extends EntityComponent<T> {
        public void setParentEntity(EntityItem<P> parent);
    }
    public interface EntityFormComponent<T> extends EntityComponent<T>{
        public boolean isModifield();
        public boolean isValid();
        public void discard();
        public void save() throws CommitException;
        public void setEntityItem(EntityItem<T> entityItem);

    }


}
