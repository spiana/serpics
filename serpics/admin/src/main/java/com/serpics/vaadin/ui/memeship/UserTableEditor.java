package com.serpics.vaadin.ui.memeship;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.membership.persistence.User;
import com.serpics.membership.persistence.UsersReg;
import com.serpics.membership.services.UserService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.EntityTable;
import com.vaadin.addon.jpacontainer.EntityItem;

@VaadinComponent(value = "userTableEditor")
public class UserTableEditor extends EntityTable<UsersReg> {

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

    @Autowired
    PrimaryAddressEditor primaryAddressEditor;

    @Autowired
    MemberRoleTable memberRoleTable;

    EntityItem<User> entityItem;

    public UserTableEditor() {
        super(UsersReg.class);
    } 

    @Override
    public void init() {
        editorWindow = new EntityFormWindow<UsersReg>();
        editorWindow.addTab(userEditorComponent, "main");
        editorWindow.addTab(primaryAddressEditor, "contactAddress");
        editorWindow.addTab(userRegEditorComponent, "userReg");
        editorWindow.addTab(addressTableEditor, "address");
        editorWindow.addTab(membergroupRelTable, "grouprelation");
        editorWindow.addTab(memberRoleTable, "memberRole");


        final String[] p = { "firstname", "lastname", "created", "logonid" };
        setPropertyToShow(p );
        setService(userService);
        super.init();


    }
}
