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
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
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

	private MasterTableListner masterTableListner;
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
		removeAllComponents();
		formFiledBinding = new BeanFieldGroup<T>(entityClass);
		ComboBox c;
		Field<?> field;

		for (String pid : searchProperties) {
			if (pid.contains("."))
				propertyList.addNestedProperty(pid);
			VerticalLayout h = new VerticalLayout();
			h.addComponent(new Label(pid));
			c = new ComboBox("", Arrays.asList(new String[] { "iniza con", "contiene", "finisce con", "è uguale a",
					"è diverso da", "è maggiore di", "è maggiore o uguale a", "è minore o uguale a" }));
			field = createField(pid);
			field.setCaption("");
			((TextField) field).setInputPrompt("Filter");
			((TextField) field).setIcon(FontAwesome.SEARCH);
			((TextField) field).addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

			h.addComponent(c);
			h.addComponent(field);
			formFiledBinding.bind(field, pid);
			h.setWidth("100%");
			h.setHeight("50%");
			addComponent(h);
			h.removeComponent(new Button("Cancel"));
		}

		Button search = new Button("search");
		//masterTableListner.get().searchButtonClickListener(this.container, search, field, c);

		addComponent(search);

	}

	@Override
	protected Field<?> createField(final String pid) {
		@SuppressWarnings("rawtypes")
		final Property p = item.getItemProperty(pid);
		final Field<?> f = CustomFieldFactory.get().createField(item, pid, this);
		f.setBuffered(true);

		if (f instanceof TextField) {
			((TextField) f).setNullRepresentation("");
		}
		if (String.class.isAssignableFrom(p.getType())) {
			f.setWidth("80%");
		}
		if (Number.class.isAssignableFrom(p.getType())) {
			f.setWidth("30%");
		}
		return f;
	}

	public String[] searchProperties() {
		return this.searchProperties;
	}
}
