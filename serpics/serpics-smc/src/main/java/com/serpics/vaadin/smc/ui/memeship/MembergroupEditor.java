package com.serpics.vaadin.smc.ui.memeship;

import com.serpics.membership.data.model.Membergroup;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.MasterForm;

@VaadinComponent("membergroupEditorComponent")
public class MembergroupEditor extends MasterForm<Membergroup> {
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
