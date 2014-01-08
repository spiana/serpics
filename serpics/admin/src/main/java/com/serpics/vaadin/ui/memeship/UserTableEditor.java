package com.serpics.vaadin.ui.memeship;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.membership.persistence.User;
import com.serpics.membership.services.UserService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.EntityTable;
import com.vaadin.addon.jpacontainer.EntityItem;

@VaadinComponent(value = "userTableEditor")
public class UserTableEditor extends EntityTable<User>{

    private static final long serialVersionUID = -8370714049392595536L;

    @Autowired
    private transient UserService userService;

    @Autowired
    UserRegEditorComponent userRegEditorComponent;

    @Autowired
    UserEditorComponent userEditorComponent;

    @Autowired
    AddressTableEditor addressTableEditor;

    @Autowired
    MembergroupRelTable membergroupRelTable;

    EntityItem<User> entityItem;

    public UserTableEditor() {
        super(User.class);
    } 

    @Override
    public void init() {
        this.cont.addNestedContainerProperty("userReg.*");
        editorWindow= new EntityFormWindow<User>();
        addressTableEditor.init();
        membergroupRelTable.init();
        userRegEditorComponent.init();

        editorWindow.addTab(userEditorComponent, "main");
        editorWindow.addTab(userRegEditorComponent, "userReg");
        editorWindow.addTab(addressTableEditor, "address");
        editorWindow.addTab(membergroupRelTable, "grouprelation");


        final String[] p = { "firstname", "lastname", "created", "userReg.logonid" };
        setPropertyToShow(p );
        setService(userService);
        super.init();


    }
}
