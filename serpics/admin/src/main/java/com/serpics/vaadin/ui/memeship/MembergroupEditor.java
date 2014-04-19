package com.serpics.vaadin.ui.memeship;

import com.serpics.membership.persistence.Membergroup;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityForm;

@VaadinComponent("membergroupEditorComponent")
public class MembergroupEditor extends EntityForm<Membergroup> {
    private static final long serialVersionUID = 1L;

    public MembergroupEditor() {
        super(Membergroup.class);

    }

    @Override
    public void init() {

        super.init();
        final String[] displayProperties = { "name", "description" };
        setDisplayProperties(displayProperties);
    }

}
