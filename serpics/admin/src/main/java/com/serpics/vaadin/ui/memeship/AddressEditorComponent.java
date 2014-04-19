package com.serpics.vaadin.ui.memeship;

import com.serpics.membership.persistence.AbstractAddress;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityForm;

@VaadinComponent("addressEditorComponent")
public class AddressEditorComponent extends EntityForm<AbstractAddress> {
    private static final long serialVersionUID = 1L;

    public AddressEditorComponent() {
        super(AbstractAddress.class);

    }

    @Override
    public void init() {
        super.init();
        final String[] displayProperties = { "firstname", "lastname", "phone", "email", "company",
                "address1",
                "zipcode", "city", "region", "country" };
        setDisplayProperties(displayProperties);

    }

}
