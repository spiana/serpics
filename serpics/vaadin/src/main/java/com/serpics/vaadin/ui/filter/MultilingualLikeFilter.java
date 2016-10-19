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
package com.serpics.vaadin.ui.filter;

import com.serpics.vaadin.jpacontainer.filters.SubQueryFilter;
import com.vaadin.addon.jpacontainer.filter.JoinFilter;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Like;


public class MultilingualLikeFilter extends SubQueryFilter {

	public MultilingualLikeFilter(String propertyId , String language , String text) {
		super(propertyId, new JoinFilter("map", new And(new Compare.Equal("language", language), new Like("text", "%"+text+"%", false))));
	}	

	public MultilingualLikeFilter(String propertyId , String language , String text, boolean caseSensitive) {
			super(propertyId, new JoinFilter("map", new And(new Compare.Equal("language", language), new Like("text", text, caseSensitive))));
	}	

	private static final long serialVersionUID = -66956295133372820L;
}
