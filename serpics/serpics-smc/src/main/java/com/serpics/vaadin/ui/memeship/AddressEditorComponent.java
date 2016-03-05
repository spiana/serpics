package com.serpics.vaadin.ui.memeship;

import com.serpics.membership.data.model.PermanentAddress;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.ComboBox;
import com.serpics.vaadin.ui.MasterForm;
import com.vaadin.addon.jpacontainer.EntityContainer;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.ui.Field;

@VaadinComponent("addressEditorComponent")
public class AddressEditorComponent extends MasterForm<PermanentAddress> {
    private static final long serialVersionUID = 1L;

    public AddressEditorComponent() {
        super(PermanentAddress.class);

    }

    @Override
    protected void buildContent() {
    	super.buildContent();
    	
    	
    	Field<?> country = fieldGroup.getField("country");
    	if (country != null){
	    	country.addValueChangeListener(new ValueChangeListener() {
				@Override
				public void valueChange(ValueChangeEvent event) {
				com.vaadin.ui.Field.ValueChangeEvent _event = (com.vaadin.ui.Field.ValueChangeEvent) event	;
				 ComboBox f= (ComboBox) ((com.vaadin.ui.Field.ValueChangeEvent) event).getSource();
					EntityItem item = (EntityItem) f.getContainerDataSource().getItem(_event.getProperty().getValue());
					
					ComboBox district = (ComboBox)fieldGroup.getField("district");
					if (district != null){
						((EntityContainer)district.getContainerDataSource()).removeAllContainerFilters();
							((EntityContainer)district.getContainerDataSource()).addContainerFilter(new Compare.Equal(
								"country" , item.getEntity()));
					}
				}
		});
    	}
    }
}