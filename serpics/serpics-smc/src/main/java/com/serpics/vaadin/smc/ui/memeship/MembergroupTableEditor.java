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

import com.serpics.membership.data.model.Membergroup;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.data.utils.I18nUtils;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterTable;
import com.vaadin.addon.jpacontainer.EntityItem;

@VaadinComponent(value = "membergroupTableEditor")
public class MembergroupTableEditor extends MasterTable<Membergroup> {

    private static final long serialVersionUID = -1487550710132191348L;


    @Autowired
    MembergroupEditor membergroupEditor;

    public MembergroupTableEditor() {
        super(Membergroup.class);
    }

   
    @Override
    public EntityFormWindow<Membergroup> buildEntityWindow() {
    	EntityFormWindow<Membergroup> _e = new EntityFormWindow<Membergroup>();
    	_e.addTab(membergroupEditor, I18nUtils.getMessage("membergroup.main", ""));
    	return _e;
    }
    @Override
    public void init() {
        super.init();
        final String[] p = { "name", "description", "updated" };
        setPropertyToShow(p);

    }
    
    @Override
    public EntityItem<Membergroup> createEntityItem() {
    	return super.createEntityItem();
    }

}
