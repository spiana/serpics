package com.serpics.vaadin.ui.component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serpics.base.data.model.MultilingualString;
import com.serpics.vaadin.jpacontainer.provider.ServiceContainerFactory;
import com.serpics.vaadin.ui.MultilingualStringConvert;
import com.serpics.vaadin.ui.PropertyList;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.metadata.MetadataFactory;
import com.vaadin.addon.jpacontainer.metadata.PropertyKind;
import com.vaadin.data.Property;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.validator.BeanValidator;
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
	private transient Object backReferencePropertyId;


	private FieldGroup fieldGroup ;

	@SuppressWarnings("unchecked")
	public One2oneField(EntityItem<M> item, Object parentPropertyId) {
		this.fieldGroup = new FieldGroup();
		this.fieldGroup.setBuffered(true);
		
		this.masterEntity = item;
		this.parentPropertyId = parentPropertyId;
		this.backReferencePropertyId = getMappedByProperty(this.parentPropertyId.toString());
		
	
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
		
		T value = (T) masterEntity.getItemProperty(parentPropertyId).getValue();
		EntityItem<?> a= masterEntity.getItemProperty(parentPropertyId).getItem();
			JPAContainer<T> container = ServiceContainerFactory.make(masterEntity
					.getItemProperty(parentPropertyId).getType());
			if (value == null){
				this.entityItem = createEntityItem(container);
				 this.entityItem.getItemProperty(this.backReferencePropertyId).setValue(this.masterEntity.getEntity());
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
	                masterEntity.getItemProperty(parentPropertyId).setValue(entityItem.getEntity());
	             masterEntity.getContainer().commit();
			 }  
			
		} catch (CommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected  String getMappedByProperty(String propertyName)
	  {
	    OneToOne otm = (OneToOne)getAnnotationForProperty(OneToOne.class, masterEntity.getEntity().getClass(), propertyName);
	    if ((otm != null) && (!("".equals(otm.mappedBy())))) {
	      return otm.mappedBy();
	    }
	    return getType().getSimpleName().toLowerCase();
	  }
	
	 private <A extends Annotation> A getAnnotationForProperty(Class<A> annotationType, Class<?> entityClass, String propertyName)
	  {
	    Annotation annotation = getAnnotationFromPropertyGetter(annotationType, entityClass, propertyName);
	    
	    if (annotation == null) {
	      annotation = getAnnotationFromField(annotationType, entityClass, propertyName);
	    }
	    return (A) annotation;
	  }
	 
	 private  <A extends Annotation> A getAnnotationFromField(Class<A> annotationType, Class<?> entityClass, String propertyName)
	  {
	    java.lang.reflect.Field  field = null;
	    try
	    {
	      field = entityClass.getDeclaredField(propertyName);
	 
	    }
	    catch (Exception e)
	    {
	    }
	    if ((field != null) && (field.isAnnotationPresent(annotationType))) {
	      return field.getAnnotation(annotationType);
	    }
	    return null;
	  }

	  private  <A extends Annotation> A getAnnotationFromPropertyGetter(Class<A> annotationType, Class<?> entityClass, String propertyName)
	  {
	    Method getter = null;
	    try {
	      getter = entityClass.getMethod("get" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1), new Class[0]);
	    }
	    catch (Exception e)
	    {
	      try
	      {
	        getter = entityClass.getMethod("is" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1), new Class[0]);
	      }
	      catch (Exception e1)
	      {
	      }
	    }

	    if ((getter != null) ) {
	      if  (getter.isAnnotationPresent(annotationType))
	    	  return getter.getAnnotation(annotationType);
	      else{
	    	  return getAnnotationFromField(annotationType, getter.getDeclaringClass(), propertyName);
	      }
	    }
	    return null;
	  }

}
