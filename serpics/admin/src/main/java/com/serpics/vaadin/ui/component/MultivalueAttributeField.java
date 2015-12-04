package com.serpics.vaadin.ui.component;

import com.serpics.base.MultiValueField;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomField;

public class MultivalueAttributeField<T extends MultiValueField> extends CustomField<T>{

	EntityItem<T> masterEntity;
	Object propertyId ;
	
	public MultivalueAttributeField(EntityItem<T> item , Object propertyId) {
		super();
		this.masterEntity = item;
		this.propertyId =propertyId;
	}

	@Override
	protected Component initContent() {
		CssLayout layout = new CssLayout();
		
		
		
		
		return layout;
	}

	@Override
	public Class<? extends T> getType() {
		return masterEntity.getItemProperty(propertyId).getType();
	}


}
