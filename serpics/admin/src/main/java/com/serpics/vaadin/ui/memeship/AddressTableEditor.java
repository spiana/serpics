package com.serpics.vaadin.ui.memeship;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.membership.data.model.PermanentAddress;
import com.serpics.membership.data.model.User;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterDetailTable;
import com.vaadin.addon.jpacontainer.EntityItem;


@VaadinComponent(value = "addressTableEditor")
public class AddressTableEditor extends MasterDetailTable<PermanentAddress, User> {

    private static final long serialVersionUID = -1487550710132191348L;

    @Autowired
    private AddressEditorComponent addressEditorComponent;

    public AddressTableEditor() {
        super(PermanentAddress.class);
    }

    @Override
    public EntityFormWindow<PermanentAddress> buildEntityWindow() {
    	EntityFormWindow<PermanentAddress> _e = new EntityFormWindow<PermanentAddress>();
    	_e.addTab(addressEditorComponent, "main");
    	return _e;
    }
    @Override
    public void init() {
        super.init();
      
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

	

    

}
