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
package com.serpics.vaadin.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serpics.base.Multilingual;
import com.serpics.base.data.model.MultilingualString;
import com.serpics.vaadin.ui.component.MultilingualTextField;
import com.serpics.vaadin.ui.filter.MultilingualLikeFilter;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.filter.Filters;
import com.vaadin.data.Container;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.IsNull;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public class FilterComponentUtils {

	private static final long serialVersionUID = -2736583181645447496L;
	private static transient Logger LOG = LoggerFactory.getLogger(FilterComponentUtils.class);

	private static FilterComponentUtils instance; 
	
	public FilterComponentUtils() {
		super();
	}

	public static FilterComponentUtils get() {
		if (instance == null)
			instance = new FilterComponentUtils();
		return instance;
	}
	
	public enum FilteringMode {
	    OFF,EQUALS, STARTSWITH,ENDWITH, CONTAINS,
		LESSOREQUAL, GREATEROREQUAL, BETWEEN , ISEMPTY;
	}

	
	public Filter addFilter(Class<?> type, final String entry, FilteringMode filteringMode,  final Object value, final Object value1){
		Filter filter = null;
		if (entry != null) {
			LOG.info("filter mode : [{}]", filteringMode);
			if (filteringMode != FilteringMode.OFF){
			if (Multilingual.class.isAssignableFrom(type)) {
				filter = buildMultilinguaStringFilter(entry, value.toString(), filteringMode);
			} else if(String.class.isAssignableFrom(type)) {
				filter = buildSimpleStringFilter(entry, value.toString(), filteringMode);
			} else if(Date.class.isAssignableFrom(type)) {
				filter = buildDateFilter(entry, (Date)value, (Date) value1, filteringMode);
			} else if(Boolean.class.isAssignableFrom(type)) {	
				filter = new Compare.Equal(entry, value);
			} else if(Number.class.isAssignableFrom(type)) {
				filter = buildNumericFilter(entry, (Number)value, (Number)value1, filteringMode);
			}else if (Enum.class.isAssignableFrom(type)){
				filter = buildFilter(entry, value, filteringMode);
			} else 
				LOG.warn("no type detected ! could not ctrate a new filter ");
			}
		}
		return filter;
	}
	
	 protected Filter buildMultilinguaStringFilter(String entry, String filterString, FilteringMode filteringMode) {
		 	Locale locale = UI.getCurrent().getSession().getLocale();
	        Filter filter = null;
	            switch (filteringMode) {
	            case ISEMPTY:
	            	filter = new MultilingualLikeFilter(entry,locale.getLanguage(), "" , true);
	            	break;
	            case EQUALS:
	            	filter = new MultilingualLikeFilter(entry,locale.getLanguage(), filterString, true);
	                break;
	            case ENDWITH:
	            	filterString = '%' +filterString ;
	            	filter = new MultilingualLikeFilter(entry,locale.getLanguage(), filterString, false);
	                break;
	            case STARTSWITH:
	            	filterString = filterString + '%';
	            	filter = new MultilingualLikeFilter(entry,locale.getLanguage(), filterString, false);
	                break;
	            case CONTAINS:
	            	filterString =  '%' + filterString + '%';
	                filter = new MultilingualLikeFilter(entry,locale.getLanguage(), filterString);
	                break;
	            }
	       
	        return filter;
	    }

	 protected Filter buildSimpleStringFilter(String entry, String filterString, FilteringMode filteringMode) {
	        Filter filter = null;
	         switch (filteringMode) {
	            case ISEMPTY:
	            	filter = new IsNull(entry);
	            case EQUALS:
	            	filter = new Compare.Equal(entry, filterString);
	                break;
	            case STARTSWITH:
	            	filterString = filterString + '%';
	                filter = new SimpleStringFilter(entry,filterString, true, true);
	                break;
	            case ENDWITH:
	            	filterString = '%'+filterString ;
	                filter = new SimpleStringFilter(entry,filterString, true, true);
	                break;
	            case CONTAINS:
	            	filterString =  '%' + filterString + '%';
	                filter = new SimpleStringFilter(entry,filterString, true, false);
	                break;
	            }
	        return filter;
	    }
	 
	 protected Filter buildDateFilter(String entry, Comparable<?> valueStart, Comparable<?> valueEnd, FilteringMode filteringMode){
		 Filter filter = null;
		 
            switch (filteringMode) {
	            case ISEMPTY:
	            	filter = new IsNull(entry);
	            	break;
	            case LESSOREQUAL:
	            	filter = new Compare.LessOrEqual(entry, valueStart);
	                break;
	            case GREATEROREQUAL:
	            	filter = new Compare.GreaterOrEqual(entry, valueStart);
	                break;
	            case BETWEEN:
	            	filter = new Between(entry, valueStart	, valueEnd);
	                break;
            }
	       
		 return filter;
	 }
	 
	 protected Filter buildFilter(String propertyId, Object value, FilteringMode filteringMode)
	 {
		 Filter filter = null;
	        switch (filteringMode) {
	        case ISEMPTY:
            	filter = new IsNull(propertyId);
            	break;
	        case EQUALS:
	        	filter = new Compare.Equal(propertyId, value);
	        	break;
	        default :
	        	break;
	        }
	      return filter;
	 }
	protected Filter buildNumericFilter(String entry, Number valueStart, Number valueEnd, FilteringMode filteringMode){
	
		 Filter filter = null;
	        switch (filteringMode) {
	            case ISEMPTY:
	            	filter = new IsNull(entry);
	            	break;
	            case LESSOREQUAL:
	            	filter = new Compare.LessOrEqual(entry, valueStart);
	                break;
	            case GREATEROREQUAL:
	            	filter = new Compare.GreaterOrEqual(entry, valueStart);
	                break;
	            case BETWEEN:
	            	filter = new Between(entry, (Comparable<?>)valueStart	, (Comparable<?>)valueEnd);
	                break;
            }
	    return filter;
	 }
		
}
