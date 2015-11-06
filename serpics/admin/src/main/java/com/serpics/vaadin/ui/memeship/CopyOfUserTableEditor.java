package com.serpics.vaadin.ui.memeship;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.membership.data.model.UsersReg;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterTable;
import com.vaadin.addon.jpacontainer.EntityItem;


@VaadinComponent(value = "userTableEditor" ,stores={"test-store"})
public class CopyOfUserTableEditor extends MasterTable<UsersReg> {

    private static final long serialVersionUID = -8370714049392595536L;

  

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
    BillingAddressEditor billingAddressEditor;

    @Autowired
    MemberRoleTable memberRoleTable;


    public CopyOfUserTableEditor() {
        super(UsersReg.class);
    } 

    @Override
    public EntityFormWindow<UsersReg> buildEntityWindow() {
    	EntityFormWindow<UsersReg> editorWindow = new EntityFormWindow<UsersReg>();
    	editorWindow.addTab(userEditorComponent, "main");
 //       editorWindow.addTab(primaryAddressEditor, "contactAddress");
        editorWindow.addTab(billingAddressEditor, "billingAddress");
        editorWindow.addTab(userRegEditorComponent, "userReg");
//        editorWindow.addTab(addressTableEditor, "address");
//        editorWindow.addTab(membergroupRelTable, "grouprelation");
//        editorWindow.addTab(memberRoleTable, "memberRole");
    	
    	return editorWindow;
    }

    @Override
    public void init() {
        super.init();
//        container.addNestedContainerProperty("primaryAddress.*");
//        final String[] p = { "firstname", "lastname", "created" };
//        setPropertyToShow(p);
//        

    }

    @Override
    public EntityItem<UsersReg> createEntityItem() {
        final UsersReg u = new UsersReg();
        return container.createEntityItem(u);
    }
}