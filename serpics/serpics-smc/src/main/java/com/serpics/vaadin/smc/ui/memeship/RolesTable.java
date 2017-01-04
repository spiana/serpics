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

import com.serpics.membership.data.model.Role;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.MasterForm;
import com.serpics.vaadin.data.utils.I18nUtils;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterTable;


@VaadinComponent(value = "roleTable")
public class RolesTable extends MasterTable<Role> {

    private static final long serialVersionUID = -1487550710132191348L;

    public RolesTable() {
        super(Role.class);
    }

    @Override
    public EntityFormWindow<Role> buildEntityWindow() {
    	EntityFormWindow<Role> _e = new EntityFormWindow<Role>();
    	
    	final MasterForm<Role> editor = new MasterForm<Role>(Role.class) {
            private static final long serialVersionUID = -8926571251656496441L;

            @Override
            public void init() {
                super.init();
                final String[] p = { "name", "description" };
                setPropertyToShow(p);
            }

        };
        _e.addTab(editor, I18nUtils.getMessage("role", "main"));
    	return _e;
    }
    
    @Override
    public void init() {
        super.init();

        final MasterForm<Role> editor = new MasterForm<Role>(Role.class) {
            private static final long serialVersionUID = -8926571251656496441L;

            @Override
            public void init() {
                super.init();
                final String[] p = { "name", "description" };
                setPropertyToShow(p);
            }

        };
       

        final String[] p = { "name", "description", "updated" };
        setPropertyToShow(p);
    }


}
