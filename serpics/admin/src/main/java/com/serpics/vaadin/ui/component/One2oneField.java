package com.serpics.vaadin.ui.component;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serpics.base.data.model.MultilingualString;
import com.serpics.vaadin.ui.EntityForm;
import com.serpics.vaadin.ui.MultilingualStringConvert;
import com.serpics.vaadin.ui.PropertyList;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.metadata.MetadataFactory;
import com.vaadin.addon.jpacontainer.metadata.PropertyKind;
import com.vaadin.addon.jpacontainer.provider.ServiceContainerFactory;
import com.vaadin.data.Property;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class One2oneField<M, T> extends CustomField<T> {
	private static final long serialVersionUID = -6909188345491634488L;
	private static transient Logger LOG = LoggerFactory
			.getLogger(One2oneField.class);
	private transient PropertyList<T> propertyList;
	private transient String[] displayProperties;
	private transient final Set<String> hideProperties = new HashSet<String>(0);
	private transient String[] readOnlyProperties = {};
	private transient EntityItem<T> entityItem;
	private transient EntityItem<M> masterEntity;
	private transient Object parentPropertyId;


	private FieldGroup fieldGroup ;

	@SuppressWarnings("unchecked")
	public One2oneField(EntityItem<M> item, Object parentPropertyId) {
		this.fieldGroup = new FieldGroup();
		this.fieldGroup.setBuffered(true);
		
		this.masterEntity = item;
		this.parentPropertyId = parentPropertyId;

		T value = (T) item.getItemProperty(parentPropertyId).getValue();
		JPAContainer<T> container = ServiceContainerFactory.make(item
				.getItemProperty(parentPropertyId).getType());

		this.entityItem = container.getItem(container.getEntityProvider()
				.getIdentifier(value));

	}

	private void buildContent(FormLayout layout) {
		fieldGroup.setItemDataSource(entityItem);
		fieldGroup.setBuffered(true);
		if (displayProperties != null)
			addField(displayProperties , layout);
		else
			addField(propertyList.getAllAvailablePropertyNames().toArray(
					new String[] {}) , layout);

	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<? extends T> getType() {
		return masterEntity.getItemProperty(parentPropertyId).getType();
	}

	private void addField(final String[] propertyNames , FormLayout layout) {
		for (final String pid : propertyNames) {
			if (propertyList.getClassMetadata().getProperty(pid) == null)
				LOG.error("properity {} not found !", pid);
			else if (propertyList.getClassMetadata().getProperty(pid)
					.getAnnotation(Id.class) == null)
				if (propertyList.getClassMetadata().getProperty(pid)
						.getAnnotation(EmbeddedId.class) == null)
					if (propertyList.getPropertyKind(pid).equals(
							PropertyKind.SIMPLE))
						if (!hideProperties.contains(pid))
							layout.addComponent(createField(pid));
		}

	}

	protected Field<?> createField(final String pid) {
		@SuppressWarnings("rawtypes")
		final Property p = entityItem.getItemProperty(pid);

		LOG.info("create field : {}", pid);
		final Field<?> f = CustomFieldFactory.get().createField(entityItem,
				pid, this);
		fieldGroup.bind(f, pid);
		f.setBuffered(true);

		if (f instanceof TextField) {
			if (MultilingualString.class.isAssignableFrom(p.getType())) {
				((TextField) f).setConverter(new MultilingualStringConvert());
				f.setWidth("80%");
			}
			((TextField) f).setNullRepresentation("");
		}
		f.addValidator(new BeanValidator(getType(), pid));
		if (String.class.isAssignableFrom(p.getType())) {
			f.setWidth("80%");
		}
		return f;
	}

	@Override
	protected Component initContent() {
		FormLayout layout = new FormLayout();
		layout.setCaption(parentPropertyId.toString());
		
		propertyList = new PropertyList<T>(MetadataFactory.getInstance()
				.getEntityClassMetadata((Class<T>) getType()));
		this.fieldGroup.setItemDataSource(entityItem);

		if (this.entityItem.getEntity() == null) {
			// JPAContainer<T> container = (JPAContainer<T>) this.entityItem
			// .getContainer();
			// container.createEntityItem(entity)

		}

		buildContent(layout);
		
		return layout;
	}
	
	@Override
	public boolean isModified() {
		return fieldGroup.isModified();
	}
	
	@Override
	public void commit() throws SourceException, InvalidValueException {
		try {
			fieldGroup.commit();
		} catch (CommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
