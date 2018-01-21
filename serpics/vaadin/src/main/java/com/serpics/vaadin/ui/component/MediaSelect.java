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


import java.io.File;

import com.serpics.mediasupport.MediaSupportType;
import com.serpics.mediasupport.data.model.MediaField;
import com.serpics.mediasupport.utils.MediaUtil;
import com.serpics.vaadin.data.utils.I18nUtils;
import com.serpics.vaadin.data.utils.PropertiesUtils;
import com.serpics.vaadin.jpacontainer.ServiceContainerFactory;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.vaadin.addon.jpacontainer.EntityContainer;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.data.Property;
import com.vaadin.event.MouseEvents;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

/**
 * @author spiana
 *
 */
public class MediaSelect<T extends MediaField> extends CustomField<T> {
	private static final long serialVersionUID = 1L;
	
	private EntityItem item;
	private Object propertyId;
	
	private ComboBox combo;
	private Image image;
	
	
	 private JPAContainer referencedContainer;
	 
	public MediaSelect(EntityItem item , Object propertyId) {
		super();
		this.item = item;
		this.propertyId = propertyId;
		this.setCaption(propertyId.toString());
		
	
		this.combo = new ComboBox();
    	 referencedContainer =ServiceContainerFactory.make(item.getContainer().getType(propertyId));
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
				setValue((T) referencedContainer.getItem(combo.getValue()).getEntity());
				loadResource();
			}
		});
         
         
         
       
         image = new Image();
        
   }
	
	
	private String buildReferencedPropertyToShow( EntityContainer containerForProperty , Object propertyId){
		Class<?> referencedType=  containerForProperty.getType(propertyId);
		String referencedProperty = PropertiesUtils.get().getSelectProperty(referencedType);
		
		if (referencedProperty == null)
			referencedProperty = "uuid"; // this is default
		
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
		
		VerticalLayout v = new VerticalLayout();
		v.setWidth("100%");
		v.setMargin(new MarginInfo(false,true,false,false));
		
		image.setWidth("100%");
		v.addComponent(image);
		
		combo.setWidth("100%");
		hlayout.addComponent(v);
		hlayout.addComponent(combo);
		
		
		
		image.addClickListener(new MouseEvents.ClickListener() {
			
			@Override
			public void click(ClickEvent event) {
				if  (!event.isDoubleClick())
					return;
				Window w = new Window();
				w.setModal(true);
				w.setContent(new Image("", image.getSource()));
				w.setClosable(true);
				w.setHeight("400px");
				w.setWidth("740px");
				w.setCaption("zoom");
				getUI().addWindow(w);
			}
		});

		Button newMedia = new Button(I18nUtils.getMessage("smc.mediaselect.add.media", "add"));
		newMedia.addStyleName("top");
		newMedia.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
				
				try {
					final EntityFormWindow<T> createForm  = new EntityFormWindow<T>();
					createForm.addTab(new MediaComponent<T>(item.getItemProperty(propertyId).getType()), "main");
					createForm.setNewItem(true);
					createForm.setReadOnly(false);
					createForm.setEntityItem(referencedContainer.createEntityItem(item.getItemProperty(propertyId).getType().newInstance()));
					getUI().addWindow(createForm);
					createForm.addCloseListener(new CloseListener() {
						
						@Override
						public void windowClose(CloseEvent e) {
							
							if (createForm.getEntityItem().isPersistent())
								setValue((T)createForm.getEntityItem().getEntity());
							
							
							combo.markAsDirty();
							combo.setValue(referencedContainer.lastItemId());
							combo.setContainerDataSource(referencedContainer);		}
					});
					
				} catch (InstantiationException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		hlayout.addComponent(newMedia);
		
		hlayout.setExpandRatio(combo, 0.75F);
		hlayout.setExpandRatio(v, 0.20F);
		hlayout.setExpandRatio(newMedia, 0.5F);
		
		return hlayout;
		
	}

	public Image getImage() {
		return image;
	}
	/* (non-Javadoc)
	 * @see com.vaadin.ui.AbstractField#getType()
	 */
	@Override
	public Class<T> getType() {
		return item.getItemProperty(propertyId).getType();
	}
	
	/* (non-Javadoc)
	 * @see com.vaadin.ui.AbstractField#setPropertyDataSource(com.vaadin.data.Property)
	 */
	@Override
	public void setPropertyDataSource(Property newDataSource) {
		super.setPropertyDataSource(newDataSource);
		combo.setPropertyDataSource(newDataSource);
	}
	
	private void loadResource(){
		MediaField media = getValue();
		if (media != null){
			if (media.getType().equals(MediaSupportType.REMOTE)){
				String source = media.getSource();
				if (source != null){
					ExternalResource r = new ExternalResource(media.getSource());
					this.image.setSource(r);
					this.image.markAsDirty();
				}
			}else{
				String source = media.getSource();
				if (source != null){
					String mediaFilePath = MediaUtil.getInstance().getMediaStorePathFromSource(source);
					FileResource resource = new FileResource(new File(mediaFilePath));
					this.image.setSource(resource);
					this.image.markAsDirty();
				}
			}
				
			
		}
		
		
	}
}
