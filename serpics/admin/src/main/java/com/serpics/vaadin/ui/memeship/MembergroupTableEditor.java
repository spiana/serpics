package com.serpics.vaadin.ui.memeship;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.core.data.Repository;
import com.serpics.core.service.EntityService;
import com.serpics.membership.persistence.Membergroup;
import com.serpics.membership.repositories.MemberGroupRepository;
import com.serpics.membership.services.MembergroupService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityTable;

@VaadinComponent(value = "membergroupTableEditor")
public class MembergroupTableEditor extends EntityTable<Membergroup> {

    private static final long serialVersionUID = -1487550710132191348L;


    @Autowired
    MembergroupEditor membergroupEditor;

    public MembergroupTableEditor() {
        super(Membergroup.class);
    }

   
    @Override
    public void init() {
        super.init();
        editorWindow.addTab(membergroupEditor, "main");
        final String[] p = { "name", "description", "updated" };
        setPropertyToShow(p);

    }

}
