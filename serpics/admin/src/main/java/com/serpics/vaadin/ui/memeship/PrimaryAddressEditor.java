package com.serpics.vaadin.ui.memeship;

import com.serpics.membership.data.model.UsersReg;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityForm;

@VaadinComponent("primaryAddressEditor")
public class PrimaryAddressEditor extends EntityForm<UsersReg> {
    private static final long serialVersionUID = 1L;

 
    public PrimaryAddressEditor() {
        super(UsersReg.class);

    }

    @Override
    public void init() {
        super.init();
        final String[] displayProperties = { "primaryAddress" };
        setReadOnlyProperties(new String[] {"primaryAddress.created" , "primaryAddress.updated" , "primaryAddress.uuid"});
        setDisplayProperties(displayProperties);
       
    }

    
}
