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

import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serpics.i18n.Multilingual;
import com.serpics.vaadin.ui.filter.MultilingualLikeFilter;
import com.serpics.vaadin.ui.filter.MultilingualisNullFilter;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.IsNull;
import com.vaadin.data.util.filter.Not;
import com.vaadin.data.util.filter.SimpleStringFilter;
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
		LESSOREQUAL, GREATEROREQUAL, BETWEEN , ISEMPTY , ISNOTEMPTY;
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
	
	 protected Filter buildMultilinguaStringFilter(String propertyId, String filterString, FilteringMode filteringMode) {
		 	Locale locale = UI.getCurrent().getSession().getLocale();
	        Filter filter = null;
	            switch (filteringMode) {
	            case ISEMPTY:
	            	filter = new MultilingualisNullFilter(propertyId,locale.getLanguage());
	            	break;
	            case ISNOTEMPTY:
		            filter = new Not(new MultilingualisNullFilter(propertyId, locale.getLanguage()));
		            break;
	            case EQUALS:
	            	filter = new MultilingualLikeFilter(propertyId,locale.getLanguage(), filterString, true);
	                break;
	            case ENDWITH:
	            	filterString = '%' +filterString ;
	            	filter = new MultilingualLikeFilter(propertyId,locale.getLanguage(), filterString, false);
	                break;
	            case STARTSWITH:
	            	filterString = filterString + '%';
	            	filter = new MultilingualLikeFilter(propertyId,locale.getLanguage(), filterString, false);
	                break;
	            case CONTAINS:
	            	filterString =  '%' + filterString + '%';
	                filter = new MultilingualLikeFilter(propertyId,locale.getLanguage(), filterString);
	                break;
	            }
	       
	        return filter;
	    }

	 protected Filter buildSimpleStringFilter(String propertyId, String filterString, FilteringMode filteringMode) {
	        Filter filter = null;
	         switch (filteringMode) {
	            case ISEMPTY:
	            	filter = new IsNull(propertyId);
	            	break;
	            case ISNOTEMPTY:
	            	filter = new Not(new IsNull(propertyId));
	            	break;
	            case EQUALS:
	            	filter = new Compare.Equal(propertyId, filterString);
	                break;
	            case STARTSWITH:
	            	filterString = filterString + '%';
	                filter = new SimpleStringFilter(propertyId,filterString, true, true);
	                break;
	            case ENDWITH:
	            	filterString = '%'+filterString ;
	                filter = new SimpleStringFilter(propertyId,filterString, true, true);
	                break;
	            case CONTAINS:
	            	filterString =  '%' + filterString + '%';
	                filter = new SimpleStringFilter(propertyId,filterString, true, false);
	                break;
	            }
	        return filter;
	    }
	 
	 protected Filter buildDateFilter(String propertyId, Comparable<?> valueStart, Comparable<?> valueEnd, FilteringMode filteringMode){
		 Filter filter = null;
		 
            switch (filteringMode) {
	            case ISEMPTY:
	            	filter = new IsNull(propertyId);
	            	break;
	            case ISNOTEMPTY:
	            	filter = new Not(new IsNull(propertyId));
	            	break;
	            case LESSOREQUAL:
	            	filter = new Compare.LessOrEqual(propertyId, valueStart);
	                break;
	            case GREATEROREQUAL:
	            	filter = new Compare.GreaterOrEqual(propertyId, valueStart);
	                break;
	            case BETWEEN:
	            	filter = new Between(propertyId, valueStart	, valueEnd);
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
	        case ISNOTEMPTY:
            	filter = new Not(new IsNull(propertyId));
            	break;	
	        case EQUALS:
	        	filter = new Compare.Equal(propertyId, value);
	        	break;
	        default :
	        	break;
	        }
	      return filter;
	 }
	protected Filter buildNumericFilter(String propertyId, Number valueStart, Number valueEnd, FilteringMode filteringMode){
	
		 Filter filter = null;
	        switch (filteringMode) {
	            case ISEMPTY:
	            	filter = new IsNull(propertyId);
	            	break;
	            case ISNOTEMPTY:
	            	filter = new Not(new IsNull(propertyId));
	            	break;
	            case LESSOREQUAL:
	            	filter = new Compare.LessOrEqual(propertyId, valueStart);
	                break;
	            case GREATEROREQUAL:
	            	filter = new Compare.GreaterOrEqual(propertyId, valueStart);
	                break;
	            case BETWEEN:
	            	filter = new Between(propertyId, (Comparable<?>)valueStart	, (Comparable<?>)valueEnd);
	                break;
            }
	    return filter;
	 }
		
}
