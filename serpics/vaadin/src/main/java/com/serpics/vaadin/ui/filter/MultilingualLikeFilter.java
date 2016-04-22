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
