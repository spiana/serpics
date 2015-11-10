/**
 * 
 */
package com.serpics.vaadin.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serpics.vaadin.ui.component.CustomFieldFactory;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.metadata.MetadataFactory;
import com.vaadin.addon.jpacontainer.metadata.PropertyKind;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author christian
 * @param <T>
 *
 */
public class AdvanceSearchForm<T> extends MasterForm<T> {

	private static transient Logger LOG = LoggerFactory.getLogger(AdvanceSearchForm.class);

	private static final long serialVersionUID = 87868509261251877L;
	private BeanFieldGroup<T> formFiledBinding;
	private transient PropertyList<T> propertyList;
	protected transient Class<T> entityClass;
	protected transient EntityItem<T> item;

	private transient String[] searchProperties;

	public AdvanceSearchForm(Class<T> clazz, EntityItem<T> item) {
		super(clazz);
		this.entityClass = clazz;
		this.item = item;
		this.propertyList = new PropertyList<T>(MetadataFactory.getInstance().getEntityClassMetadata(entityClass));
		searchProperties = PropertiesUtils.get().getSearchProperty(entityClass.getSimpleName());
		if (searchProperties == null) {
			this.searchProperties = buildDisplayProperties(entityClass);
			buildContent();
		}
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
		formFiledBinding.setItemDataSource(createEmptyInstance());
		for (String pid : searchProperties) {
			if (pid.contains("."))
				propertyList.addNestedProperty(pid);
			HorizontalLayout h = new HorizontalLayout();			
			ComboBox filterProperty = new ComboBox("", Arrays.asList(new String[] { "iniza con", "contiene", "finisce con", "è uguale a",
					"è diverso da", "è maggiore di", "è maggiore o uguale a", "è minore o uguale a" }));
			filterProperty.setCaption("Choose type of filter");
			filterProperty.setInputPrompt("filter");
			Field<?> field = createField(pid, h);
			field.setCaption("Property to filter: " + pid);
			field.setIcon(FontAwesome.SEARCH);
			field.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
			h.addComponent(filterProperty);
			h.addComponent(field);
			formFiledBinding.bind(field, pid);
			h.setWidth("100%");
			addComponent(h);

		}
		HorizontalLayout buttonPanel = new HorizontalLayout();
		buttonPanel.setSpacing(true);
		buttonPanel.setStyleName("buttonPanelOnModal");
		Button search = new Button("search");
		search.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
			}
		});
		addComponent(search);
		Button reset = new Button("search");
		reset.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
			}
		});
		buttonPanel.addComponent(search);
		buttonPanel.addComponent(reset);
		addComponent(buttonPanel);	
	}

	/**
	 * 
	 * @param pid
	 * @param uicontext
	 * @return
	 */
	protected Field<?> createField(final String pid, Component uicontext) {
		@SuppressWarnings("rawtypes")
		final Property p = item.getItemProperty(pid);
		LOG.info("create field : {}", pid);
		final Field<?> f = CustomFieldFactory.get().createField(item, pid, uicontext);
		f.setBuffered(true);

		if (f instanceof TextField) {
			((TextField) f).setNullRepresentation("");
		}

		f.addValidator(new BeanValidator(entityClass, pid));

		if (String.class.isAssignableFrom(p.getType())) {
			f.setWidth("80%");
		}
		if (Number.class.isAssignableFrom(p.getType())) {
			f.setWidth("30%");
		}
		return f;
	}

	/**
	 * 
	 * @return
	 */
	private T createEmptyInstance() {
		try {
			return (T) entityClass.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
