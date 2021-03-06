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
package com.serpics.vaadin.jpacontainer.filters;

import com.vaadin.data.Container;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.util.filter.AbstractJunctionFilter;

public class SubQueryFilter extends AbstractJunctionFilter {

	String subqueryProperty ;
	
	public SubQueryFilter( String subqueryProperty ,Filter... filters) {
		super(filters);
		this.subqueryProperty = subqueryProperty;
	}
	@Override
	public boolean passesFilter(Object itemId, Item item)
			throws UnsupportedOperationException {
		for (Container.Filter f : getFilters()) {
			if (!(f.passesFilter(itemId, item))) {
				return false;
			}
		}
		return true;
	}
	public String getSubqueryProperty() {
		return subqueryProperty;
	}
	public void setSubqueryProperty(String subqueryProperty) {
		this.subqueryProperty = subqueryProperty;
	}
	

}
