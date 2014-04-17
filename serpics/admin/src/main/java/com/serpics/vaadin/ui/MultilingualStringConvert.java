package com.serpics.vaadin.ui;

import com.serpics.base.persistence.MultilingualString;
import com.vaadin.data.util.converter.Converter;
public class MultilingualStringConvert implements Converter<String, MultilingualString> {
    private static final long serialVersionUID = 1L;

    MultilingualString field;

    @Override
    public MultilingualString convertToModel(final String value, final Class<? extends MultilingualString> targetType,
            final java.util.Locale locale) throws com.vaadin.data.util.converter.Converter.ConversionException {

        if (field == null)
            field = new MultilingualString();

        field.addText(locale.getLanguage(), (String) value);
        return field;
    }

    @Override
    public String convertToPresentation(final MultilingualString value, final Class<? extends String> targetType,
            final java.util.Locale locale) throws com.vaadin.data.util.converter.Converter.ConversionException {

        if (value != null && locale != null) {
            field = value;
            return value.getText(locale);
        }

        return "";
    }

    @Override
    public Class<MultilingualString> getModelType() {
        return MultilingualString.class;
    }

    @Override
    public Class<String> getPresentationType() {

        return String.class;
    }

}
