package com.serpics.vaadin.ui.memeship;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.membership.Member2GroupRelType;
import com.serpics.membership.data.model.Membergrouprel;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.repositories.MembergrouprelRepository;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterDetailTable;
import com.vaadin.addon.jpacontainer.EntityItem;


@VaadinComponent("membergroupRelTable")
public class MembergroupRelTable extends MasterDetailTable<Membergrouprel , User> {
    private static final long serialVersionUID = 5863975797848978227L;

    @Autowired
    private transient MembergrouprelRepository membergrouprelRepository;

    @Autowired
    MembergroupRelEditor membergroupRelEditor;

    public MembergroupRelTable() {
        super(Membergrouprel.class);
    }

    @Override
    public EntityFormWindow<Membergrouprel> buildEntityWindow() {
    	EntityFormWindow<Membergrouprel> _e = new EntityFormWindow<Membergrouprel>();
    	_e.addTab(membergroupRelEditor, "main");
    	return _e;
    }
  
    @Override
    public void init() {
        super.init();
        container.addNestedContainerProperty("membergroup.*");
        final String[] p = { "membergroup.name", "status", "validFrom", "validTo" };
        setPropertyToShow(p);
        setParentProperty("member");
    }


    @Override
    public EntityItem<Membergrouprel> createEntityItem() {
        final Membergrouprel membergrouprel = new Membergrouprel();
        membergrouprel.setMember(parent.getEntity());
        membergrouprel.setStatus(Member2GroupRelType.EFFECTIVE);
        membergrouprel.setValidFrom(new Date());
        membergrouprel.setValidTo(new Date(new Double(4.071e+12 + 3.136e+10).longValue()));
        return container.createEntityItem(membergrouprel);
    }

}
