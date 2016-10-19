/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.vaadin.ui.component;



import com.serpics.base.data.model.Locale;
import com.serpics.base.data.model.MultilingualText;
import com.serpics.vaadin.jpacontainer.ServiceContainerFactory;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.data.Property;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.UI;

public class MultilingualRichTextField extends CustomField<MultilingualText> {
    private static final long serialVersionUID= -8222498672841576094L;
    
    RichTextArea textField ;
    
    Property<MultilingualText> property;
    
    public MultilingualRichTextField() {
        super();
        this.textField = new RichTextArea();
        setBuffered(true);
       
    }

    /* (non-Javadoc)
	 * @see com.vaadin.ui.CustomField#initContent()
	 */
	@Override
	protected Component initContent() {
		
		HorizontalLayout h = new HorizontalLayout();
		ComboBox combo = new ComboBox();
		final JPAContainer<Locale> locales =  ServiceContainerFactory.make(Locale.class);
		
	
		 combo.setContainerDataSource(locales);
         combo.setItemCaptionMode(ItemCaptionMode.PROPERTY);
         combo.setItemCaptionPropertyId("language");
         combo.setFilteringMode(FilteringMode.CONTAINS);
         combo.setImmediate(true);
         combo.setConverter(new SingleSelectConverter(combo));
         
         locales.addContainerFilter(new Compare.Equal("language", UI.getCurrent().getLocale().getLanguage()));
         combo.setValue(locales.firstItemId());
         locales.removeAllContainerFilters();
         
         combo.setNullSelectionAllowed(false);
     
         
		combo.addValueChangeListener(new ValueChangeListener() {
				@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
					EntityItem<Locale> locale = locales.getItem(event.getProperty().getValue());
					if (locale != null){
						MultilingualText m = property.getValue();
						m.addText(getLocale().getLanguage(), textField.getValue());
						setLocale(new java.util.Locale(locale.getEntity().getLanguage()));
						textField.setValue(m.getText(getLocale()));
						
					}
			}
		});
         
		h.addComponent(combo);
		h.addComponent(textField);
		
		combo.setWidth("100%");
		textField.setWidth("100%");
		textField.setBuffered(true);
		textField.setNullRepresentation("");
		
		textField.addValueChangeListener(new ValueChangeListener() {
				@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
					getState().modified= true;
				
			}
		});
		
		h.setWidth("100%");
		
		h.setExpandRatio(combo, 0.08F);
		h.setExpandRatio(textField, 0.90F);
		return h;
	}

	/* (non-Javadoc)
	 * @see com.vaadin.ui.AbstractField#getType()
	 */
	@Override
	public Class<MultilingualText> getType() {
		return MultilingualText.class;
	}
   
	/* (non-Javadoc)
	 * @see com.vaadin.ui.AbstractField#setPropertyDataSource(com.vaadin.data.Property)
	 */
	@Override
	public void setPropertyDataSource(Property newDataSource) {
		super.setPropertyDataSource(newDataSource);
		property = newDataSource;
		if (property != null){
			
			if (property.getValue() == null){
				MultilingualText m = new MultilingualText();
				property.setValue(m);
			}
				
		textField.setValue(property.getValue().getText(UI.getCurrent().getLocale()));
		}
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.vaadin.ui.AbstractField#commit()
	 */
	@Override
	public void commit() throws SourceException, InvalidValueException {
		MultilingualText m = property.getValue();
		m.addText(getLocale().getLanguage(), textField.getValue());
		getState(true).modified = false;
	}

}
