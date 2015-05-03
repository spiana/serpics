package com.serpics.vaadin.ui.memeship;

import com.serpics.membership.data.model.UsersReg;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityForm;

@VaadinComponent("userEditorComponent")
public class UserEditorComponent extends EntityForm<UsersReg> {
    private static final long serialVersionUID = 1L;

    public UserEditorComponent() {
        super(UsersReg.class);
    }

    @Override
    public void init() {
        super.init();
        final String[] displayProperties ={"firstname" , "lastname" , "phone" , "email" ,"primaryAddress" ,"created"};
        setDisplayProperties(displayProperties);
        setReadOnlyProperties(new String[] { "created" });
    }
}
