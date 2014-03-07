package com.serpics.vaadin.ui.memeship;

import com.serpics.membership.persistence.UsersReg;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityForm;

@VaadinComponent("userEditorComponent")
public class UserEditorComponent extends EntityForm<UsersReg> {
    private static final long serialVersionUID = 1L;

    public UserEditorComponent() {

        super(UsersReg.class);

        final String[] displayProperties ={"firstname" , "lastname" , "phone" , "email" , "created"};
        this.displayProperties = displayProperties;
        this.readOnlyProperties = new String[] { "created" };
    }

}
