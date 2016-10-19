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

import com.serpics.membership.data.model.Membergrouprel;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.repositories.MembergrouprelRepository;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.data.utils.I18nUtils;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterDetailTable;


@VaadinComponent("membergroupRelTable")
public class MembergroupRelTable extends MasterDetailTable<Membergrouprel , User> {
    private static final long serialVersionUID = 5863975797848978227L;

    @Autowired
    private transient MembergrouprelRepository membergrouprelRepository;

    @Autowired
    MembergroupRelEditor membergroupRelEditor;

    public MembergroupRelTable() {
        super(Membergrouprel.class);
    }

    @Override
    public EntityFormWindow<Membergrouprel> buildEntityWindow() {
    	EntityFormWindow<Membergrouprel> _e = new EntityFormWindow<Membergrouprel>();
    	_e.addTab(membergroupRelEditor, I18nUtils.getMessage("membergrouprel.main", ""));
    	return _e;
    }
  
    @Override
    public void init() {
        super.init();
        container.addNestedContainerProperty("membergroup.*");
        final String[] p = { "membergroup.name", "status", "validFrom", "validTo" };
        setPropertyToShow(p);
        setParentProperty("memberGroupRel");
    }

}
