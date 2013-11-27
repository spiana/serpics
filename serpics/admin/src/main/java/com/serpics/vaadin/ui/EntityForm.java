package com.serpics.vaadin.ui;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.SerpicsStringToNumberConverter;
import com.vaadin.addon.jpacontainer.metadata.MetadataFactory;
import com.vaadin.addon.jpacontainer.metadata.PropertyKind;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroupFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class EntityForm<T> extends FormLayout implements
		FieldGroupFieldFactory , EntityComponent{

	private transient PropertyList<T> propertyList;

	private FieldGroup fieldGroup;

	private boolean initialized = false;

	protected String[] displayProperties;

	EntityItem<T> entityItem;

	private String prefix;

	public EntityForm(Class<T> clazz) {

		propertyList = new PropertyList<T>(MetadataFactory.getInstance()
				.getEntityClassMetadata(clazz));

		setSizeUndefined();
		fieldGroup = new FieldGroup();
		fieldGroup.setFieldFactory(this);
		setImmediate(false);
		setWidth("100%");
		setHeight("100%");
		setMargin(true);
		setSpacing(true);

	}

	/**
	 * @return the caption of the editor window
	 */
	private String buildCaption() {
		// return String.format("%s %s", entityItem.getItemProperty("firstName")
		// .getValue(), entityItem.getItemProperty("lastName").getValue());
		return "main";

	}

	@Override
	public <T extends Field> T createField(Class<?> dataType, Class<T> fieldType) {
		DefaultFieldGroupFieldFactory fa = new DefaultFieldGroupFieldFactory();
		T f = fa.createField(dataType, fieldType);
		if (f instanceof TextField)
			f.setWidth("500px");

		return f;
	}

	public Item getEntityItem() {
		return entityItem;
	}

	public void setEntityItem(EntityItem<T> entityItem) {
		this.entityItem = entityItem;
		fieldGroup.setItemDataSource(entityItem);
		fieldGroup.setBuffered(true);

		if (!initialized) {
			if (displayProperties != null)
				addField(displayProperties);
			else
				addField(propertyList.getPropertyNames().toArray( new String[] {} ));
			initialized = true;
		}

	}

	private void addField(String[] propertyNames) {
		for (String pid : propertyNames) {
			createField(pid);
		}

	}

	private void createField(String pid) {
		Property p = entityItem.getItemProperty((prefix != null ? prefix : "")
				+ pid);
		if (propertyList.getPropertyKind(pid).equals(PropertyKind.SIMPLE)) {
			Field<?> f = fieldGroup.buildAndBind((prefix != null ? prefix : "")
					+ (String) pid, (prefix != null ? prefix : "") + pid);
			f.setBuffered(true);
			if (f instanceof TextField) {
				if (Number.class.isAssignableFrom(p.getType()))
					((TextField) f)
							.setConverter(new SerpicsStringToNumberConverter());
				((TextField) f).setNullRepresentation("");
			}

			addComponent(f);
		}

	}

	public void save() throws CommitException {

		fieldGroup.commit();
		if (entityItem.getItemId() == null)
			entityItem.getContainer().addEntity(entityItem.getEntity());
	}

	public void discard() {
		fieldGroup.discard();
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix + ".";
	}

	public void setDisplayProperties(String[] displayProperties) {
		this.displayProperties = displayProperties;
	}
}
