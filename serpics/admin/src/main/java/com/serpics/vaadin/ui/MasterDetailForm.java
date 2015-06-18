package com.serpics.vaadin.ui;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.persistence.OneToOne;

import org.springframework.util.Assert;

import com.serpics.vaadin.jpacontainer.provider.ServiceContainerFactory;
import com.serpics.vaadin.ui.EntityComponent.EntityComponentChild;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;

public abstract class MasterDetailForm<MASTER, DETAIL> extends MasterForm<DETAIL> implements EntityComponentChild<DETAIL,MASTER> {
    private static final long serialVersionUID = 7628189100288027785L;

    private transient EntityItem<MASTER> masterEntity;
    private transient Object parentPropertyId;
 //   private transient Object backReferencePropertyId;
  
    public MasterDetailForm(final Class<DETAIL> clazz) {
        super(clazz);
    }


    @SuppressWarnings({ "unchecked" })
	@Override
    public void setParentEntity(final EntityItem<MASTER> masterEntity) {
    	Assert.notNull(this.parentPropertyId , "parentPropertyId must not be null !");
        this.masterEntity = masterEntity;
        Object backReferencePropertyId = getMappedByProperty(this.parentPropertyId.toString());
        DETAIL value = (DETAIL) masterEntity.getItemProperty(parentPropertyId).getValue();
		JPAContainer<DETAIL> container = ServiceContainerFactory.make(masterEntity
					.getItemProperty(parentPropertyId).getType());
		if (value == null){
			this.entityItem = createEntityItem(container);
			 this.entityItem.getItemProperty(backReferencePropertyId).setValue(masterEntity.getEntity());
		}else{
			this.entityItem = container.getItem(container.getEntityProvider()
				.getIdentifier(value));
		}
        buildContent();
    }
    
    
    @SuppressWarnings("unused")
	private EntityItem<DETAIL> createEntityItem(JPAContainer<DETAIL> container) {

        EntityItem<DETAIL> entityItem = null;
        try {
        	Class<? extends DETAIL> entityClass =getType();
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

    @Override
    public void save() throws CommitException {
    	if (isInitialized()){
	    	super.save();
	    	if (!entityItem.isPersistent()){
	    		 masterEntity.getItemProperty(parentPropertyId).setValue(entityItem.getEntity());
	             masterEntity.getContainer().commit();
	    	}
    	}
    }
    
    @Override
    public void setParentProperty(final Object parentPropertyId) {
        this.parentPropertyId = parentPropertyId;
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

    
	  	@SuppressWarnings("unchecked")
		public Class<? extends DETAIL> getType() {
			return masterEntity.getItemProperty(parentPropertyId).getType();
		}
}
