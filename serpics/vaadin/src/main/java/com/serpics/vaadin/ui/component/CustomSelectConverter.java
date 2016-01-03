package com.serpics.vaadin.ui.component;

import java.util.Locale;

import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.ui.AbstractSelect;

public class CustomSelectConverter extends SingleSelectConverter{

	private AbstractSelect select;
	public CustomSelectConverter(AbstractSelect select) {
		super(select);
		this.select = select;
	}

	@Override
	public Object convertToPresentation(Object value, Class targetType,
			Locale locale) throws ConversionException {
//			if (value.toString().contains("."))
//				((EntityContainer)this.select.getContainerDataSource()).addNestedContainerProperty(value.toString());
		
		return super.convertToPresentation(value, targetType, locale);
	}
}
