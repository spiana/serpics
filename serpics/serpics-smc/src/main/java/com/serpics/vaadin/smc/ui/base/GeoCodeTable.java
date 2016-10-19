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

import com.serpics.base.data.model.Geocode;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.data.utils.I18nUtils;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterForm;
import com.serpics.vaadin.ui.MasterTable;
import com.serpics.vaadin.ui.converters.MultilingualFieldConvert;

@VaadinComponent("geocodeTable")
public class GeoCodeTable extends MasterTable<Geocode> {

	public GeoCodeTable() {
		super(Geocode.class);
		
	}
	
	private static final long serialVersionUID = 1064176359644526596L;

	
	@Override
	public void init() {
		super.init();
		setPropertyToShow(new String[]{"code","description" });
		entityList.setConverter("description", new MultilingualFieldConvert());
		
	}
	
	@Override
	public EntityFormWindow<Geocode> buildEntityWindow() {
		EntityFormWindow<Geocode> editorWindow = new EntityFormWindow<Geocode>();
	
			MasterForm<Geocode> main = new MasterForm<Geocode>(Geocode.class) {
				@Override
				public void init() {
					super.init();
					setDisplayProperties(new String[]{"code" , "description"});
					
				}
			};
			
		editorWindow.addTab(main,  I18nUtils.getMessage("geocode.main", ""));
		return editorWindow;
	}
}
