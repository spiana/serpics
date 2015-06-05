package com.serpics.vaadin.ui.memeship;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.membership.data.model.Membergroup;
import com.serpics.membership.data.model.Membergrouprel;
import com.serpics.membership.data.model.MembgrouprelPK;
import com.serpics.membership.data.repositories.MemberGroupRepository;
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

    public MembergroupRelEditor() {
        super(Membergrouprel.class);

    }

    @Autowired
    private transient MemberGroupRepository memberGroupRepository;
    
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
        memberGroups = ServiceContainerFactory.make(Membergroup.class);
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
        } else
            return super.createField(pid);
    }

}