/**
 * 
 */
package com.serpics.vaadin.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serpics.vaadin.data.utils.PropertiesUtils;
import com.serpics.vaadin.jpacontainer.ServiceContainerFactory;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.metadata.MetadataFactory;
import com.vaadin.addon.jpacontainer.metadata.PropertyKind;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author christian
 * @param <T>
 *
 */
@SuppressWarnings("unused")
public class AdvanceSearchForm<T> extends MasterForm<T> {
	private static final long serialVersionUID = -2122206953286953439L;

	private static transient Logger LOG = LoggerFactory.getLogger(AdvanceSearchForm.class);

	private BeanFieldGroup<T> formFiledBinding;
	private transient PropertyList<T> propertyList;
	protected transient Class<T> entityClass;


	private MasterTableListner masterTableListner;
	private transient String[] searchProperties;
	

	
	public AdvanceSearchForm(Class<T> clazz) {
		super(clazz);
		this.entityClass = clazz;
		this.propertyList = new PropertyList<T>(MetadataFactory.getInstance().getEntityClassMetadata(entityClass));
		searchProperties = PropertiesUtils.get().getSearchProperty(entityClass.getSimpleName());
		if (searchProperties == null) {
			this.searchProperties = buildDisplayProperties(entityClass);
			
			final JPAContainer<T> container = makeJPAContainer(this.entityClass);
			try {
				setEntityItem(container.createEntityItem(entityClass.newInstance()));
				formFiledBinding.setItemDataSource(this.entityItem);
				
			} catch (InstantiationException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private JPAContainer<T> makeJPAContainer(Class<T> clazz) {		
		JPAContainer<T> container = ServiceContainerFactory.make(clazz);
		return container;
	}

	private String[] buildDisplayProperties(Class<?> referencedType) {
		List<String> properties = new ArrayList<String>();
		for (Object pid : propertyList.getAllAvailablePropertyNames()) {
			if (propertyList.getClassMetadata().getProperty(pid.toString()).getAnnotation(Id.class) == null)
				if (propertyList.getClassMetadata().getProperty(pid.toString()).getAnnotation(EmbeddedId.class) == null)
					if (propertyList.getPropertyKind(pid.toString()).equals(PropertyKind.SIMPLE))
						properties.add(pid.toString());
		}

		return properties.toArray(new String[] {});
	}

	@Override
	protected void buildContent() {
		formFiledBinding = new BeanFieldGroup<T>(entityClass);
	
		final Map<TextField, ComboBox> map = new HashMap<TextField, ComboBox>();
		for (String pid : searchProperties) {
			if (pid.contains("."))
				propertyList.addNestedProperty(pid);
			HorizontalLayout searchForm = new HorizontalLayout();
			searchForm.addStyleName("form-search");
			ComboBox filterProperty = new ComboBox("",
					Arrays.asList(new String[] { "iniza con", "contiene", "finisce con", "è uguale a", "è diverso da",
							"è maggiore di", "è maggiore o uguale a", "è minore o uguale a" }));
			filterProperty.setCaption("Choose type of filter");
			filterProperty.setInputPrompt("filter");
			Field<?> field = createField(pid, searchForm );
			field.setCaption("Property to filter: " + pid);
			field.setIcon(FontAwesome.SEARCH);
			field.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
			map.put((TextField) field, filterProperty);
			searchForm.addComponent(filterProperty);
			searchForm.addComponent(field);
			formFiledBinding.bind(field, pid);
			searchForm.setWidth("100%");
			addComponent(searchForm);

		}

		

		HorizontalLayout buttonPanel = new HorizontalLayout();
		buttonPanel.setSpacing(true);
		buttonPanel.setStyleName("buttonPanelOnModal");
		
		final Button search = new Button("search");
		search.addClickListener(new Button.ClickListener() {
		private static final long serialVersionUID = -2221265605196009394L;
			@Override
			public void buttonClick(ClickEvent event) {
				for (String property : searchProperties) {
					for (Entry<TextField, ComboBox> entry : map.entrySet())
						MasterTableListner.get().addClickListnerOnSearchButton((JPAContainer)entityItem.getContainer(), search, entry.getKey(),
								entry.getValue(), property);
				}
			}
		});
		
		final Button reset = new Button("reset");		
		reset.addClickListener(new Button.ClickListener() {			
			private static final long serialVersionUID = 4768573772242735353L;

			@Override
			public void buttonClick(ClickEvent event) {
				MasterTableListner.get().resetButtonClickListener((JPAContainer)entityItem.getContainer(), reset);
				MasterTableListner.get().showNotificationMessage("Remove all filter from the container!");
			}
		});
		buttonPanel.addComponent(search);
		buttonPanel.addComponent(reset);
		addComponent(buttonPanel);
		}
		
}
