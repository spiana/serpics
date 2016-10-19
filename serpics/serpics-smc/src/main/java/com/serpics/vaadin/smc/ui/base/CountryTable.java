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
package com.serpics.vaadin.smc.ui.base;

import com.serpics.base.data.model.Country;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.data.utils.I18nUtils;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterForm;
import com.serpics.vaadin.ui.MasterTable;

@VaadinComponent("countryTable")
public class CountryTable extends MasterTable<Country> {

	public CountryTable() {
		super(Country.class);
	}
	
	private static final long serialVersionUID = 1064176359644526596L;
	
	@Override
	public EntityFormWindow<Country> buildEntityWindow() {
		
		EntityFormWindow<Country> editorWindow = new EntityFormWindow<Country>();
	
			MasterForm<Country> main = new MasterForm<Country>(Country.class) {
			};
			
		editorWindow.addTab(main, I18nUtils.getMessage("country.main", ""));
		return editorWindow;
	}
}
