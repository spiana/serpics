package com.serpics.vaadin.ui.memeship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.serpics.core.service.EntityService;
import com.serpics.membership.persistence.Member;
import com.serpics.membership.persistence.PrimaryAddress;
import com.serpics.membership.services.PrimaryAddressService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityFormChild;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.data.util.filter.Compare;

@VaadinComponent("primaryAddressEditor")
public class PrimaryAddressEditor extends EntityFormChild<PrimaryAddress, Member> {
    private static final long serialVersionUID = 1L;

    @Autowired
    private transient PrimaryAddressService addressService;

    public PrimaryAddressEditor() {
        super(PrimaryAddress.class);

    }

    @SuppressWarnings("rawtypes")
    @Override
    public EntityService getService() {
        return addressService;
    }


    @Override
    public void init() {
        super.init();
        final String[] displayProperties = { "firstname", "lastname", "phone", "email", "company",
                "address1",
                "zipcode", "city", "region", "country" };

        setDisplayProperties(displayProperties);

    }

    @Override
    public void setParentEntity(final EntityItem<Member> parent) {
        Assert.notNull(parent);
        container.removeAllContainerFilters();
        container.addContainerFilter(new Compare.Equal("member", parent.getEntity()));
        EntityItem<PrimaryAddress> e = container.getItem(container.getIdByIndex(0));
        if (e == null)
            e = container.createEntityItem(new PrimaryAddress());
        e.getEntity().setMember(parent.getEntity());
        setEntityItem(e);
        super.setParentEntity(parent);
    }
}
