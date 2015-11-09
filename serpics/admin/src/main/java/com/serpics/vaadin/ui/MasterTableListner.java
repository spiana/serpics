/**
 * 
 */
package com.serpics.vaadin.ui;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.persistence.Transient;

import com.serpics.base.data.model.MultilingualString;
import com.serpics.vaadin.ui.component.MultilingualLikeFilter;
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
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

/**
 * 
 * 
 * @author christian
 * @param <T>
 *
 */
public class MasterTableListner extends FormLayout {

	private static final long serialVersionUID = -2736583181645447496L;

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
	public <T> void deleteButtonClickListener(final JPAContainer<T> container, final Table entityList,
			final Button delete) {

		delete.addClickListener(new Button.ClickListener() {			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				if (entityList.getValue() == null)
					return;
				MessageBox.showPlain(Icon.QUESTION, I18nUtils.getMessage("messagebox.delete.title", ""),
						I18nUtils.getMessage("messagebox.delete.text", ""), new MessageBoxListener() {
					@Override
					public void buttonClicked(final ButtonId buttonId) {
						if (buttonId.compareTo(ButtonId.YES) == 0) {
							if (!container.removeItem(entityList.getValue()))
								System.out.println("Errore !");
						}
					}
				}, ButtonId.NO, ButtonId.YES);
			}
		});
	}

	/**
	 * 
	 * @param container
	 * @param _search
	 */
	public <T> void searchButtonClickListener(final JPAContainer<T> container, final Button search,
			 final TextField field, final ComboBox filterType) {
		
		if ( filterType.getValue() != null) {
			search.addClickListener(new Button.ClickListener() {				
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					if (filterType.getValue() != null) {
						switch ((String) filterType.getValue()) {
						case "inizia con":
							container.addContainerFilter(
									new SimpleStringFilter(filterType.getValue(), field.getValue(), false, true));
							break;
						case "contiene":
							container.addContainerFilter(
									new SimpleStringFilter(filterType, field.getValue(), false, false));
							break;
						case "è uguale a":
							container.addContainerFilter(new Compare.Equal(filterType, field.getValue()));
							break;
						case "è diverso da":
							container.addContainerFilter(new Not(new Compare.Equal(filterType, field.getValue())));
							break;
						case "è maggiore di":
							container.addContainerFilter(new Compare.Greater(filterType, field.getValue()));
							break;
						case "è minore di":
							container.addContainerFilter(new Compare.Less(filterType, field.getValue()));
							break;
						case "è maggiore o uguale a":
							container.addContainerFilter(new Compare.GreaterOrEqual(filterType, field.getValue()));
							break;
						case "è minore o uguale a":
							container.addContainerFilter(new Compare.LessOrEqual(filterType, field.getValue()));
							break;
						default:
							break;
						}
					}
				}
			});
		}
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
				showNotificationMessage("Romove all filter from container!!");
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
		notification.setDescription("<br /><span><p>"+message+"<br /></p></span>");
		notification.setPosition(Position.BOTTOM_RIGHT);
		notification.setDelayMsec(6000);
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
						if (container.getType(entry).isAssignableFrom(MultilingualString.class)) {
							filter = new Or(new MultilingualLikeFilter(entry, locale.getLanguage(), filterField));
						} else {
							filter = new Or(new SimpleStringFilter(entry, filterField, true, false));
						}
						filters.add(filter);
					}
				}
				container.addContainerFilter(Filters.or(filters));

				if (event.getText().equals("")) {
					container.removeAllContainerFilters();
					showNotificationMessage("Roove all filter from container!!");
				}
			}
		});
	}

}