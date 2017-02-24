/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.vaadin.smc.ui.memeship;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.data.model.MemberPricelistRelation;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.data.utils.I18nUtils;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterDetailTable;
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
    	editorWindow.addTab(userEditorComponent, I18nUtils.getMessage("user", "user"));
    	editorWindow.addTab(primaryAddressEditor, "contactAddress");
        editorWindow.addTab(billingAddressEditor, I18nUtils.getMessage("billingaddress", "billingaddress"));
        editorWindow.addTab(userRegEditorComponent,I18nUtils.getMessage("usersreg", "usersreg"));
        editorWindow.addTab(addressTableEditor, I18nUtils.getMessage("address", "address"));
        editorWindow.addTab(membergroupRelTable, I18nUtils.getMessage("membergrouprel", "membergrouprel"));
        editorWindow.addTab(memberRoleTable, I18nUtils.getMessage("membersrole", "membersrole"));
        editorWindow.addTab(new MasterDetailTable<MemberPricelistRelation , User>(MemberPricelistRelation.class , "member" , "member") {
			private static final long serialVersionUID = -6807854177166353469L;
        	
        }, I18nUtils.getMessage("userpricelist", "userpricelist"));
    	return editorWindow;
    }


   
}
