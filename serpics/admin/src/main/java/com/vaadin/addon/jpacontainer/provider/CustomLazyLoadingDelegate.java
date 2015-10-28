package com.vaadin.addon.jpacontainer.provider;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.vaadin.addon.jpacontainer.EntityProvider;
import com.vaadin.addon.jpacontainer.LazyLoadingDelegate;

public class CustomLazyLoadingDelegate
implements LazyLoadingDelegate , Serializable

{

	private static final long serialVersionUID = 4843643429545351670L;
	private EntityProvider<?> entityProvider;

public void setEntityProvider(EntityProvider<?> ep)
{
  this.entityProvider = ep;
}

public <E> E ensureLazyPropertyLoaded(E entity, String propertyName) {
  String prop = getRootPropertyName(propertyName);
  try {
    Object value = lazilyLoadPropertyValue(entity, prop);
    value = recurseIfNested(propertyName, value);
    trySetUsingSetter(entity, prop, value);
  } catch (IllegalArgumentException e) {
    e.printStackTrace();
  }
  return entity;
}

private <E> Object lazilyLoadPropertyValue(E entity, String prop)
{
  CriteriaBuilder cb = this.entityProvider.getEntityManager().getCriteriaBuilder();

  CriteriaQuery q = cb.createQuery();
  Root root = q.from(entity.getClass());
  q.select(root.get(prop));
  q.where(cb.equal(root.get(getprimaryKey(entity.getClass())), cb.literal(tryGetEntityId(entity))));
  return this.entityProvider.getEntityManager().createQuery(q).getResultList();
}

private Object recurseIfNested(String propertyName, Object value)
{
  if (isNestedProperty(propertyName))
  {
    Object subEntity = ((List)value).get(0);
    String subProperty = propertyName.substring(propertyName.indexOf(46) + 1);

    value = ensureLazyPropertyLoaded(subEntity, subProperty);
  }
  return value;
}

private String getRootPropertyName(String propertyName)
{
  if (isNestedProperty(propertyName)) {
    return propertyName.substring(0, propertyName.indexOf(46));
  }
  return propertyName;
}

private boolean isNestedProperty(String propertyName)
{
  return (propertyName.indexOf(46) != -1);
}

private <E> Object tryGetEntityId(E entity) {
  try {
    return getEntityId(entity.getClass() , entity);
  } catch (IllegalArgumentException e) {
    throw new RuntimeException("Could not get the entity id.", e);
  } catch (IllegalAccessException e) {
    throw new RuntimeException("Could not get the entity id.", e);
  } catch (InvocationTargetException e) {
    throw new RuntimeException("Could not get the entity id.", e);
  }
}

private <E> String getprimaryKey( Class<?> clazz){
	  for (Field f : clazz.getDeclaredFields()) {
		    if ((f.isAnnotationPresent(Id.class) || f.isAnnotationPresent( EmbeddedId.class)))
		    	return f.getName();
		    }
	  
	 if(clazz.getSuperclass() != null)
		return  getprimaryKey(clazz.getSuperclass());
		  
	 return null; 
	
}


private <E> Object getEntityId(Class<?> clazz , E entity)
  throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
{
  for (Field f : clazz.getDeclaredFields()) {
    if ((f.isAnnotationPresent(Id.class) || f.isAnnotationPresent(EmbeddedId.class))) {
	    try {
	      f.setAccessible(true);
	      Object localObject1 = f.get(entity);
	
	      return localObject1; 
	      } finally { f.setAccessible(false);
	    }
    }

  }

  for (Method m : clazz.getMethods()) {
    if (m.isAnnotationPresent(Id.class)|| m.isAnnotationPresent(EmbeddedId.class)) {
      return m.invoke(entity, new Object[0]);
    }
  }
  if (clazz.getSuperclass() != null)
	  return getEntityId(clazz.getSuperclass() , entity);
  
  return null;
}

private <E> void trySetUsingSetter(E entity, String propertyName, Object value)
{
  try {
    setUsingSetter(entity, propertyName, value);
  } catch (IllegalArgumentException e) {
    throw new RuntimeException("Could not set lazy loaded value for entity.", e);
  }
  catch (SecurityException e) {
    throw new RuntimeException("Could not set lazy loaded value for entity.", e);
  }
  catch (IllegalAccessException e) {
    throw new RuntimeException("Could not set lazy loaded value for entity.", e);
  }
  catch (InvocationTargetException e) {
    throw new RuntimeException("Could not set lazy loaded value for entity.", e);
  }
  catch (InstantiationException e) {
    throw new RuntimeException("Could not set lazy loaded value for entity.", e);
  }
  catch (NoSuchMethodException e) {
    throw new RuntimeException("Could not set lazy loaded value for entity.", e);
  }
}

private <E> void setUsingSetter(E entity, String propertyName, Object value)
  throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException, SecurityException, NoSuchMethodException
{
  Method setter = findSetterFor(entity, propertyName);
  if (setter != null) {
    Class parameterType = setter.getParameterTypes()[0];
    if (Collection.class.isAssignableFrom(parameterType)) {
      if (Set.class.isAssignableFrom(parameterType))
        value = new HashSet((Collection)value);
    }
    else if (value instanceof Collection)
    {
      value = ((Collection)value).iterator().next();
    }
    setter.invoke(entity, new Object[] { value });
  }
}

private <E> Method findSetterFor(E entity, String propertyName) {
  for (Method m : entity.getClass().getMethods()) {
    if (m.getName().equalsIgnoreCase("set" + propertyName)) {
      return m;
    }
  }
  return null;
}

}
