


package com.serpics.vaadin.ui.converters;

import com.serpics.base.data.model.MultilingualField;
import com.vaadin.data.util.converter.Converter;
public class MultilingualFieldConvert implements Converter<String, MultilingualField> {
    private static final long serialVersionUID = 1L;
  
    @Override
    public MultilingualField convertToModel(final String value, final Class<? extends MultilingualField> targetType,
            final java.util.Locale locale) throws com.vaadin.data.util.converter.Converter.ConversionException {

          MultilingualField field;
		try {
			field = targetType.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}

        field.addText(locale.getLanguage(), (String) value);
        return field;
    }

    @Override
    public String convertToPresentation(final MultilingualField value, final Class<? extends String> targetType,
            final java.util.Locale locale) throws com.vaadin.data.util.converter.Converter.ConversionException {

        if (value != null && locale != null) {
            return value.getText(locale);
        }

        return "";
    }

 
    @Override
    public Class<MultilingualField> getModelType() {
        return MultilingualField.class;
    }

    @Override
    public Class<String> getPresentationType() {

        return String.class;
    }

}