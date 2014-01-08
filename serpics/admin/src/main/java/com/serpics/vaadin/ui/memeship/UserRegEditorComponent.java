package com.serpics.vaadin.ui.memeship;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.admin.SerpicsContainerFactory;
import com.serpics.membership.persistence.User;
import com.serpics.membership.persistence.UsersReg;
import com.serpics.membership.services.UserRegService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityFormChild;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.SerpicsPersistentContainer;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;

@VaadinComponent("userRegEditorComponent")
public class UserRegEditorComponent extends EntityFormChild<UsersReg, User> {
    private static final long serialVersionUID = 8474927861483565203L;

    @Autowired
    private transient UserRegService userRegService;

    private SerpicsPersistentContainer<UsersReg> container;

    public UserRegEditorComponent() {
        super(UsersReg.class);
        final String[] p = { "logonid", "password", "alternateEmail" };
        // setDisplayProperties(p);
        final String[] readonlyProps = { "lastLogin", "created", "updated", "passwordChange", "lastLogin" };
        setReadOnlyProperties(readonlyProps);
        setHideProperties(new String[] { "localeId", "uuid" });

    }

    @Override
    public void init() {
        container = SerpicsContainerFactory.make(UsersReg.class,
                userRegService);
        super.init();
    }


    @Override
    public void save() throws CommitException {

        if (isModifield() && parent != null) {
            if (entityItem.getItemId() == null) {
                entityItem.getEntity().setUserId(parent.getEntity().getUserId());
                entityItem.getEntity().setUser(parent.getEntity());
                super.save();


            } else
                super.save();
        }
    }

    @Override
    public void setParentEntity(final EntityItem<User> parent) {
        super.setParentEntity(parent);
        EntityItem<UsersReg> e = container.getItem(parent.getItemId());
        if (e == null)
            e = container.createEntityItem(new UsersReg());
        setEntityItem(e);
    }


}

