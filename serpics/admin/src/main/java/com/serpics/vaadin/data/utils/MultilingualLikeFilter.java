package com.serpics.vaadin.data.utils;

import com.vaadin.addon.jpacontainer.filter.JoinFilter;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Like;

public class MultilingualLikeFilter extends JoinFilter {

	public MultilingualLikeFilter(String propertyId , String language , String text) {
		super(propertyId, new JoinFilter("map", new Compare.Equal("language", language)),
				new JoinFilter("map", new Like("text", text)));
		
	}

	
	private static final long serialVersionUID = -66956295133372820L;

}
