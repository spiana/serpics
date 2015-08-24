package com.serpics.vaadin.ui.memeship;

import com.serpics.membership.data.model.PermanentAddress;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.MasterForm;

@VaadinComponent("addressEditorComponent")
public class AddressEditorComponent extends MasterForm<PermanentAddress> {
    private static final long serialVersionUID = 1L;

    public AddressEditorComponent() {
        super(PermanentAddress.class);

    }

    @Override
    public void init() {
        final String[] displayProperties = { "firstname", "lastname", "phone", "email", "company",
                "address1",
                "zipcode", "city", "region", "country" };
        setDisplayProperties(displayProperties);
        super.init();
    }

}
