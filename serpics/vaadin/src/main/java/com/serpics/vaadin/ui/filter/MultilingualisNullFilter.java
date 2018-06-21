package com.serpics.vaadin.ui.filter;

import com.serpics.i18n.data.model.MultilingualField;
import com.vaadin.v7.data.Item;
import com.vaadin.v7.data.Property;

public class MultilingualisNullFilter implements com.vaadin.v7.data.Container.Filter {

	private Object propertyId;
	private String language;
	
	
	public MultilingualisNullFilter(Object propertyId, String language) {
		super();
		this.propertyId = propertyId;
		this.language = language;
	}

	@Override
	public boolean passesFilter(Object itemId, Item item) throws UnsupportedOperationException {
		 final Property<?> p = item.getItemProperty(getPropertyId());
	        if (null == p) {
	            return false;
	        }
	        MultilingualField m = (MultilingualField) p.getValue();
	        
	        return null == m.getText(language);
	}

	@Override
	public boolean appliesToProperty(Object propertyId) {
		 return getPropertyId().equals(propertyId);
	}

	 @Override
	    public boolean equals(Object obj) {
	        if (obj == null) {
	            return false;
	        }

	        // Only objects of the same class can be equal
	        if (!getClass().equals(obj.getClass())) {
	            return false;
	        }
	        final MultilingualisNullFilter o = (MultilingualisNullFilter) obj;

	        // Checks the properties one by one
	        return (null != getPropertyId()) ? getPropertyId().equals(
	                o.getPropertyId()) : null == o.getPropertyId();
	    }

	    @Override
	    public int hashCode() {
	        return (null != getPropertyId() ? getPropertyId().hashCode() : 0);
	    }
	    
	    /**
	     * Returns the property id of the property tested by the filter, not null
	     * for valid filters.
	     * 
	     * @return property id (not null)
	     */
	    public Object getPropertyId() {
	        return propertyId;
	    }

}
