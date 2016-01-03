package com.serpics.vaadin.ui;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Component;
import com.vaadin.ui.TableFieldFactory;

public interface EntityComponent<T> extends Component{

    public void init();

    public boolean isInitialized();

    public void discard();

    public void save() throws CommitException;
    public Class<T> getEntityType();
  
    public interface MasterTableComponent<T> extends EntityComponent<T>{
        public void setTableFieldFactory(TableFieldFactory factory);
    }

    public interface EntityComponentChild<DETAIL,MASTER> extends EntityComponent<DETAIL> {
        public void setParentEntity(EntityItem<MASTER> parent);
        public void setParentProperty(Object parentPropertyId);

       
    }
    public interface EntityFormComponent<T> extends EntityComponent<T>{
        public boolean isModifield();
        public boolean isValid();
        public void setEntityItem(EntityItem<T> entityItem);

    }


}
