package com.serpics.vaadin.ui;

import com.vaadin.data.Property;
import com.vaadin.ui.TextField;

public class MultilingualTextField extends TextField {
    private static final long serialVersionUID = -8222498672841576094L;

    public MultilingualTextField() {
        super();
        setConverter(new MultilingualFieldConvert());

    }

    public MultilingualTextField(final Property dataSource) {
        super(dataSource);
        setConverter(new MultilingualFieldConvert());

    }

    public MultilingualTextField(final String caption, final Property dataSource) {
        super(caption, dataSource);
        setConverter(new MultilingualFieldConvert());
    }

    public MultilingualTextField(final String caption, final String value) {
        super(caption, value);
        setConverter(new MultilingualFieldConvert());
    }

    public MultilingualTextField(final String caption) {
        super(caption);
        setConverter(new MultilingualFieldConvert());
    }

}
