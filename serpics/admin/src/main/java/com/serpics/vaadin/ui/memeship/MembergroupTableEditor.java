package com.serpics.vaadin.ui.memeship;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.membership.persistence.Membergroup;
import com.serpics.membership.services.MembergroupService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.EntityTable;
import com.vaadin.addon.jpacontainer.EntityItem;

@VaadinComponent(value = "membergroupTableEditor")
public class MembergroupTableEditor extends EntityTable<Membergroup> {

    private static final long serialVersionUID = -1487550710132191348L;

    @Autowired
    transient MembergroupService membergroupService;

    @Autowired
    MembergroupEditor membergroupEditor;

    public MembergroupTableEditor() {
        super(Membergroup.class);
    }

    @Override
    public void init() {
        editorWindow = new EntityFormWindow<Membergroup>();
        editorWindow.addTab(membergroupEditor, "main");
        //setEditorWindow(uw) ;
        final String[] p = { "name", "description", "updated" };
        setPropertyToShow(p);
        setService(membergroupService);
        super.init();
    }

    @Override
    public EntityItem<Membergroup> createEntityItem() {
        final Membergroup a = new Membergroup();
        return cont.createEntityItem(a);
    }

}
