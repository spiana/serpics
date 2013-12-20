package com.serpics.vaadin.ui.memeship;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.membership.persistence.Membergroup;
import com.serpics.membership.services.MembergroupService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.EntityTable;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;

@VaadinComponent(value = "membergroupTableEditor")
public class MembergroupTableEditor extends EntityTable<Membergroup> {

    private static final long serialVersionUID = -1487550710132191348L;


    @Autowired
    MembergroupService membergroupService;

    @Autowired
    MembergroupEditor membergroupEditor;

    EntityItem<Membergroup> entityItem;



    public MembergroupTableEditor() {
        super(Membergroup.class);
    }

    @Override
    public void init() {
        editorWindow = new EntityFormWindow<Membergroup>();
        editorWindow.addTab(membergroupEditor, "main");
        //setEditorWindow(uw) ;
        // final String[] p = {"firstname" , "lastname" };
        // setPropertyToShow(p );
        setService(membergroupService);
        super.init();



    }
    @Override
    public void save() throws CommitException {
        entityList.commit();
    }

    @Override
    public void discard() {
        entityList.discard();
    }

    @Override
    public void setEntityItem(final EntityItem<Membergroup> entityItem) {
        this.entityItem = entityItem;
    } 
    @Override
    public EntityItem<Membergroup> createEntityItem() {
        final Membergroup a = new Membergroup();
        return cont.createEntityItem(a);
    }

    @Override
    public void setParentEntity(final Object entity) {
        // TODO Auto-generated method stub

    }


}
