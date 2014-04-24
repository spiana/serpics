package com.serpics.vaadin.ui.memeship;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.membership.persistence.UsersReg;
import com.serpics.membership.services.PrimaryAddressService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityForm;

@VaadinComponent("primaryAddressEditor")
public class PrimaryAddressEditor extends EntityForm<UsersReg> {
    private static final long serialVersionUID = 1L;

    @Autowired
    private transient PrimaryAddressService addressService;

    public PrimaryAddressEditor() {
        super(UsersReg.class);

    }

    @Override
    public void init() {
        super.init();
        final String[] displayProperties = { "primaryAddress.firstname", "primaryAddress.lastname",
                "primaryAddress.phone", "primaryAddress.email", "primaryAddress.company", "primaryAddress.address1",
                "primaryAddress.zipcode", "primaryAddress.city", "primaryAddress.region", "primaryAddress.country" };

        setDisplayProperties(displayProperties);
    }

}
