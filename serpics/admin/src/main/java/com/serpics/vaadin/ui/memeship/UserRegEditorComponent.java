package com.serpics.vaadin.ui.memeship;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.membership.UserRegStatus;
import com.serpics.membership.persistence.UsersReg;
import com.serpics.membership.services.UserRegService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityForm;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;

@VaadinComponent("userRegEditorComponent")
public class UserRegEditorComponent extends EntityForm<UsersReg> {
    private static final long serialVersionUID = 8474927861483565203L;

    @Autowired
    private transient UserRegService userRegService;



    public UserRegEditorComponent() {
        super(UsersReg.class);
        final String[] p = { "logonid", "password", "alternateEmail" };
        // setDisplayProperties(p);
        final String[] readonlyProps = { "lastLogin", "created", "updated", "passwordChange", "lastLogin" };
        setDisplayProperties(p);
        // setReadOnlyProperties(readonlyProps);
        setHideProperties(new String[] { "localeId", "uuid" });

    }

    @Override
    protected Field<?> createField(final String pid) {

        if (pid.equals("status")) {
            final ComboBox combo = new ComboBox("status");
            combo.setNullSelectionAllowed(false);
            combo.addItem(UserRegStatus.ACTIVE);
            combo.addItem(UserRegStatus.WAITING);
            fieldGroup.bind(combo, "status");
            return combo;
        }

        return super.createField(pid);
    }

}

