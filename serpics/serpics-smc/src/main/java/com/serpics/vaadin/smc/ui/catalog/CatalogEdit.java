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
package com.serpics.vaadin.smc.ui.catalog;


import javax.annotation.Resource;

import com.serpics.catalog.data.model.Catalog;
import com.serpics.catalog.services.CatalogService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterForm;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;

/**
 * @author spiana
 *
 */
@VaadinComponent("catalogEditWindow")
public class CatalogEdit extends EntityFormWindow<Catalog>{

	@Resource
	CatalogService catalogService;
	/**
	 * 
	 */
	private static final long serialVersionUID = -3425496930838508846L;

	
	/* (non-Javadoc)
	 * @see com.serpics.vaadin.ui.EntityFormWindow#build()
	 */
	@Override
	protected void build() {
		super.build();
		
		addTab(new MasterForm<Catalog>(Catalog.class) {
			/* (non-Javadoc)
			 * @see com.serpics.vaadin.ui.MasterForm#save()
			 */
			@Override
			public void save() throws CommitException {
				if (fieldGroup.isModified()){
				if (!entityItem.isPersistent()){
					fieldGroup.commit();
					entityItem.commit();
					catalogService.create(entityItem.getEntity());
					entityItem.getContainer().addEntity(entityItem.getEntity());
				}
				else
					super.save();
				}
			}
		}, "main");
	}

}
