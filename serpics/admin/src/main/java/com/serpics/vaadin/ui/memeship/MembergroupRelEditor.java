package com.serpics.vaadin.ui.memeship;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.membership.Member2GroupRelType;
import com.serpics.membership.persistence.Membergroup;
import com.serpics.membership.persistence.Membergrouprel;
import com.serpics.membership.persistence.MembgrouprelPK;
import com.serpics.membership.services.MembergroupService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityForm;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.addon.jpacontainer.provider.ServiceContainerFactory;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;

@VaadinComponent("membergroupRelEditor")
public class MembergroupRelEditor extends EntityForm<Membergrouprel> {

    private static transient Logger logger = LoggerFactory.getLogger(MembergroupRelEditor.class);

    @Autowired
    private transient MembergroupService membergroupService;

    public MembergroupRelEditor() {
        super(Membergrouprel.class);

    }


    private transient JPAContainer<Membergroup> memberGroups;

    @Override
    public void attach() {
        memberGroups.refresh();
        super.attach();
    }

    @Override
    public void init() {
        super.init();
        final String[] displayProperties = { "membergroup", "status", "validFrom", "validTo" };
        memberGroups = ServiceContainerFactory.make(Membergroup.class,
                membergroupService);
        setDisplayProperties(displayProperties);
    }

    @Override
    public void save() throws CommitException {
        fieldGroup.commit();
        if (super.entityItem.getItemId() == null) {
            final Membergrouprel m = entityItem.getEntity();
            final MembgrouprelPK pk = new MembgrouprelPK();
            pk.setMembergroupsId(m.getMembergroup().getMembergroupsId());
            pk.setMemberId(m.getMember().getMemberId());
            m.setId(pk);
            entityItem.getContainer().addEntity(m);
        }
    }


    @Override
    protected Field<?> createField(final String pid) {
        if (pid.equals("membergroup")) {
            final ComboBox combo = new ComboBox("membergroup");
            combo.setContainerDataSource(memberGroups);
            combo.setItemCaptionMode(ItemCaptionMode.PROPERTY);
            combo.setItemCaptionPropertyId("name");
            combo.setFilteringMode(FilteringMode.CONTAINS);
            combo.setImmediate(true);
            combo.setConverter(new SingleSelectConverter(combo));
            fieldGroup.bind(combo, "membergroup");
            return combo;
        } else if (pid.equals("status")) {
            final ComboBox combo = new ComboBox("status");
            combo.addItem(Member2GroupRelType.EFFECTIVE);
            combo.setItemCaption(Member2GroupRelType.EFFECTIVE, "Effective");
            combo.addItem(Member2GroupRelType.REQUEST);
            combo.setItemCaption(Member2GroupRelType.REQUEST, "Request");
            combo.setNullSelectionAllowed(false);
            fieldGroup.bind(combo, "status");
            return combo;

        } else
            return super.createField(pid);
    }

}