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
/**
 * 
 */
package com.serpics.vaadin.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serpics.i18n.data.model.MultilingualString;
import com.serpics.vaadin.ui.component.MultilingualTextField;
import com.serpics.vaadin.ui.filter.MultilingualLikeFilter;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.filter.Filters;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Not;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

/**
 * 
 * 
 * @author christian
 * @param <T>
 *
 */
public class MasterTableListner extends FormLayout implements Serializable {

	private static final long serialVersionUID = -2736583181645447496L;
	private static transient Logger LOG = LoggerFactory.getLogger(MasterTableListner.class);

	@Transient
	private transient String[] searchProperties;

	private static MasterTableListner instance;

	public MasterTableListner() {
		super();
	}

	public static MasterTableListner get() {
		if (instance == null)
			instance = new MasterTableListner();
		return instance;
	}

	
	/**
	 * 
	 * @param container
	 * @param _search
	 */
	@SuppressWarnings("unused")
	public <T> void addClickListnerOnSearchButton(final JPAContainer<T> container, final Button search,
			final TextField field, final ComboBox filterType, final String property) {

		List<Filter> filters = new ArrayList<Filter>();
		Filter filter = null;

		if (filterType.getValue() != null) {
			switch ((String) filterType.getValue()) {
			case "inizia con":
				filter = new SimpleStringFilter(property, field.getValue(), false, true);
				break;
			case "contiene":
				filter = new SimpleStringFilter(property, field.getValue(), false, false);
				break;
			case "è uguale a":
				filter = new Compare.Equal(property, field.getValue());
				break;
			case "è diverso da":
				filter = new Not(new Compare.Equal(property, field.getValue()));
				break;
			case "è maggiore di":
				filter = new Compare.Greater(property, field.getValue());
				break;
			case "è minore di":
				filter = new Compare.Less(property, field.getValue());
				break;
			case "è maggiore o uguale a":
				filter = new Compare.GreaterOrEqual(property, field.getValue());
				break;
			case "è minore o uguale a":
				filter = new Compare.LessOrEqual(property, field.getValue());
				break;
			default:
				break;				
			}
			filters.add(filter);
			showNotificationMessage("Add filter on property: " + property);
		}
		container.addContainerFilter(Filters.or(filters));
	}

	/**
	 * 
	 * @param container
	 * @param _search
	 */
	public <T> void resetButtonClickListener(final JPAContainer<T> container, final Button reset) {
		reset.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {				
				container.removeAllContainerFilters();
			}
		});
	}

	/**
	 * 
	 * @return
	 */
	public Component buildFilterField() {
		final TextField filter = new TextField();
		filter.setInputPrompt("Filter");
		filter.setIcon(FontAwesome.SEARCH);
		filter.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
		return filter;
	}

	/**
	 * 
	 * @return
	 */
	public Component createComboFilterType() {
		ComboBox filterType = new ComboBox("", Arrays.asList(new String[] { "inizia con", "contiene", "è uguale a",
				"è diverso da", "è maggiore di", "è minore di", "è maggiore o uguale a", "è minore e uguale a" }));
		return filterType;
	}

	/**
	 * 
	 * @return
	 */
	public Component buildComboByMXL(String[] properties) {
		final ComboBox propertiesId = new ComboBox();
		if (properties != null) {
			for (String entry : properties) {
				propertiesId.addItem((String) entry);
			}
		}
		return propertiesId;
	}

	/**
	 * 
	 * @param message
	 */
	public void showNotificationMessage(String message) {
		Notification notification = new Notification("Commerce PlaTform Notification");
		notification.setHtmlContentAllowed(true);
		notification.setDescription("<br /><span><p>" + message + "<br /></p></span>");
		notification.setPosition(Position.BOTTOM_RIGHT);
		notification.setDelayMsec(6000);
		notification.setStyleName("commerce-notification");
		notification.show(Page.getCurrent());
	}

	/**
	 * 
	 * @param container
	 * @param property
	 * @param field
	 * @param properties
	 */
	public <T> void filterAllContainerJPA(final JPAContainer<T> container, final TextField filter,
			final String[] properties) {

		filter.addTextChangeListener(new TextChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void textChange(final TextChangeEvent event) {

				String filterField = "%" + event.getText() + "%";
				List<Filter> filters = new ArrayList<Filter>();

				Locale locale = UI.getCurrent().getSession().getLocale();
				Filter filter = null;
				container.removeAllContainerFilters();

				if (properties != null) {
					for (String entry : properties) {
						if (MultilingualString.class.isAssignableFrom(container.getType(entry))) {
							filter = new Or(new MultilingualLikeFilter(entry, locale.getLanguage(), filterField));
						}else if (MultilingualTextField.class.isAssignableFrom(container.getType(entry))) {
							LOG.warn("MultilingualTextField is not yet supported in container filters !");
						} else {
							filter = new Or(new SimpleStringFilter(entry, filterField, true, false));
						}
						filters.add(filter);
					}
				}
				container.addContainerFilter(Filters.or(filters));

				if (event.getText().equals("")) {
					container.removeAllContainerFilters();
					showNotificationMessage("Remove all filter from container!!");
				}
			}
		});
	}

}