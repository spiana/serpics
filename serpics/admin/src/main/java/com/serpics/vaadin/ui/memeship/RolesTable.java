package com.serpics.vaadin.ui.memeship;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.core.service.EntityService;
import com.serpics.membership.persistence.Role;
import com.serpics.membership.services.RoleService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityForm;
import com.serpics.vaadin.ui.EntityTable;

@VaadinComponent(value = "roleTable")
public class RolesTable extends EntityTable<Role> {

    private static final long serialVersionUID = -1487550710132191348L;

    @Autowired
    private transient RoleService roleService;

    public RolesTable() {
        super(Role.class);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public EntityService getService() {
        return roleService;
    }

    @Override
    public void init() {
        super.init();

        final EntityForm<Role> editor = new EntityForm<Role>(Role.class) {
            private static final long serialVersionUID = -8926571251656496441L;

            @Override
            public void init() {
                super.init();
                final String[] p = { "name", "description" };
                setPropertyToShow(p);
            }

        };
        editorWindow.addTab(editor, "main");

        final String[] p = { "name", "description", "updated" };
        setPropertyToShow(p);
    }

}
