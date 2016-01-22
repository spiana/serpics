package com.serpics.vaadin.ui.memeship;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.membership.data.model.UsersReg;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.data.utils.I18nUtils;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterTable;


@VaadinComponent(value = "userTableEditor" )
public class UserTableEditor extends MasterTable<UsersReg> {

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


    public UserTableEditor() {
        super(UsersReg.class);
    } 

    @Override
    public EntityFormWindow<UsersReg> buildEntityWindow() {
    	EntityFormWindow<UsersReg> editorWindow = new EntityFormWindow<UsersReg>();
    	
    	editorWindow.addTab(userEditorComponent, I18nUtils.getMessage("user", ""));
 //       editorWindow.addTab(primaryAddressEditor, "contactAddress");
<<<<<<< HEAD
        editorWindow.addTab(billingAddressEditor, I18nUtils.getMessage("billingaddress", ""));
        editorWindow.addTab(userRegEditorComponent,I18nUtils.getMessage("usersreg", ""));
        editorWindow.addTab(addressTableEditor, I18nUtils.getMessage("address", ""));
        editorWindow.addTab(membergroupRelTable, I18nUtils.getMessage("membergrouprel", ""));
      editorWindow.addTab(memberRoleTable, I18nUtils.getMessage("membersrole", ""));
=======
        editorWindow.addTab(billingAddressEditor,I18nUtils.getMessage("billingAddress", "billingAddress"));
        editorWindow.addTab(userRegEditorComponent, "userReg");
        editorWindow.addTab(addressTableEditor, "address");
        editorWindow.addTab(membergroupRelTable, "grouprelation");
      editorWindow.addTab(memberRoleTable, "memberRole");
>>>>>>> e108e3406a7a14a68f4e2e555e9cf398e628a28e
    	
    	return editorWindow;
    }


   
}
