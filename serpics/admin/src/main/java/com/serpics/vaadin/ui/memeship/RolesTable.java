package com.serpics.vaadin.ui.memeship;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.membership.persistence.Role;
import com.serpics.membership.services.RoleService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityForm;
import com.serpics.vaadin.ui.EntityTable;
import com.vaadin.ui.Field;

@VaadinComponent(value = "roleTable")
public class RolesTable extends EntityTable<Role> {

    private static final long serialVersionUID = -1487550710132191348L;

    @Autowired
    transient RoleService roleService;

    public RolesTable() {
        super(Role.class);
    }

    @Override
    public void init() {

        final EntityForm<Role> editor = new EntityForm<Role>(Role.class) {
            @Override
            public void init() {
                final String[] p = { "name", "description" };
                setPropertyToShow(p);
                super.init();
            }

            @Override
            protected Field<?> createField(final String pid) {
                final Field<?> f = super.createField(pid);

                if (pid.equals("description"))
                    f.setWidth("80%");
                return f;
            }
        };

        editorWindow.addTab(editor, "main");

        final String[] p = { "name", "description", "updated" };
        setPropertyToShow(p);
        setService(roleService);
        super.init();
    }

}
