package com.serpics.vaadin.smc.ui.memeship;

import com.serpics.membership.data.model.Role;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.MasterForm;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterTable;


@VaadinComponent(value = "roleTable")
public class RolesTable extends MasterTable<Role> {

    private static final long serialVersionUID = -1487550710132191348L;

    public RolesTable() {
        super(Role.class);
    }

    @Override
    public EntityFormWindow<Role> buildEntityWindow() {
    	EntityFormWindow<Role> _e = new EntityFormWindow<Role>();
    	
    	final MasterForm<Role> editor = new MasterForm<Role>(Role.class) {
            private static final long serialVersionUID = -8926571251656496441L;

            @Override
            public void init() {
                super.init();
                final String[] p = { "name", "description" };
                setPropertyToShow(p);
            }

        };
        _e.addTab(editor, "main");
    	return _e;
    }
    
    @Override
    public void init() {
        super.init();

        final MasterForm<Role> editor = new MasterForm<Role>(Role.class) {
            private static final long serialVersionUID = -8926571251656496441L;

            @Override
            public void init() {
                super.init();
                final String[] p = { "name", "description" };
                setPropertyToShow(p);
            }

        };
       

        final String[] p = { "name", "description", "updated" };
        setPropertyToShow(p);
    }


}
