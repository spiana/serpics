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
