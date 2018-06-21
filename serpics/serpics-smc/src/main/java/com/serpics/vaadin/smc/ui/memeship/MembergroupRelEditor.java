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

import com.serpics.membership.data.model.Membergrouprel;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.MasterForm;
import com.vaadin.v7.ui.Field;

@VaadinComponent("memberGroupRelEditor")
public class MembergroupRelEditor extends MasterForm<Membergrouprel> {
	private static final long serialVersionUID = 7609356693138124682L;

	public MembergroupRelEditor() {
        super(Membergrouprel.class);

    }
    @Override
    public void init() {
        final String[] displayProperties = { "membergroup", "status", "validFrom", "validTo" };
        setDisplayProperties(displayProperties);
    }

    @Override
    protected Field<?> createField(final String pid) {
//        if (pid.equals("membergroup")) {
//            final ComboBox combo = new ComboBox("membergroup");
//            combo.setContainerDataSource(ServiceContainerFactory.make(Membergroup.class));
//            combo.setItemCaptionMode(ItemCaptionMode.PROPERTY);
//            combo.setItemCaptionPropertyId("name");
//            combo.setFilteringMode(FilteringMode.CONTAINS);
//            combo.setImmediate(true);
//            combo.setConverter(new SingleSelectConverter(combo));
//            fieldGroup.bind(combo, "membergroup");
//            return combo;
//        } else
            return super.createField(pid);
    }
    
}