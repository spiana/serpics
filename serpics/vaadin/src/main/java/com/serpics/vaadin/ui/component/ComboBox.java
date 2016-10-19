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
package com.serpics.vaadin.ui.component;

import java.util.Collection;

import com.serpics.base.data.model.MultilingualField;
import com.serpics.vaadin.ui.converters.MultilingualFieldConvert;
import com.serpics.vaadin.ui.filter.MultilingualLikeFilter;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.shared.ui.combobox.FilteringMode;

/**
 * @author spiana
 *
 */
public class ComboBox extends com.vaadin.ui.ComboBox {

	/* (non-Javadoc)
	 * @see com.vaadin.ui.AbstractComponent#getCaption()
	 */
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7619403808959605717L;

	public ComboBox() {
		super();
	}

	public ComboBox(String caption, Collection<?> options) {
		super(caption, options);
	
	}

	public ComboBox(String caption, Container dataSource) {
		super(caption, dataSource);
		
	}

	public ComboBox(String caption) {
		super(caption);
	}


	
	 public String getItemCaption(Object itemId) {

	        // Null items can not be found
	        if (itemId == null) {
	            return null;
	        }

	        String caption = null;

	        switch (getItemCaptionMode()) {

	        case ID:
	        	caption = super.getItemCaption(itemId);
	            break;

	        case INDEX:
	        	caption = super.getItemCaption(itemId);
	            break;

	        case ITEM:
	        	caption = super.getItemCaption(itemId);
	            break;

	        case EXPLICIT:
	        	super.getItemCaption(itemId);
	            break;

	        case EXPLICIT_DEFAULTS_ID:
	        	caption = super.getItemCaption(itemId);
	            break;

	        case PROPERTY:
	            final Property<?> p = getContainerProperty(itemId,
	                    getItemCaptionPropertyId());
	            
	            
	            if (p != null) {
	            	if (MultilingualField.class.isAssignableFrom(p.getType())){
	            		caption = new MultilingualFieldConvert().convertToPresentation((MultilingualField) p.getValue(), String.class, getSession().getLocale());
	            	}
	            	else{
		            	Object value = p.getValue();
		                if (value != null) {
		                    caption = value.toString();
		                }
	            	}
	            }
	           
	            break;
	        }
	        
	        return caption;
	 }  
	 
	 
	@Override
	 protected Filter buildFilter(String filterString,
	            FilteringMode filteringMode) {
	        Filter filter = null;
	        
	        Class<?> type=  getContainerDataSource().getType(getItemCaptionPropertyId());
	        
	       

	        if (null != filterString && !"".equals(filterString)) {
	            switch (filteringMode) {
	            case OFF:
	                break;
	            case STARTSWITH:
	            	if (MultilingualField.class.isAssignableFrom(type))
	            		filter = new MultilingualLikeFilter(getItemCaptionPropertyId().toString(), getSession().getLocale().getLanguage(), filterString);
	            	else
	            		filter = new SimpleStringFilter(getItemCaptionPropertyId(),
	            				filterString, true, true);
	                break;
	            case CONTAINS:
	            	if (MultilingualField.class.isAssignableFrom(type))
	            		filter = new MultilingualLikeFilter(getItemCaptionPropertyId().toString(), getSession().getLocale().getLanguage(), filterString);
	            	else
	            		filter = new SimpleStringFilter(getItemCaptionPropertyId(),
	            				filterString, true, false);
	                break;
	            }
	        }
	        return filter;
	    }
	
	
	
	
}



