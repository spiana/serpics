package com.serpics.vaadin.ui.memeship;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.membership.persistence.Address;
import com.serpics.membership.persistence.PermanentAddress;
import com.serpics.membership.persistence.User;
import com.serpics.membership.services.PermanentAddressService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.EntityTable;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.filter.Compare;

@VaadinComponent(value="addressTableEditor")
public class AddressTableEditor extends EntityTable<PermanentAddress>{

    private static final long serialVersionUID = -1487550710132191348L;

    @Autowired
    PermanentAddressService addressService;

    @Autowired
    AddressEditorComponent addressEditorComponent;

    EntityItem<PermanentAddress> entityItem ;

    User user ;

    public AddressTableEditor() {
        super(Address.class);
    }

    @Override
    public void init() {
        editorWindow = new EntityFormWindow<PermanentAddress>();
        editorWindow.addTab(addressEditorComponent, "main");
        //setEditorWindow(uw) ;
        final String[] p = { "firstname", "lastname", "company", "address1", "zipcode", "city", "region", "country" };
        setPropertyToShow(p );
        setService(addressService);
        super.init();



    }
    @Override
    public void save() throws CommitException {
        entityList.commit();
    }

    @Override
    public void discard() {
        // TODO Auto-generated method stub
    }

    @Override
    public void setEntityItem(final EntityItem entityItem) {
        this.user = (User) entityItem;
        //	removeAllFilter();
        addFilter(new Compare.Equal("member", user));
    } 
    @Override
    public EntityItem<PermanentAddress> createEntityItem() {
        final PermanentAddress a = new PermanentAddress();
        if (user != null) {
            a.setMember(user);
            // user.getPermanentAddresses().add(a);
        }
        return cont.createEntityItem(a);
    }

    @Override
    public void setParentEntity(final Object entity) {
        this.user = (User) entity;
        //	removeAllFilter();
        addFilter(new Compare.Equal("member", user));
    }

}
