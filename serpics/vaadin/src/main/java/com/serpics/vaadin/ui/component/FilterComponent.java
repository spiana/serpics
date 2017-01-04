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

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serpics.base.Multilingual;
import com.serpics.vaadin.data.utils.I18nUtils;
import com.serpics.vaadin.ui.FilterComponentUtils;
import com.serpics.vaadin.ui.FilterComponentUtils.FilteringMode;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.metadata.PropertyKind;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

/**
 * @author spiana
 * @param <T>
 *
 */
public class FilterComponent<T> extends CustomComponent {

	private static transient Logger LOG = LoggerFactory.getLogger(FilterComponent.class);

	HorizontalLayout main;
	private String propertyId;	
	private MenuBar menuBar;
	private Filter currentFilter;
	private FilteringMode filteringMode = FilteringMode.OFF;
	
	protected transient JPAContainer<T> container;

	public FilterComponent(String propertyId) {
		this.propertyId = propertyId;
	}

	public void buildContent() {

		LOG.info("entry buildContent");
		
		main = new HorizontalLayout();
		setCompositionRoot(main);
		
		main.setMargin(new MarginInfo(false,true,false,true));
		
		menuBar = new MenuBar();
		menuBar.setCaption(printLabelLang(propertyId));
		main.addComponentAsFirst(this.menuBar);
		if (this.container.getPropertyKind(propertyId).equals(PropertyKind.SIMPLE)){
			
			if (String.class.isAssignableFrom(this.container.getType(this.propertyId)))
				menuStringItem();
			else if (Multilingual.class.isAssignableFrom(this.container.getType(propertyId)))
				menuStringItem();
			else if (Date.class.isAssignableFrom(this.container.getType(propertyId)))
				menuDateItem();
			else if (Number.class.isAssignableFrom(this.container.getType(propertyId)))
				menuNumericItem();
			else if (Boolean.class.isAssignableFrom(this.container.getType(propertyId)))
				menuBooleanItem();
			else if (Enum.class.isAssignableFrom(container.getType(propertyId)))
				menuEnumeration();
		}
	
		this.container.getPropertyKind(propertyId);


	}

	protected void menuEnumeration(){
		    try {
		    	EntityItem<T> entityItem =  container.createEntityItem(container.getEntityClass().newInstance());
		    	final Field<?> f  = CustomFieldFactory.get().createField(entityItem, propertyId, this);
		    	f.setCaption("");
		    	
		    	final MenuItem item = menuBar.addItem("", null); 
				item.addItem(printFilterLabelLang("isempty"), new Command() {
					
					@Override
					public void menuSelected(MenuItem selectedItem) {
						container.removeContainerFilter(currentFilter);
						filteringMode = FilteringMode.ISEMPTY;
						currentFilter = FilterComponentUtils.get().addFilter(container, propertyId, filteringMode, (AbstractField) f , null);
						
					}
				});

				item.addItem(printFilterLabelLang("equals"), new Command() {
					
					@Override
					public void menuSelected(MenuItem selectedItem) {
						container.removeContainerFilter(currentFilter);
						filteringMode = FilteringMode.EQUALS;
						selectedItem.getParent().setText(selectedItem.getText());
						if (f.getValue() != null)
							currentFilter = FilterComponentUtils.get().addFilter(container, propertyId, filteringMode, (AbstractField) f  , null);
					}
				});
		    	
				
				main.addComponent(f);
				f.addValueChangeListener(new ValueChangeListener() {
					
					@Override
					public void valueChange(ValueChangeEvent event) {
						container.removeContainerFilter(currentFilter);	
						if (f.getValue() != null){
							currentFilter = FilterComponentUtils.get().addFilter(container.getType(propertyId), propertyId, filteringMode, (AbstractField) f, null);
							container.addContainerFilter(currentFilter);
						}
						
					}
				});
			} catch (InstantiationException | IllegalAccessException e) {
				
				e.printStackTrace();
			}
	         
	    }
	
	protected void menuStringItem() {
		
		final TextField searchString = new TextField();
		
		final MenuItem item = menuBar.addItem("", null); 
		item.addItem(printFilterLabelLang("isempty"), new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.ISEMPTY;
				selectedItem.getParent().setText(selectedItem.getText());
				if (!searchString.getValue().isEmpty())
					currentFilter = FilterComponentUtils.get().addFilter(container, propertyId, filteringMode, searchString , null);
				
			}
		});

		item.addItem(printFilterLabelLang("equals"), new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.EQUALS;
				selectedItem.getParent().setText(selectedItem.getText());
				if (!searchString.getValue().isEmpty())
					currentFilter = FilterComponentUtils.get().addFilter(container, propertyId, filteringMode, searchString , null);
				
			}
		});
		
		item.addItem(printFilterLabelLang("startwith"), new Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.STARTSWITH;
				selectedItem.getParent().setText(selectedItem.getText());
				if (StringUtils.isNotEmpty(searchString.getValue()))
					currentFilter = FilterComponentUtils.get().addFilter(container, propertyId, filteringMode, searchString , null);
			}
		});
		
		item.addItem(printFilterLabelLang("endwith"), new Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.ENDWITH;
				selectedItem.getParent().setText(selectedItem.getText());
				if (StringUtils.isNotEmpty(searchString.getValue()))
					currentFilter = FilterComponentUtils.get().addFilter(container, propertyId, filteringMode, searchString , null);
			}
		});
		
		item.addItem(printFilterLabelLang("like"), new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.CONTAINS;
				selectedItem.getParent().setText(selectedItem.getText());
				if (StringUtils.isNotEmpty(searchString.getValue()))
					currentFilter = FilterComponentUtils.get().addFilter(container, propertyId, filteringMode, searchString , null);
				
			}
		});
	
		String id = getFilterComponentName();
		
		searchString.setCaption("");
		searchString.setTextChangeTimeout(200);
		searchString.setTextChangeEventMode(TextChangeEventMode.LAZY);
		searchString.setVisible(true);
		searchString.setValue("");
		searchString.setImmediate(true);

		searchString.setId(id);
		
		searchString.addTextChangeListener(new TextChangeListener() {
			
			@Override
			public void textChange(TextChangeEvent event) {
				LOG.info("text change" + event.getText());
				container.removeContainerFilter(currentFilter);
				AbstractTextField c = (AbstractTextField)event.getComponent();
				c.setValue(event.getText());
				if (event.getText().equals("")) 
					showNotificationMessage("Remove all filter from container!!");
				else
					currentFilter = FilterComponentUtils.get().addFilter(container, propertyId, filteringMode, c, null); //CREA TIPOLOGIA FILTRIO
			}
		});
		
		this.main.addComponent(searchString);
	}



	private void menuDateItem() {
		
		final DateField dataStart = new DateField();
		final DateField dataEnd = new DateField();
		
		final MenuItem item = menuBar.addItem("", null); 
		
		item.addItem(printFilterLabelLang("isempty"), new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				selectedItem.getParent().setText(selectedItem.getText());
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.ISEMPTY;
				selectedItem.getParent().setText(selectedItem.getText());
				dataEnd.setVisible(false);
				if (dataStart.getValue() != null)
					currentFilter = FilterComponentUtils.get().addFilter(container, propertyId, filteringMode, dataStart , null);
			}
		});
		
		item.addItem(printFilterLabelLang("before"), new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				selectedItem.getParent().setText(selectedItem.getText());
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.LESSOREQUAL;
				selectedItem.getParent().setText(selectedItem.getText());
				dataEnd.setVisible(false);
				if (dataStart.getValue() != null)
					currentFilter = FilterComponentUtils.get().addFilter(container, propertyId, filteringMode, dataStart , null);
			}
		});
		item.addItem(printFilterLabelLang("after"), new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				selectedItem.getParent().setText(selectedItem.getText());
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.GREATEROREQUAL;
				dataEnd.setVisible(false);
				selectedItem.getParent().setText(selectedItem.getText());
				if (dataStart.getValue() != null)
					currentFilter = FilterComponentUtils.get().addFilter(container, propertyId, filteringMode, dataStart , null);
				
			}
		});
		item.addItem(printFilterLabelLang("between"), new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				selectedItem.getParent().setText(selectedItem.getText());
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.BETWEEN;
				dataEnd.setVisible(true);
				selectedItem.getParent().setText(selectedItem.getText());
				if (dataStart.getValue() != null && dataEnd.getValue() != null)
					currentFilter = FilterComponentUtils.get().addFilter(container, propertyId, filteringMode, dataStart , dataEnd);
				
			}
		});
		
		
		
		dataStart.setCaption("");
		dataStart.setValue(new Date());
		dataStart.setVisible(true);
		main.addComponent(dataStart);
		
		dataEnd.setValue(new Date());
		dataEnd.setCaption("");
		dataEnd.setVisible(false);
		main.addComponent(dataEnd);
	
		dataStart.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				container.removeContainerFilter(currentFilter);
				if (event.getProperty().getValue() == null) 
					showNotificationMessage("Remove all filter from container!!");
				else {
					currentFilter =FilterComponentUtils.get().addFilter(container.getType(propertyId), propertyId, filteringMode, dataStart, dataEnd); 
					container.addContainerFilter(currentFilter);
				}
			}
		});
		
		dataEnd.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				container.removeContainerFilter(currentFilter);
				if (event.getProperty().getValue().equals("")) 
					showNotificationMessage("Remove all filter from container!!");
				else {
					currentFilter = FilterComponentUtils.get().addFilter(container.getType(propertyId), propertyId, filteringMode, dataStart, dataEnd); 
					container.addContainerFilter(currentFilter);
				}
			}
		});
		
	}
	
	private void menuNumericItem() {
		
		final TextField textField = new TextField();
		textField.setCaption("");
		
		final MenuItem item = menuBar.addItem("", null); 
		
		item.addItem(printFilterLabelLang("isempty"), new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				selectedItem.getParent().setText(selectedItem.getText());
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.ISEMPTY;
				selectedItem.getParent().setText(selectedItem.getText());
				if (StringUtils.isNotEmpty(textField.getValue()))
					currentFilter = FilterComponentUtils.get().addFilter(container, propertyId, filteringMode, textField , null);
				
			}
		});
		
		item.addItem(printFilterLabelLang("lessequal"), new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				selectedItem.getParent().setText(selectedItem.getText());
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.LESSOREQUAL;
				selectedItem.getParent().setText(selectedItem.getText());
				if (StringUtils.isNotEmpty(textField.getValue()))
					currentFilter = FilterComponentUtils.get().addFilter(container, propertyId, filteringMode, textField , null);
				
			}
		});
		item.addItem(printFilterLabelLang("greaterequal"), new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				selectedItem.getParent().setText(selectedItem.getText());
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.GREATEROREQUAL;
				selectedItem.getParent().setText(selectedItem.getText());
				if (StringUtils.isNotEmpty(textField.getValue()))
					currentFilter = FilterComponentUtils.get().addFilter(container, propertyId, filteringMode, textField , null);
				
			}
		});
		
		
		
		textField.setCaption(this.propertyId);
		textField.setVisible(true);
		textField.setValue("0");
		textField.setConverter(Integer.class);
		main.addComponent(textField);
		
		textField.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				LOG.info("text change" + event.getProperty().getValue() + "  " +  event.getClass().toString());
				container.removeContainerFilter(currentFilter);
				if (event.getProperty().getValue().equals("")) 
					showNotificationMessage("Remove all filter from container!!");
				else 
					currentFilter =FilterComponentUtils.get().addFilter(container, propertyId, filteringMode, textField, null); 
			}
		});
	}
	
	
	private void menuBooleanItem() {
		final MenuItem item = menuBar.addItem("", null); 
		item.addItem(printFilterLabelLang("true"), new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				selectedItem.getParent().setText(selectedItem.getText());
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.EQUALS;
				selectedItem.getParent().setText(selectedItem.getText());
				CheckBox cb = new CheckBox();
				cb.setValue(true);
				cb.setVisible(false);
				currentFilter = FilterComponentUtils.get().addFilter(container, propertyId, filteringMode, cb , null);
				
			}
		});
		item.addItem(printFilterLabelLang("false"), new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
					selectedItem.getParent().setText(selectedItem.getText());
					container.removeContainerFilter(currentFilter);
					filteringMode = FilteringMode.EQUALS;
					selectedItem.getParent().setText(selectedItem.getText());
					CheckBox cb = new CheckBox();
					cb.setValue(false);
					cb.setVisible(false);
					currentFilter = FilterComponentUtils.get().addFilter(container, propertyId, filteringMode, cb , null);
			}
		});
	}
		
	
	
	public void removeCurrentFilter(){
		if (currentFilter != null)
			container.removeContainerFilter(currentFilter);
	}

	public void setContainer(JPAContainer<T> container) {
		this.container = container;
	}

	public void removeContent(String id, HorizontalLayout filterPanel) {
		LOG.info("removeContent - id main" + this.main.getId() + " id menubar " + this.menuBar.getId() + " id attule " + id);
		container.removeContainerFilter(currentFilter);
		filterPanel.removeComponent(this);
	}
	
	
	private String printLabelLang(String name){
		return I18nUtils.getMessage(container.getEntityClass().getSimpleName().toLowerCase() + "." + name,name);
	}
	private String printFilterLabelLang(String name){
		return I18nUtils.getMessage("filter." + name,name);
	}
	
	private String getFilterComponentName() {
		return getFilterComponentName("");
	}
	private String getFilterComponentName(String prex) {
		String id = prex + container.getEntityClass().getSimpleName() + "-" + getCaption();
		return id;
	}
	
	private void showNotificationMessage(String message) {
		Notification notification = new Notification("");
		notification.setHtmlContentAllowed(true);
		notification.setDescription("<br /><span><p>" + message + "<br /></p></span>");
		notification.setPosition(Position.BOTTOM_RIGHT);
		notification.setDelayMsec(6000);
		notification.setStyleName("commerce-notification");
		notification.show(Page.getCurrent());
	}
}