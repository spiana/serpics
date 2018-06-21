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

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serpics.i18n.Multilingual;
import com.serpics.vaadin.data.utils.I18nUtils;
import com.serpics.vaadin.ui.FilterComponentUtils;
import com.serpics.vaadin.ui.FilterComponentUtils.FilteringMode;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.metadata.PropertyKind;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.v7.data.Container.Filter;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.event.FieldEvents.TextChangeEvent;
import com.vaadin.v7.event.FieldEvents.TextChangeListener;
import com.vaadin.v7.shared.ui.datefield.Resolution;
import com.vaadin.v7.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.v7.ui.DateField;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.TextField;


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
	EntityItem<T> entityItem;

	protected transient JPAContainer<T> container;

	public FilterComponent(String propertyId, JPAContainer<T> container) {
		this.propertyId = propertyId;
		this.container = container;
		try {
			this.entityItem = container.createEntityItem(container.getEntityClass().newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public void buildContent() {

		LOG.info("entry buildContent");

		main = new HorizontalLayout();
		setCompositionRoot(main);

		main.setMargin(new MarginInfo(false, true, false, true));

		menuBar = new MenuBar();
		menuBar.setCaption(printLabelLang(propertyId));
		main.addComponentAsFirst(this.menuBar);
		if (this.container.getPropertyKind(propertyId).equals(PropertyKind.SIMPLE)
				|| this.container.getPropertyKind(propertyId).equals(PropertyKind.ONE_TO_ONE)) {

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
	}

	protected void menuEnumeration() {
		final Field<?> searchField = CustomFieldFactory.get().createField(entityItem, propertyId, this);
		searchField.addValueChangeListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				container.removeContainerFilter(currentFilter);
				if (event.getProperty().getValue() != null) {
					currentFilter = FilterComponentUtils.get().addFilter(container.getType(propertyId), propertyId,
							filteringMode, event.getProperty().getValue(), null);
					container.addContainerFilter(currentFilter);
				}

			}
		});

		searchField.setCaption("");

		final MenuItem item = menuBar.addItem("", null);
		item.addItem(printFilterLabelLang("isempty"), new MenuBar.Command()  {
			@Override
			public void menuSelected(MenuItem selectedItem) {
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.ISEMPTY;
				currentFilter = FilterComponentUtils.get().addFilter(container.getType(propertyId), propertyId,
						filteringMode, searchField.getValue(), null);
				container.addContainerFilter(currentFilter);
			}
		});

		item.addItem(printFilterLabelLang("isnotempty"), new MenuBar.Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.ISNOTEMPTY;
				currentFilter = FilterComponentUtils.get().addFilter(container.getType(propertyId), propertyId,
						filteringMode, searchField.getValue(), null);
				container.addContainerFilter(currentFilter);
			}
		});

		item.addItem(printFilterLabelLang("equals"), new MenuBar.Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.EQUALS;
				selectedItem.getParent().setText(selectedItem.getText());
				if (searchField.getValue() != null) {
					currentFilter = FilterComponentUtils.get().addFilter(container.getType(propertyId), propertyId,
							filteringMode, searchField.getValue(), null);
					container.addContainerFilter(currentFilter);
				}
			}
		});

		main.addComponent(searchField);

	}

	protected void menuStringItem() {

		final TextField searchText = new TextField();

		searchText.addTextChangeListener(new TextChangeListener() {

			@Override
			public void textChange(TextChangeEvent event) {
				container.removeContainerFilter(currentFilter);
				if (StringUtils.isNoneEmpty(event.getText()) &&
						(filteringMode.equals(FilteringMode.EQUALS) ||
								filteringMode.equals(FilteringMode.STARTSWITH)	||
								filteringMode.equals(FilteringMode.ENDWITH) ||
								filteringMode.equals(FilteringMode.CONTAINS)
								)) {
					currentFilter = FilterComponentUtils.get().addFilter(container.getType(propertyId), propertyId,
							filteringMode, event.getText(), null);
					container.addContainerFilter(currentFilter);
				}

			}
		});
		searchText.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				container.removeContainerFilter(currentFilter);
				if (event.getProperty().getValue() != null) {
					currentFilter = FilterComponentUtils.get().addFilter(container.getType(propertyId), propertyId,
							filteringMode, searchText.getValue(), null);
					container.addContainerFilter(currentFilter);
				}
			}
		});

		final MenuItem item = menuBar.addItem("", null);

		item.addItem(printFilterLabelLang("isempty"), new MenuBar.Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.ISEMPTY;
				selectedItem.getParent().setText(selectedItem.getText());
				currentFilter = FilterComponentUtils.get().addFilter(container.getType(propertyId), propertyId,
						filteringMode, searchText.getValue(), null);
				container.addContainerFilter(currentFilter);
			}
		});

		item.addItem(printFilterLabelLang("isnotempty"), new MenuBar.Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.ISNOTEMPTY;
				selectedItem.getParent().setText(selectedItem.getText());
				currentFilter = FilterComponentUtils.get().addFilter(container.getType(propertyId), propertyId,
						filteringMode, searchText.getValue(), null);
				container.addContainerFilter(currentFilter);
			}
		});
		item.addItem(printFilterLabelLang("equals"), new MenuBar.Command(){

			@Override
			public void menuSelected(MenuItem selectedItem) {
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.EQUALS;
				selectedItem.getParent().setText(selectedItem.getText());
				if (StringUtils.isNotEmpty(searchText.getValue())) {
					currentFilter = FilterComponentUtils.get().addFilter(container.getType(propertyId), propertyId,
							filteringMode, searchText.getValue(), null);
					container.addContainerFilter(currentFilter);
				}

			}
		});

		item.addItem(printFilterLabelLang("startwith"), new MenuBar.Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.STARTSWITH;
				selectedItem.getParent().setText(selectedItem.getText());
				if (StringUtils.isNotEmpty(searchText.getValue())) {
					currentFilter = FilterComponentUtils.get().addFilter(container.getType(propertyId), propertyId,
							filteringMode, searchText.getValue(), null);
					container.addContainerFilter(currentFilter);
				}

			}
		});

		item.addItem(printFilterLabelLang("endwith"), new MenuBar.Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.ENDWITH;
				selectedItem.getParent().setText(selectedItem.getText());
				if (StringUtils.isNotEmpty(searchText.getValue())) {
					currentFilter = FilterComponentUtils.get().addFilter(container.getType(propertyId), propertyId,
							filteringMode, searchText.getValue(), null);
					container.addContainerFilter(currentFilter);
				}
			}
		});

		item.addItem(printFilterLabelLang("like"), new MenuBar.Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.CONTAINS;
				selectedItem.getParent().setText(selectedItem.getText());
				if (StringUtils.isNotEmpty(searchText.getValue())) {
					currentFilter = FilterComponentUtils.get().addFilter(container.getType(propertyId), propertyId,
							filteringMode, searchText.getValue(), null);
					container.addContainerFilter(currentFilter);
				}
			}
		});

		String id = getFilterComponentName();

		searchText.setCaption("");
		searchText.setTextChangeTimeout(200);
		searchText.setTextChangeEventMode(TextChangeEventMode.LAZY);
		searchText.setVisible(true);
		searchText.setValue("");
		searchText.setImmediate(true);

		this.main.addComponent(searchText);
	}

	private void menuDateItem() {

		final DateField dataStart = new DateField();
		final DateField dataEnd = new DateField();

		dataStart.setResolution(Resolution.SECOND);
		dataEnd.setResolution(Resolution.SECOND);
		;

		final MenuItem item = menuBar.addItem("", null);

		item.addItem(printFilterLabelLang("isempty"), new MenuBar.Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				selectedItem.getParent().setText(selectedItem.getText());
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.ISEMPTY;
				selectedItem.getParent().setText(selectedItem.getText());
				dataEnd.setVisible(false);
				if (dataStart.getValue() != null) {
					currentFilter = FilterComponentUtils.get().addFilter(container.getType(propertyId), propertyId,
							filteringMode, dataStart.getValue(), null);
					container.addContainerFilter(currentFilter);
				}
			}
		});

		item.addItem(printFilterLabelLang("isnotempty"), new MenuBar.Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				selectedItem.getParent().setText(selectedItem.getText());
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.ISNOTEMPTY;
				selectedItem.getParent().setText(selectedItem.getText());
				dataEnd.setVisible(false);
				if (dataStart.getValue() != null) {
					currentFilter = FilterComponentUtils.get().addFilter(container.getType(propertyId), propertyId,
							filteringMode, dataStart.getValue(), null);
					container.addContainerFilter(currentFilter);
				}
			}
		});

		item.addItem(printFilterLabelLang("before"), new MenuBar.Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				selectedItem.getParent().setText(selectedItem.getText());
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.LESSOREQUAL;
				selectedItem.getParent().setText(selectedItem.getText());
				dataEnd.setVisible(false);
				if (dataStart.getValue() != null) {
					currentFilter = FilterComponentUtils.get().addFilter(container.getType(propertyId), propertyId,
							filteringMode, dataStart.getValue(), null);
					container.addContainerFilter(currentFilter);
				}
			}
		});
		item.addItem(printFilterLabelLang("after"), new MenuBar.Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				selectedItem.getParent().setText(selectedItem.getText());
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.GREATEROREQUAL;
				dataEnd.setVisible(false);
				selectedItem.getParent().setText(selectedItem.getText());
				if (dataStart.getValue() != null) {
					currentFilter = FilterComponentUtils.get().addFilter(container.getType(propertyId), propertyId,
							filteringMode, dataStart.getValue(), null);
					container.addContainerFilter(currentFilter);
				}

			}
		});
		item.addItem(printFilterLabelLang("between"), new MenuBar.Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				selectedItem.getParent().setText(selectedItem.getText());
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.BETWEEN;
				dataEnd.setVisible(true);
				selectedItem.getParent().setText(selectedItem.getText());
				if (dataStart.getValue() != null && dataEnd.getValue() != null) {
					currentFilter = FilterComponentUtils.get().addFilter(container.getType(propertyId), propertyId,
							filteringMode, dataStart.getValue(), dataEnd.getValue());
					container.addContainerFilter(currentFilter);
				}
			}
		});

		Date d = new Date();
		d = DateUtils.truncate(d, Calendar.DAY_OF_MONTH);

		dataStart.setCaption("");
		dataStart.setValue(d);
		dataStart.setVisible(true);
		main.addComponent(dataStart);

		d = DateUtils.addDays(d, 7);
		dataEnd.setValue(d);
		dataEnd.setCaption("");
		dataEnd.setVisible(false);
		main.addComponent(dataEnd);

		dataStart.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				container.removeContainerFilter(currentFilter);
				if (event.getProperty().getValue() != null) {
					currentFilter = FilterComponentUtils.get().addFilter(container.getType(propertyId), propertyId,
							filteringMode, dataStart.getValue(), dataEnd.getValue());
					container.addContainerFilter(currentFilter);
				}
			}
		});

		dataEnd.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				container.removeContainerFilter(currentFilter);
				if (event.getProperty().getValue() != null) {
					currentFilter = FilterComponentUtils.get().addFilter(container.getType(propertyId), propertyId,
							filteringMode, dataStart.getValue(), dataEnd.getValue());
					container.addContainerFilter(currentFilter);
				}
			}
		});

	}

	private void menuNumericItem() {

		final TextField textField = new TextField();
		textField.setCaption("");

		final MenuItem item = menuBar.addItem("", null);

		item.addItem(printFilterLabelLang("isempty"), new MenuBar.Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				selectedItem.getParent().setText(selectedItem.getText());
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.ISEMPTY;
				selectedItem.getParent().setText(selectedItem.getText());
				if (StringUtils.isNotEmpty(textField.getValue())) {
					currentFilter = FilterComponentUtils.get().addFilter(container.getType(propertyId), propertyId,
							filteringMode, textField.getConvertedValue(), null);
					container.addContainerFilter(currentFilter);
				}
			}
		});
		item.addItem(printFilterLabelLang("isnotempty"), new MenuBar.Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				selectedItem.getParent().setText(selectedItem.getText());
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.ISNOTEMPTY;
				selectedItem.getParent().setText(selectedItem.getText());
				if (StringUtils.isNotEmpty(textField.getValue())) {
					currentFilter = FilterComponentUtils.get().addFilter(container.getType(propertyId), propertyId,
							filteringMode, textField.getConvertedValue(), null);
					container.addContainerFilter(currentFilter);
				}
			}
		});

		item.addItem(printFilterLabelLang("lessequal"), new MenuBar.Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				selectedItem.getParent().setText(selectedItem.getText());
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.LESSOREQUAL;
				selectedItem.getParent().setText(selectedItem.getText());
				if (StringUtils.isNotEmpty(textField.getValue())) {
					currentFilter = FilterComponentUtils.get().addFilter(container.getType(propertyId), propertyId,
							filteringMode, textField.getConvertedValue(), null);
					container.addContainerFilter(currentFilter);
				}
			}
		});
		item.addItem(printFilterLabelLang("greaterequal"), new MenuBar.Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				selectedItem.getParent().setText(selectedItem.getText());
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.GREATEROREQUAL;
				selectedItem.getParent().setText(selectedItem.getText());
				if (StringUtils.isNotEmpty(textField.getValue())) {
					currentFilter = FilterComponentUtils.get().addFilter(container.getType(propertyId), propertyId,
							filteringMode, textField.getConvertedValue(), null);
					container.addContainerFilter(currentFilter);
				}
			}
		});

		textField.setCaption(this.propertyId);
		textField.setVisible(true);
		textField.setValue("0");
		textField.setConverter(Integer.class);
		textField.setTextChangeTimeout(200);
		textField.setTextChangeEventMode(TextChangeEventMode.LAZY);

		main.addComponent(textField);

		textField.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				LOG.info("text change" + event.getProperty().getValue() + "  " + event.getClass().toString());
				container.removeContainerFilter(currentFilter);
				if (!event.getProperty().getValue().equals("")) {
					currentFilter = FilterComponentUtils.get().addFilter(container.getType(propertyId), propertyId,
							filteringMode, ((TextField) event.getProperty()).getConvertedValue(), null);
					container.addContainerFilter(currentFilter);
				}
			}
		});
	}

	private void menuBooleanItem() {
		final MenuItem item = menuBar.addItem("", null);
		item.addItem(printFilterLabelLang("true"), new MenuBar.Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				selectedItem.getParent().setText(selectedItem.getText());
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.EQUALS;
				selectedItem.getParent().setText(selectedItem.getText());
				currentFilter = FilterComponentUtils.get().addFilter(container.getType(propertyId), propertyId,
						filteringMode, true, null);
				container.addContainerFilter(currentFilter);
			}
		});
		item.addItem(printFilterLabelLang("false"), new MenuBar.Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				selectedItem.getParent().setText(selectedItem.getText());
				container.removeContainerFilter(currentFilter);
				filteringMode = FilteringMode.EQUALS;
				selectedItem.getParent().setText(selectedItem.getText());
				currentFilter = FilterComponentUtils.get().addFilter(container.getType(propertyId), propertyId,
						filteringMode, false, null);
				container.addContainerFilter(currentFilter);
			}
		});
	}

	public void removeCurrentFilter() {
		if (currentFilter != null)
			container.removeContainerFilter(currentFilter);
	}

	public void removeContent(String id, HorizontalLayout filterPanel) {
		LOG.info("removeContent - id main" + this.main.getId() + " id menubar " + this.menuBar.getId() + " id attule "
				+ id);
		container.removeContainerFilter(currentFilter);
		filterPanel.removeComponent(this);
	}

	private String printLabelLang(String name) {
		return I18nUtils.getMessage(container.getEntityClass().getSimpleName().toLowerCase() + "." + name, name);
	}

	private String printFilterLabelLang(String name) {
		return I18nUtils.getMessage("filter." + name, name);
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