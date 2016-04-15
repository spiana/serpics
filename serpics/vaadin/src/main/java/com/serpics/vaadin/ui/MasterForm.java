package com.serpics.vaadin.ui;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serpics.base.MultiValueField;
import com.serpics.base.Multilingual;
import com.serpics.vaadin.data.utils.I18nUtils;
import com.serpics.vaadin.data.utils.PropertiesUtils;
import com.serpics.vaadin.ui.EntityComponent.EntityFormComponent;
import com.serpics.vaadin.ui.component.CustomFieldFactory;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.metadata.MetadataFactory;
import com.vaadin.addon.jpacontainer.metadata.PropertyKind;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroupFieldFactory;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.UI;

public abstract class MasterForm<T> extends FormLayout implements EntityFormComponent<T> {
	private static final long serialVersionUID = -7816433625437405000L;
	private static final transient Logger LOG = LoggerFactory.getLogger(MasterForm.class);

	private transient PropertyList<T> propertyList;

	protected FieldGroup fieldGroup;
	private boolean initialized = false;
	private String[] displayProperties;
	private final Set<String> hideProperties = new HashSet<String>(0);
	private Set<String> readOnlyProperties ;
	protected EntityItem<T> entityItem;
	private boolean readOnly = true;
	protected Class<T> entityClass;

	public MasterForm(final Class<T> clazz) {
		propertyList = new PropertyList<T>(MetadataFactory.getInstance().getEntityClassMetadata(clazz));
		this.entityClass = clazz;
		setWidth("100%");
		setImmediate(true);
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

	public EntityItem<T> getEntityItem() {
		return entityItem;
	}

	protected void buildContent() {
		removeAllComponents();
		
		fieldGroup = new FieldGroup();
		// fieldGroup.setFieldFactory(this);
		fieldGroup.setItemDataSource(entityItem);
		fieldGroup.setBuffered(true);

		if (this.displayProperties == null)
			this.displayProperties = PropertiesUtils.get().getEditProperty(entityClass.getSimpleName());

		if (this.readOnlyProperties == null || this.readOnlyProperties.isEmpty()){
			String[] _readonly = PropertiesUtils.get().getReadOnlyProperty(entityClass.getSimpleName());
			if (_readonly != null)
				this.readOnlyProperties  = new HashSet<String>(Arrays.asList(_readonly));
			else
				this.readOnlyProperties = new HashSet<String>(0);
		}
		if (displayProperties != null)
			addField(displayProperties);
		else {
			addField(propertyList.getAllAvailablePropertyNames().toArray(new String[] {}));
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
		removeAllComponents();
		buildContent();
	}

	private void addField(final String[] propertyNames) {
		for (final String pid : propertyNames) {
			if (pid.contains(".")){
				propertyList.addNestedProperty(pid);
			}
			LOG.info("try to initialize property {}" , pid);
			
			if (propertyList.getPropertyKind(pid) == null)
				LOG.error("properity {} not found !", pid);

			else if (
					propertyList.getClassMetadata().getProperty(pid).getAnnotation(Id.class) == null &&
					propertyList.getClassMetadata().getProperty(pid).getAnnotation(EmbeddedId.class) == null){
					if (propertyList.getPropertyKind(pid).equals(PropertyKind.SIMPLE)
							|| propertyList.getPropertyKind(pid).equals(PropertyKind.ONE_TO_MANY)
							|| propertyList.getPropertyKind(pid).equals(PropertyKind.MANY_TO_ONE)
							|| propertyList.getPropertyKind(pid).equals(PropertyKind.NONPERSISTENT)
							|| propertyList.getPropertyKind(pid).equals(PropertyKind.EMBEDDED)
							|| Multilingual.class.isAssignableFrom(propertyList.getPropertyType(pid))
							|| MultiValueField.class.isAssignableFrom(propertyList.getPropertyType(pid))
							|| entityItem.isPersistent()){
						if (!hideProperties.contains(pid)) {
							Field<?> f = createField(pid);
							if (readOnlyProperties.contains(pid))
								f.setReadOnly(true);
							else if (propertyList.getClassMetadata().getProperty(pid).getAnnotation(Column.class) != null){
								Column c = propertyList.getClassMetadata().getProperty(pid).getAnnotation(Column.class);
								if (!c.updatable() && entityItem.isPersistent())
									f.setReadOnly(true);
								if (!c.insertable() && !entityItem.isPersistent())
									f.setReadOnly(true);
								
							}else if (propertyList.getClassMetadata().getProperty(pid).getAnnotation(JoinColumn.class) != null){
								JoinColumn c = propertyList.getClassMetadata().getProperty(pid).getAnnotation(JoinColumn.class);
								if (!c.updatable() && entityItem.isPersistent())
									f.setReadOnly(true);
								if (!c.insertable() && !entityItem.isPersistent())
									f.setReadOnly(true);
							}
							addComponent(f);
						}
					}
			}
		}

	}

	protected Field<?> createField(final String pid) {
		return createField(pid, this);
	}
	protected Field<?> createField(final String pid , Component uicontext) {
		return createField(pid, uicontext , this.entityItem);
	}
	
	
	protected Field<?> createField(final String pid , Component uicontext , EntityItem<T> item) {
		@SuppressWarnings("rawtypes")
		final Property p = item.getItemProperty(pid);
		LOG.debug("create field : {}", pid);
		final Field<?> f = CustomFieldFactory.get().createField(item, pid, uicontext);
		fieldGroup.bind(f, pid);
		f.setBuffered(true);
	
		f.addValidator(new BeanValidator(entityClass, pid));
	
		String message = I18nUtils.getMessage(entityClass.getSimpleName().toLowerCase() + "." + pid, null);
		if (message != null)
			f.setCaption(message);

		PropertiesUtils.get().setFieldProperty(entityClass.getSimpleName(), pid, f);
		return f;
	}

	@Override
	public void save() throws CommitException {
		if (initialized) {
			if (fieldGroup.isModified()) {
				fieldGroup.commit();
				
				entityItem.commit();
				
				if (!entityItem.isPersistent()) {
					entityItem.getContainer().addEntity(entityItem.getEntity());
				}
				try{	
					entityItem.getContainer().commit();
				}catch (Exception e){
					//TODO: to fix with something better.
					entityItem.getContainer().refresh();
					throw e;
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
		if (initialized) {
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
		if (this.readOnlyProperties == null){
			this.readOnlyProperties = new HashSet<String>(0);
		}
		this.readOnlyProperties.addAll(Arrays.asList(readOnlyProperties));
	}

	@Override
	public boolean isInitialized() {
		return initialized;
	}

	@Override
	public boolean isModifield() {
		if (initialized)
			return fieldGroup.isModified();
		else
			return false;

	}

	@Override
	public boolean isValid() {
		return fieldGroup.isValid();
	}

}
