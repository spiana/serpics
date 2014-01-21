package com.serpics.vaadin.ui.memeship;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.membership.persistence.PermanentAddress;
import com.serpics.membership.persistence.User;
import com.serpics.membership.services.PermanentAddressService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.EntityTableChild;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.data.util.filter.Compare;

@VaadinComponent(value="addressTableEditor")
public class AddressTableEditor extends EntityTableChild<PermanentAddress, User> {

    private static final long serialVersionUID = -1487550710132191348L;

    @Autowired
    private transient PermanentAddressService addressService;

    @Autowired
    AddressEditorComponent addressEditorComponent;

    public AddressTableEditor() {
        super(PermanentAddress.class);
    }

    @Override
    public void init() {
        editorWindow = new EntityFormWindow<PermanentAddress>();
        editorWindow.addTab(addressEditorComponent, "main");
        //setEditorWindow(uw) ;
        final String[] p = { "isprimary", "firstname", "lastname", "company", "address1", "zipcode", "city", "region",
        "country" };
        setPropertyToShow(p );
        setService(addressService);
        super.init();



    }

    @Override
    public EntityItem<PermanentAddress> createEntityItem() {
        final PermanentAddress a = new PermanentAddress();
        a.setIsprimary(0);
        a.setMember(parent.getEntity());
        return cont.createEntityItem(a);
    }

    @Override
    public void setParentEntity(final EntityItem<User> parent) {
        super.setParentEntity(parent);
        removeAllFilter();
        addFilter(new Compare.Equal("member", parent.getEntity()));

    }

}
