package com.serpics.vaadin.ui.component;

import com.vaadin.addon.jpacontainer.filter.JoinFilter;
import com.vaadin.addon.jpacontainer.provider.SubQueryFilter;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Like;


public class MultilingualLikeFilter extends SubQueryFilter {

	public MultilingualLikeFilter(String propertyId , String language , String text) {
		
		super(propertyId, new JoinFilter("map", new And(new Compare.Equal("language", language), new Like("text", "%"+text+"%", false))));
					
	}	

	private static final long serialVersionUID = -66956295133372820L;
}
