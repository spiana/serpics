package com.serpics.vaadin.ui.memeship;

import com.serpics.membership.persistence.User;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityForm;

@VaadinComponent("userEditorComponent")
public class UserEditorComponent extends EntityForm<User>{
    private static final long serialVersionUID = 1L;

    public UserEditorComponent() {

        super(User.class);

        final String[] displayProperties ={"firstname" , "lastname" , "phone" , "email" , "created"};
        this.displayProperties = displayProperties;
        this.readOnlyProperties = new String[] { "created" };
    }

}
