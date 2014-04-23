package com.serpics.vaadin.ui.memeship;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.core.service.EntityService;
import com.serpics.membership.persistence.UsersReg;
import com.serpics.membership.services.UserRegService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityTable;

@VaadinComponent(value = "userTableEditor")
public class UserTableEditor extends EntityTable<UsersReg> {

    private static final long serialVersionUID = -8370714049392595536L;

    @Autowired
    private transient UserRegService userRegService;

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


    public UserTableEditor() {
        super(UsersReg.class);
    } 

    @Override
    public EntityService getService() {
        return userRegService;
    }

    @Override
    public void init() {
        super.init();
        final String[] p = { "firstname", "lastname", "created" };
        setPropertyToShow(p);
        editorWindow.addTab(userEditorComponent, "main");
        editorWindow.addTab(primaryAddressEditor, "contactAddress");
        editorWindow.addTab(userRegEditorComponent, "userReg");
        editorWindow.addTab(addressTableEditor, "address");
        editorWindow.addTab(membergroupRelTable, "grouprelation");
        editorWindow.addTab(memberRoleTable, "memberRole");

    }
}
