package com.serpics.vaadin.ui.memeship;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.membership.Member2GroupRelType;
import com.serpics.membership.persistence.Membergrouprel;
import com.serpics.membership.persistence.User;
import com.serpics.membership.services.MembergrouprelService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.EntityTableChild;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.data.util.filter.Compare;

@VaadinComponent("membergroupRelTable")
public class MembergroupRelTable extends EntityTableChild<Membergrouprel , User> {
    private static final long serialVersionUID = 5863975797848978227L;

    @Autowired
    private transient MembergrouprelService membergrouprelService;

    @Autowired
    MembergroupRelEditor membergroupRelEditor;

    public MembergroupRelTable() {
        super(Membergrouprel.class);
    }

    @Override
    public void init() {
        cont.addNestedContainerProperty("membergroup.*");
        editorWindow = new EntityFormWindow<Membergrouprel>();
        editorWindow.addTab(membergroupRelEditor, "main");
        final String[] p = { "membergroup.name", "status", "validFrom", "validTo" };
        setPropertyToShow(p);
        setService(membergrouprelService);
        super.init();

    }


    @Override
    public void setParentEntity(final EntityItem<User> parent) {
        super.setParentEntity(parent);
        removeAllFilter();
        addFilter(new Compare.Equal("member", parent.getEntity()));
    }


    @Override
    public EntityItem<Membergrouprel> createEntityItem() {
        final Membergrouprel membergrouprel = new Membergrouprel();
        membergrouprel.setMember(parent.getEntity());
        membergrouprel.setStatus(Member2GroupRelType.EFFECTIVE);
        membergrouprel.setValidFrom(new Date());
        membergrouprel.setValidTo(new Date(new Double(4.071e+12 + 3.136e+10).longValue()));
        return cont.createEntityItem(membergrouprel);
    }

}
