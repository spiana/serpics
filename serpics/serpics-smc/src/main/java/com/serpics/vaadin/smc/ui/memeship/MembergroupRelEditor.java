package com.serpics.vaadin.smc.ui.memeship;

import com.serpics.membership.data.model.Membergrouprel;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.MasterForm;
import com.vaadin.ui.Field;

@VaadinComponent("memberGroupRelEditor")
public class MembergroupRelEditor extends MasterForm<Membergrouprel> {
	private static final long serialVersionUID = 7609356693138124682L;

	public MembergroupRelEditor() {
        super(Membergrouprel.class);

    }
    @Override
    public void init() {
        final String[] displayProperties = { "membergroup", "status", "validFrom", "validTo" };
        setDisplayProperties(displayProperties);
    }

    @Override
    protected Field<?> createField(final String pid) {
//        if (pid.equals("membergroup")) {
//            final ComboBox combo = new ComboBox("membergroup");
//            combo.setContainerDataSource(ServiceContainerFactory.make(Membergroup.class));
//            combo.setItemCaptionMode(ItemCaptionMode.PROPERTY);
//            combo.setItemCaptionPropertyId("name");
//            combo.setFilteringMode(FilteringMode.CONTAINS);
//            combo.setImmediate(true);
//            combo.setConverter(new SingleSelectConverter(combo));
//            fieldGroup.bind(combo, "membergroup");
//            return combo;
//        } else
            return super.createField(pid);
    }
    
}