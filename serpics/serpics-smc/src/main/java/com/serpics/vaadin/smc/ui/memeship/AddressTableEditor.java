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

import com.serpics.membership.data.model.PermanentAddress;
import com.serpics.membership.data.model.User;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.data.utils.I18nUtils;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterDetailTable;


@VaadinComponent(value = "addressTableEditor")
public class AddressTableEditor extends MasterDetailTable<PermanentAddress, User> {

    private static final long serialVersionUID = -1487550710132191348L;

    @Autowired
    private AddressEditorComponent addressEditorComponent;

    public AddressTableEditor() {
        super(PermanentAddress.class);
    }

    @Override
    public EntityFormWindow<PermanentAddress> buildEntityWindow() {
    	EntityFormWindow<PermanentAddress> _e = new EntityFormWindow<PermanentAddress>();
    	_e.addTab(addressEditorComponent, I18nUtils.getMessage("address.main", ""));
    	return _e;
    }
    @Override
    public void init() {
        super.init();
       // container.addNestedContainerProperty("country.*");
      //  final String[] p = { "firstname", "lastname", "company", "address1", "zipcode", "city", "region",
       // "country.iso2Code" };
        setParentProperty("permanentAddresses");
       // setPropertyToShow(p );
    }


}
