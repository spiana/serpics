package com.serpics.vaadin.ui.memeship;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.membership.persistence.UsersReg;
import com.serpics.membership.services.UserRegService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.EntityTable;
import com.vaadin.addon.jpacontainer.EntityItem;

@VaadinComponent(value = "userRegTableEditor")
public class UserRegTableEditor extends EntityTable<UsersReg> {

    private static final long serialVersionUID = -8370714049392595536L;

    @Autowired
    private transient UserRegService userService;

    EntityItem<UsersReg> entityItem;

    public UserRegTableEditor() {
        super(UsersReg.class);
    } 

    @Override
    public void init() {
        editorWindow = new EntityFormWindow<UsersReg>();

        final String[] p = { "logonid" };
        setPropertyToShow(p);
        setService(userService);
        super.init();


    }
}
