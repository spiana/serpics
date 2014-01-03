package com.serpics.vaadin.ui.memeship;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.core.session.SessionContext;
import com.serpics.membership.persistence.Membergrouprel;
import com.serpics.membership.persistence.User;
import com.serpics.membership.services.MembergrouprelService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.EntityTable;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.filter.Compare;

@VaadinComponent("membergroupRelTable")
public class MembergroupRelTable extends EntityTable<Membergrouprel> {
    private static final long serialVersionUID = 5863975797848978227L;


    private User user;

    @Autowired
    private transient SessionContext sessionContext;

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
        // setEditorWindow(uw) ;
        final String[] p = { "membergroup.name", "status", "validFrom", "validTo" };
        setPropertyToShow(p);
        setService(membergrouprelService);
        super.init();

    }

    @Override
    public void save() throws CommitException {
        // TODO Auto-generated method stub

    }

    @Override
    public void discard() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setEntityItem(final EntityItem<Membergrouprel> entityItem) {

    }

    @Override
    public void setParentEntity(final Object entity) {
        user = (User) entity;
        addFilter(new Compare.Equal("member", this.user));

    }

    @Override
    public EntityItem<Membergrouprel> createEntityItem() {
        final Membergrouprel membergrouprel = new Membergrouprel();
        membergrouprel.setMember(user);
        return cont.createEntityItem(membergrouprel);
    }

}
