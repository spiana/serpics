package com.serpics.vaadin.ui.component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serpics.vaadin.data.utils.I18nUtils;
import com.serpics.vaadin.data.utils.PropertiesUtils;
import com.serpics.vaadin.jpacontainer.ServiceContainerFactory;
import com.serpics.vaadin.ui.PropertyList;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.metadata.MetadataFactory;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;

public class One2oneField<M, T> extends CustomField<T> {
	private static final long serialVersionUID = -6909188345491634488L;
	private static transient Logger LOG = LoggerFactory
			.getLogger(One2oneField.class);
	private transient PropertyList<T> propertyList;
	private transient String[] displayProperties;
	private transient final Set<String> hideProperties = new HashSet<String>(0);
	private transient Set<String> readOnlyProperties  = new HashSet<String>(0);
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
		
	
	}

	@SuppressWarnings("unused")
	private EntityItem<T> createEntityItem(JPAContainer<T> container) {

        EntityItem<T> entityItem = null;
        try {
        	Class<? extends T> entityClass =getType();
            entityItem = container.createEntityItem(entityClass.newInstance());
        } catch (final InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return entityItem;
    }
	
	private void buildContent(FormLayout layout) {
		fieldGroup.setItemDataSource(entityItem);
		fieldGroup.setBuffered(true);
		
		if (displayProperties == null)
			displayProperties = PropertiesUtils.get().getEditProperty(getType().getSimpleName());
			
		String[] _readOnlyProperties = PropertiesUtils.get().getReadOnlyProperty(getType().getSimpleName());
			
		if (_readOnlyProperties != null)
			readOnlyProperties.addAll(Arrays.asList(_readOnlyProperties));
		
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
						if (!hideProperties.contains(pid))
							layout.addComponent(createField(pid));
		}

	}

	protected Field<?> createField(final String pid) {
		final Field<?> f = CustomFieldFactory.get().createField(entityItem,
				pid, this);
		fieldGroup.bind(f, pid);
		f.setBuffered(true);
		f.addValidator(new BeanValidator(getType(), pid));
		if (readOnlyProperties.contains(pid) || isReadOnly())
			f.setReadOnly(true);
	
		if (propertyList.getClassMetadata().getProperty(pid).getAnnotation(Column.class) != null){
			Column c = propertyList.getClassMetadata().getProperty(pid).getAnnotation(Column.class);
			if (!c.updatable() && entityItem.isPersistent())
				f.setReadOnly(true);
			if (!c.insertable() && !entityItem.isPersistent())
				f.setReadOnly(true);
		}
		
		if (propertyList.getClassMetadata().getProperty(pid).getAnnotation(JoinColumn.class) != null){
			JoinColumn c = propertyList.getClassMetadata().getProperty(pid).getAnnotation(JoinColumn.class);
			if (!c.updatable() && entityItem.isPersistent())
				f.setReadOnly(true);
			if (!c.insertable() && !entityItem.isPersistent())
				f.setReadOnly(true);
		}
			
		String message = I18nUtils.getMessage(getType().getSimpleName().toLowerCase() +"." + pid , null);
		if (message != null)
			f.setCaption(message);
		
		PropertiesUtils.get().setFieldProperty(getType().getSimpleName(), pid, f);
		
		return f;
	}

	@Override
	protected Component initContent() {
		FormLayout layout = new FormLayout();
		layout.setCaption(parentPropertyId.toString());
		
		propertyList = new PropertyList<T>(MetadataFactory.getInstance()
				.getEntityClassMetadata((Class<T>) getType()));
		this.fieldGroup.setItemDataSource(entityItem);
		
		T value = (T) masterEntity.getItemProperty(parentPropertyId).getValue();
		EntityItem<?> a= masterEntity.getItemProperty(parentPropertyId).getItem();
			JPAContainer<T> container = ServiceContainerFactory.make(masterEntity
					.getItemProperty(parentPropertyId).getType());
			if (value == null){
				this.entityItem = createEntityItem(container);
				//if (this.backReferencePropertyId != null)
				//	this.entityItem.getItemProperty(this.backReferencePropertyId).setValue(this.masterEntity.getEntity());
			}else{
				this.entityItem = container.getItem(container.getEntityProvider()
					.getIdentifier(value));
			}

		buildContent(layout);
		setCaption(this.parentPropertyId.toString());
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
			 
			 if (!entityItem.isPersistent()) {
	                entityItem.getContainer().addEntity(entityItem.getEntity());
	    	 }  
			 masterEntity.getItemProperty(parentPropertyId).setValue(entityItem.getEntity());
             masterEntity.commit();
			 entityItem.getContainer().commit();
			 
		} catch (CommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
}
