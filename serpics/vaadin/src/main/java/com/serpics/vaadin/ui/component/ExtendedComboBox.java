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


import java.lang.reflect.Modifier;

import com.serpics.vaadin.data.utils.I18nUtils;
import com.serpics.vaadin.data.utils.PropertiesUtils;
import com.serpics.vaadin.data.utils.PropertiesUtils.SmcPropertyDef;
import com.serpics.vaadin.jpacontainer.ServiceContainerFactory;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterForm;
import com.vaadin.addon.jpacontainer.EntityContainer;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

/**
 * @author spiana
 *
 */
public class ExtendedComboBox<T> extends CustomField<T> implements com.vaadin.data.Container.Viewer {
	private static final long serialVersionUID = 1L;
	
	private EntityItem<? extends T> item;
	private Object propertyId;
	
	private ComboBox combo;
	
	private Class<? extends T> mappedClass;
	
	private JPAContainer referencedContainer;
	 
	public ExtendedComboBox(EntityItem item , Object propertyId) {
		super();
		this.item = item;
		this.propertyId = propertyId;
		this.setCaption(propertyId.toString());
		
	
		this.combo = new ComboBox();
		
		 
     	SmcPropertyDef def = PropertiesUtils.get().getPropertyForEntity(item.getEntity().getClass(), propertyId.toString());
     	if (def != null){
     		mappedClass =  (Class<? extends T>)def.getMappedClass();
     	}
     	
    	 referencedContainer =ServiceContainerFactory.make(getMappedClass());
    	 
    	 String referencedPropertyId = buildReferencedPropertyToShow(item.getContainer(), propertyId);
    	
    	 if (referencedPropertyId.contains("."))
    		 referencedContainer.addNestedContainerProperty(referencedPropertyId);
    	 
         combo.setContainerDataSource(referencedContainer);
         combo.setItemCaptionMode(ItemCaptionMode.PROPERTY);
         combo.setItemCaptionPropertyId(referencedPropertyId);
         combo.setFilteringMode(FilteringMode.CONTAINS);
         combo.setImmediate(true);
         combo.setBuffered(true);
         combo.setConverter(new SingleSelectConverter(combo));
         combo.addValueChangeListener(new ValueChangeListener() {
         
			@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				if (combo.getValue() != null)
					setValue((T) referencedContainer.getItem(combo.getValue()).getEntity());
			}
		});
         
        
        
   }
	
	
	private String buildReferencedPropertyToShow( EntityContainer containerForProperty , Object propertyId){
		Class<?> referencedType=  containerForProperty.getType(propertyId);
		String referencedProperty = PropertiesUtils.get().getSelectProperty(referencedType.getSimpleName());
		
		if (referencedProperty == null)
			referencedProperty = "code"; // this is default
		
		return referencedProperty;
	}

	/* (non-Javadoc)
	 * @see com.vaadin.ui.CustomField#initContent()
	 */
	@Override
	protected Component initContent() {
		HorizontalLayout hlayout = new HorizontalLayout();
		hlayout.setSizeFull();
		hlayout.setMargin(false);

		combo.setWidth("100%");
		hlayout.addComponent(combo);
		
		
		MenuBar editMenu = new MenuBar();
		MenuItem items = editMenu.addItem("", null);
		
		items.addItem("Add", new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				add();
			}
		});

		items.addItem("Modify", new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				modify();
			}
		});
	
		
		editMenu.addStyleName("borderless");
		
		hlayout.addComponent(editMenu);

		hlayout.setExpandRatio(combo, 0.95F);
		hlayout.setExpandRatio(editMenu, 0.5F);
		
		return hlayout;
		
	}

	protected void add(){
		
		try {
			
			if (!Modifier.isAbstract(getMappedClass().getModifiers())){
				
				EntityItem<? extends T> item = referencedContainer.createEntityItem(getMappedClass().newInstance());
				edit(item, true);
			}
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				 
	}
	protected void modify(){
		Object currentValue  = combo.getValue();
		if (currentValue != null){
			EntityItem<? extends T> item = referencedContainer.getItem(currentValue);
			edit(item, false);
		}
	}
	
	protected void edit(EntityItem<? extends T> item , boolean isnew){
		final EntityFormWindow<? extends T> createForm  = buildEntityWindow();
		createForm.setNewItem(true);
		createForm.setReadOnly(false);
		
		createForm.setEntityItem(item);
		getUI().addWindow(createForm);
		createForm.addCloseListener(new CloseListener() {
			
			@Override
			public void windowClose(CloseEvent e) {
				
				if (createForm.getEntityItem().isPersistent())
					setValue((T)createForm.getEntityItem().getEntity());
				combo.markAsDirty();
				combo.setValue(referencedContainer.lastItemId());
				combo.setContainerDataSource(referencedContainer);	
				}
		});
	}
	
	private EntityFormWindow<? extends T> buildEntityWindow (){
		EntityFormWindow<? extends T> editorWindow =  (EntityFormWindow<? extends T> ) PropertiesUtils.get().getEditBean(getMappedClass().getSimpleName());
		if (editorWindow == null){
			editorWindow = new EntityFormWindow<T>();
			editorWindow.addTab(new MasterForm(getMappedClass()){}, "main");
		}
		editorWindow.setCaption(I18nUtils.getMessage(getType().getSimpleName() , getType().getSimpleName()));
		return editorWindow;
	}
	
	/* (non-Javadoc)
	 * @see com.vaadin.ui.AbstractField#getType()
	 */
	@Override
	public Class<T> getType() {
		return item.getItemProperty(propertyId).getType();
	}
	
	/**
	 * @return the mappedClass
	 */
	public Class<? extends T> getMappedClass() {
		return mappedClass != null ? mappedClass : getType();
	}
	/* (non-Javadoc)
	 * @see com.vaadin.ui.AbstractField#setPropertyDataSource(com.vaadin.data.Property)
	 */
	@Override
	public void setPropertyDataSource(Property newDataSource) {
		super.setPropertyDataSource(newDataSource);
		combo.setPropertyDataSource(newDataSource);
	}

	@Override
	public Property getPropertyDataSource() {
		return combo.getPropertyDataSource();
	}


	@Override
	public void setContainerDataSource(Container newDataSource) {
		combo.setContainerDataSource(newDataSource);
		
	}


	@Override
	public Container getContainerDataSource() {
		return combo.getContainerDataSource();
	}
		
}
