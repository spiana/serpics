package com.serpics.vaadin.ui.memeship;

import com.serpics.membership.persistence.User;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityForm;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;

@VaadinComponent("addressEditorComponent")
public class AddressEditorComponent extends EntityForm<User>{
    private static final long serialVersionUID = 1L;

    public AddressEditorComponent() {
        super(User.class);
        final String[] displayProperties = { "firstname", "lastname", "phone", "email", "company", "address1",
                "zipcode", "city", "region", "country" };
        this.displayProperties= displayProperties;

    }

    @Override
    public void save() throws CommitException {
        super.save();
        return ;
    }

}
