package com.serpics.vaadin.ui;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Component;

public interface EntityComponent<T> extends Component{

    public void init();
    public void save() throws CommitException;
    public void discard();
    public void setEntityItem(EntityItem<T> entityItem);
    public void setParentEntity(Object entity);

    public interface EntityTableComponent<T> extends EntityComponent<T>{

    }
    public interface EntityFormComponent<T> extends EntityComponent<T>{

    }
}
