package com.serpics.vaadin.ui.memeship;

import com.serpics.membership.data.model.UsersReg;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.MasterForm;

@VaadinComponent("primaryAddressEditor")
public class PrimaryAddressEditor extends MasterForm<UsersReg> {
    private static final long serialVersionUID = 1L;

 
    public PrimaryAddressEditor() {
        super(UsersReg.class);
    }

    @Override
    public void init() {
        super.init();
        final String[] displayProperties = { "primaryAddress" };
       setDisplayProperties(displayProperties);
       
    }

    
}
