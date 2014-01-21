package com.serpics.vaadin.ui.memeship;

import com.serpics.membership.persistence.AbstractAddress;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityForm;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Field;

@VaadinComponent("addressEditorComponent")
public class AddressEditorComponent extends EntityForm<AbstractAddress> {
    private static final long serialVersionUID = 1L;

    public AddressEditorComponent() {
        super(AbstractAddress.class);
        final String[] displayProperties = { "firstname", "lastname", "phone", "email", "company",
                "address1",
                "zipcode", "city", "region", "country" };
        this.displayProperties= displayProperties;

    }

    @Override
    public void save() throws CommitException {
        super.save();
        return ;
    }

    @Override
    protected Field<?> createField(final String pid) {

        return super.createField(pid);
    }

}
