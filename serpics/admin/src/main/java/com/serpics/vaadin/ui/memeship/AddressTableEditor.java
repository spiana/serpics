package com.serpics.vaadin.ui.memeship;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.core.service.EntityService;
import com.serpics.membership.persistence.PermanentAddress;
import com.serpics.membership.persistence.User;
import com.serpics.membership.services.PermanentAddressService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityTableChild;
import com.vaadin.addon.jpacontainer.EntityItem;

@VaadinComponent(value = "addressTableEditor")
public class AddressTableEditor extends EntityTableChild<PermanentAddress, User> {

    private static final long serialVersionUID = -1487550710132191348L;

    @Autowired
    private transient PermanentAddressService addressService;

    @Autowired
    private AddressEditorComponent addressEditorComponent;

    public AddressTableEditor() {
        super(PermanentAddress.class);
    }

    @Override
    public void init() {
        super.init();
        editorWindow.addTab(addressEditorComponent, "main");
        final String[] p = { "firstname", "lastname", "company", "address1", "zipcode", "city", "region",
        "country" };
        setParentProperty("member");
        setPropertyToShow(p );

    }

    @Override
    public EntityItem<PermanentAddress> createEntityItem() {
        final PermanentAddress a = new PermanentAddress();
        a.setMember(parent.getEntity());
        return container.createEntityItem(a);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public EntityService getService() {
        return addressService;
    }

}
