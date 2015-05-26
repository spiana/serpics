package com.serpics.vaadin.ui.memeship;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.core.data.Repository;
import com.serpics.core.service.EntityService;
import com.serpics.membership.data.model.Membergroup;
import com.serpics.membership.data.repositories.MemberGroupRepository;
import com.serpics.membership.services.MembergroupService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterTable;

@VaadinComponent(value = "membergroupTableEditor")
public class MembergroupTableEditor extends MasterTable<Membergroup> {

    private static final long serialVersionUID = -1487550710132191348L;


    @Autowired
    MembergroupEditor membergroupEditor;

    public MembergroupTableEditor() {
        super(Membergroup.class);
    }

   
    @Override
    public EntityFormWindow<Membergroup> buildEntityWindow() {
    	EntityFormWindow<Membergroup> _e = new EntityFormWindow<Membergroup>();
    	_e.addTab(membergroupEditor, "main");
    	return _e;
    }
    @Override
    public void init() {
        super.init();
        final String[] p = { "name", "description", "updated" };
        setPropertyToShow(p);

    }

}
