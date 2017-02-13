/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.vaadin.ui;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import javax.persistence.OneToMany;

import com.serpics.vaadin.ui.EntityComponent.EntityComponentChild;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.filter.Compare;

public abstract class MasterDetailTable<T, P> extends MasterTable<T> implements EntityComponentChild<T, P>{
    private static final long serialVersionUID = 5325235469617666772L;

    protected P masterEntity;
    protected Object propertyId;
    protected EntityItem<P> parentEntity;
	protected transient Object backReferencePropertyId;

    public MasterDetailTable(final Class<T> entityClass) {
        super(entityClass);
        setSearchFormEnable(false);
    }
    public MasterDetailTable(final Class<T> entityClass , Object parentProperty) {
        super(entityClass);
        setSearchFormEnable(false);
        this.propertyId= parentProperty;
    }

    @Override
    public void setParentEntity(final EntityItem<P> parent) {
    	
    	buildContainer();
    	
    	this.parentEntity = parent;
        this.masterEntity = parent.getEntity();
        setBackReferenceContainerFilter();
    }

    private void setBackReferenceContainerFilter(){
    	if (this.backReferencePropertyId == null)
    		this.backReferencePropertyId = getMappedByProperty(this.propertyId.toString());
    	
    	 container.removeContainerFilters(backReferencePropertyId);
        if (parentEntity.isPersistent()	){
        	container.addContainerFilter(new Compare.Equal(backReferencePropertyId, masterEntity));
        }else{
        	Container.Filter filter = new com.vaadin.data.util.filter.IsNull(backReferencePropertyId);
			this.container.addContainerFilter(filter);
        }
         container.refresh();
    }
    
    
    @Override
    public void setParentProperty(final Object propertyId) {
        this.propertyId = propertyId;
    }
   
   /* (non-Javadoc)
    * @see com.serpics.vaadin.ui.MasterTable#removeAllFilter()
    */
	@Override
	public void removeAllFilter() {
		super.removeAllFilter();
		setBackReferenceContainerFilter();
	}
    
    @SuppressWarnings("unchecked")
	@Override
    public EntityItem<T> createEntityItem() {
     try{
    	  T newInstance = container.getEntityClass().newInstance();
	      BeanItem<T> beanItem =  new BeanItem<T>(newInstance);
	      beanItem.getItemProperty(this.backReferencePropertyId).setValue(this.masterEntity);
	      EntityItem<T> item = container.createEntityItem(newInstance);
	      return item ;
     }catch (Exception _e){
    	    Logger.getLogger(super.getClass().getName()).warning("Could not instantiate detail instance " + this.container.getEntityClass().getName());
     }
     	return null;
    }
    
    protected  String getMappedByProperty(String propertyName)
	  {
	    OneToMany otm = getAnnotationForProperty(OneToMany.class, masterEntity.getClass(), propertyName);
	    if ((otm != null) && (!("".equals(otm.mappedBy())))) {
	      return otm.mappedBy();
	    }
	    return propertyName;
	  }
	
	 @SuppressWarnings("unchecked")
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
