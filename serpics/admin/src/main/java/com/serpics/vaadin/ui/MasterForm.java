package com.serpics.vaadin.ui;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serpics.base.data.model.MultilingualString;
import com.serpics.vaadin.ui.EntityComponent.EntityFormComponent;
import com.serpics.vaadin.ui.component.CustomFieldFactory;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.metadata.MetadataFactory;
import com.vaadin.addon.jpacontainer.metadata.PropertyKind;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroupFieldFactory;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

public abstract class MasterForm<T> extends FormLayout implements
		EntityFormComponent<T> {
	private static final long serialVersionUID = -7816433625437405000L;
	private static final transient Logger LOG = LoggerFactory
			.getLogger(MasterForm.class);

	private transient PropertyList<T> propertyList;

	protected FieldGroup fieldGroup;
	private boolean initialized = false;
	private String[] displayProperties;
	private final Set<String> hideProperties = new HashSet<String>(0);
	private Set<String> readOnlyProperties = new HashSet<String>(0);
	protected EntityItem<T> entityItem;
	private boolean readOnly = true;
	protected Class<T> entityClass;

	public MasterForm(final Class<T> clazz) {
		propertyList = new PropertyList<T>(MetadataFactory.getInstance()
				.getEntityClassMetadata(clazz));

		this.entityClass = clazz;

		setWidth("100%");
		setImmediate(false);
		setMargin(true);
		setSpacing(true);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.serpics.vaadin.ui.EntityComponent#init()
	 * 
	 * To implements in extended class define 1 - Display Properties 2 - read
	 * only properties 3 - hide properties
	 */
	@Override
	public void init() {

	}

	@Override
	public Class<T> getEntityType() {
		return entityClass;
	}


	public Item getEntityItem() {
		return entityItem;
	}

	protected void buildContent(){
		removeAllComponents();
		fieldGroup = new FieldGroup();
		//fieldGroup.setFieldFactory(this);
		fieldGroup.setItemDataSource(entityItem);
		fieldGroup.setBuffered(true);
	
		
		if (displayProperties != null)
			addField(displayProperties);
		else {
			addField(propertyList.getAllAvailablePropertyNames().toArray(
					new String[] {}));
		}
		for (final String pid : readOnlyProperties) {
			if (fieldGroup.getField(pid) != null)
				fieldGroup.getField(pid).setReadOnly(true);
		}
		initialized = true;
	}
	
	@Override
	public void setEntityItem(final EntityItem<T> entityItem) {
		this.entityItem = entityItem;
		buildContent();
	}

	private void addField(final String[] propertyNames) {
		for (final String pid : propertyNames) {		
			if (propertyList.getClassMetadata().getProperty(pid) == null)
				LOG.error("properity {} not found !", pid);
	
			else if (propertyList.getClassMetadata().getProperty(pid)
					.getAnnotation(Id.class) == null)
				if (propertyList.getClassMetadata().getProperty(pid)
						.getAnnotation(EmbeddedId.class) == null)
					if(propertyList.getPropertyKind(pid).equals(PropertyKind.SIMPLE)  ||
							propertyList.getPropertyKind(pid).equals(PropertyKind.ONE_TO_MANY) ||
							propertyList.getPropertyKind(pid).equals(PropertyKind.MANY_TO_ONE) 
							|| entityItem.isPersistent())
						if (!hideProperties.contains(pid)){
							Field<?> f = createField(pid);
							if (readOnlyProperties.contains(pid))
								f.setReadOnly(true);
							addComponent(f);
						}
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
		f.addValidator(new BeanValidator(entityClass, pid));
		if (String.class.isAssignableFrom(p.getType())) {
			f.setWidth("80%");
		}
		return f;
	}

	@Override
	public void save() throws CommitException {
		if (initialized){
			if (fieldGroup.isModified()) {
				fieldGroup.commit();
				// test if new item
				if (!entityItem.isPersistent()) {
					entityItem.getContainer().addEntity(entityItem.getEntity());
				}
			}
		}
	}

	public void setFieldFactory(final FieldGroupFieldFactory fieldFactory) {
		fieldGroup.setFieldFactory(fieldFactory);
	}

	@Override
	public void discard() {
		if (initialized)
			fieldGroup.discard();
	}

	@Override
	public void attach() {
		setLocale(UI.getCurrent().getSession().getLocale());
		if(initialized){
			if (readOnly) {
				fieldGroup.setEnabled(false);
			} else {
				fieldGroup.setEnabled(true);
				
			}
		}
		super.attach();
	}


	public void setDisplayProperties(final String[] displayProperties) {
		this.displayProperties = displayProperties;
	}

	@Override
	public boolean isReadOnly() {
		return readOnly;
	}

	@Override
	public void setReadOnly(final boolean readOnly) {
		this.readOnly = readOnly;
	}

	public void setHideProperties(final String[] hideProperties) {
		this.hideProperties.addAll(Arrays.asList(hideProperties));
	}

	public void setReadOnlyProperties(final String[] readOnlyProperties) {
		this.readOnlyProperties.addAll(Arrays.asList(readOnlyProperties));
	}

	@Override
	public boolean isInitialized() {
		return initialized;
	}

	@Override
	public boolean isModifield() {
		if(initialized)
			return fieldGroup.isModified();
		else
			return false;

	}

	@Override
	public boolean isValid() {
		return fieldGroup.isValid();
	}

}
