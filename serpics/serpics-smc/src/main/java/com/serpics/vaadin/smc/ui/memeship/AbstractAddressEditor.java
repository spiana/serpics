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

import com.serpics.membership.data.model.AbstractAddress;
import com.serpics.vaadin.ui.MasterForm;
import com.serpics.vaadin.ui.component.ExtendedComboBox;
import com.vaadin.addon.jpacontainer.EntityContainer;
import com.vaadin.v7.data.Property.ValueChangeEvent;
import com.vaadin.v7.data.Property.ValueChangeListener;
import com.vaadin.v7.data.util.filter.Compare;
import com.vaadin.v7.ui.Field;


public abstract class AbstractAddressEditor<T extends AbstractAddress > extends MasterForm<T> {
	
	private static final long serialVersionUID = 1L;

    public AbstractAddressEditor(Class<T> clazz) {
		super(clazz);
	}
  

    @Override
    protected void buildContent() {
    	super.buildContent();
    	
    	
    	Field<?> country = fieldGroup.getField("country");
    	if (country != null){
	    	country.addValueChangeListener(new ValueChangeListener() {
				@Override
				public void valueChange(ValueChangeEvent event) {
				com.vaadin.v7.ui.Field.ValueChangeEvent _event = (com.vaadin.v7.ui.Field.ValueChangeEvent) event	;
				
					ExtendedComboBox district = (ExtendedComboBox)fieldGroup.getField("district");
					if (district != null){
						((EntityContainer)district.getContainerDataSource()).removeAllContainerFilters();
							((EntityContainer)district.getContainerDataSource()).addContainerFilter(new Compare.Equal(
								"country" , _event.getProperty().getValue()));
					}
				}
		});
    	}
    }
}
