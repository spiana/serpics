package com.serpics.vaadin.ui.memeship;

import com.serpics.membership.data.model.UsersReg;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityForm;

@VaadinComponent("billingAddressEditor")
public class BillingAddressEditor extends EntityForm<UsersReg> {
    private static final long serialVersionUID = 1L;

 
    public BillingAddressEditor() {
        super(UsersReg.class);

    }

    @Override
    public void init() {
        super.init();
        final String[] displayProperties = { "billingAddress" };
        setDisplayProperties(displayProperties);
    }

}
