package com.serpics.vaadin.ui.memeship;

import com.serpics.membership.data.model.AbstractAddress;
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
        final String[] displayProperties = { "firstname", "lastname", "phone", "email", "company",
                "address1",
                "zipcode", "city", "region", "country" };
        setDisplayProperties(displayProperties);
        super.init();
    }

}
