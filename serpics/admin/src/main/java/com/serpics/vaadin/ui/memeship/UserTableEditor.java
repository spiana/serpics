package com.serpics.vaadin.ui.memeship;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.membership.persistence.User;
import com.serpics.membership.services.UserService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.EntityTable;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;

@VaadinComponent(value = "userTableEditor")
public class UserTableEditor extends EntityTable<User>{

    @Autowired
    UserService userService;

    @Autowired
    UserEditorComponent userEditorComponent;

    @Autowired
    AddressTableEditor addressTableEditor;

    EntityItem<User> entityItem;

    public UserTableEditor() {
        super(User.class);
    } 

    @Override
    public void init() {
        this.cont.addNestedContainerProperty("userReg.*");
        editorWindow= new EntityFormWindow<User>();
        addressTableEditor.init();

        editorWindow.addTab(userEditorComponent, "main");
        editorWindow.addTab(addressTableEditor, "address");
        editorWindow.addTab(addressTableEditor, "address1");

        final String[] p = { "firstname", "lastname", "created", "userReg.logonid" };
        setPropertyToShow(p );
        setService(userService);
        super.init();


    }
    @Override
    public void save() throws CommitException {
        // TODO Auto-generated method stub

    }

    @Override
    public void discard() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setEntityItem(final EntityItem<User> entityItem) {
        this.entityItem = entityItem;		
    }


    @Override
    public void setParentEntity(final Object entity) {
        // TODO Auto-generated method stub

    }




}
