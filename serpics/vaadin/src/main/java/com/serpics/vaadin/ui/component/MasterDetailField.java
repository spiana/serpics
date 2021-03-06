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
package com.serpics.vaadin.ui.component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.serpics.core.data.model.MultiValueField;
import com.serpics.i18n.Multilingual;
import com.serpics.vaadin.data.utils.I18nUtils;
import com.serpics.vaadin.data.utils.PropertiesUtils;
import com.serpics.vaadin.data.utils.PropertiesUtils.SmcPropertyDef;
import com.serpics.vaadin.jpacontainer.ServiceContainerFactory;
import com.serpics.vaadin.ui.EntityComponent;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterForm;
import com.serpics.vaadin.ui.PropertyList;
import com.serpics.vaadin.ui.converters.AttributeTypeValueConverter;
import com.serpics.vaadin.ui.converters.MultilingualFieldConvert;
import com.vaadin.addon.jpacontainer.EntityContainer;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.metadata.MetadataFactory;
import com.vaadin.addon.jpacontainer.metadata.PropertyKind;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;

public class MasterDetailField<T,X> extends CustomField<T> implements Handler {
	private static final long serialVersionUID = -7646209629067140150L;
	private final Action add = new Action("Add");
	private final Action modify = new Action("Modify");
	private final Action remove = new Action("Remove");
	private final Action[] actions = {this.add ,this.modify,this.remove};
	
	
	private transient EntityContainer<T> containerForProperty ;
	private transient EntityContainer<X> container;
	private transient EntityItem<T> entityItem;
	private transient Object propertyId;
	private transient Object backReferencePropertyId;
	private transient T masterEntity;
	private  Table table;
	private transient  String[] displayProperties;
	private transient PropertyList<T> propertyList;
	
	
	
	public Table getTable() {
		return table;
	}

	

	@SuppressWarnings("unchecked")
	public MasterDetailField(
			EntityContainer<T> entityContainer,
			EntityItem<T> entityItem, Object propertyId) {
		super();
		setBuffered(true);
		this.containerForProperty = entityContainer;
		this.entityItem	 = entityItem;
		this.propertyId = propertyId;
		this.masterEntity = entityItem.getEntity();
		buildcontainer();
	}
	@SuppressWarnings("unchecked")
	public MasterDetailField(
			EntityContainer<T> entityContainer,
			EntityItem<T> entityItem, Object propertyId ,String[] displayProperties) {
		super();
		setBuffered(true);
		this.containerForProperty = entityContainer;
		this.entityItem	 = entityItem;
		this.propertyId = propertyId;
		this.masterEntity = entityItem.getEntity();
		this.displayProperties = displayProperties;

		
		buildcontainer();
		
		
	}

	@SuppressWarnings("unchecked")
	private void buildcontainer(){
		Class<T> masterEntityClass = this.containerForProperty.getEntityClass();
		Class<T> referencedType= (Class<T>) ServiceContainerFactory.detectReferencedType(
				containerForProperty.getEntityProvider().getEntityManager().getEntityManagerFactory(),
				this.propertyId, masterEntityClass);
		this.container= (EntityContainer<X>) ServiceContainerFactory.make(referencedType);
		this.backReferencePropertyId = getMappedByProperty(this.propertyId.toString());//HibernateUtil.getMappedByProperty(this.masterEntity, this.propertyId.toString());
		
		this.propertyList = new PropertyList<T>(MetadataFactory.getInstance()
				.getEntityClassMetadata(referencedType));
		
		if (entityItem.isPersistent()){
			Container.Filter filter = new com.vaadin.data.util.filter.Compare.Equal(backReferencePropertyId, this.masterEntity);
		 	this.container.addContainerFilter(filter);
		}else{
			Container.Filter filter = new com.vaadin.data.util.filter.IsNull(backReferencePropertyId);
			// is better user property uuid if exits to be sure no record will be found.
			if (propertyList.getPropertyNames().contains("uuid"))
				filter = new com.vaadin.data.util.filter.IsNull("uuid");
			
			this.container.addContainerFilter(filter);
		}
		
		
		if (displayProperties == null){
			displayProperties = PropertiesUtils.get().getTableProperty(referencedType);
			
		}
		
		if (displayProperties == null){
			buildDisplayProperties(referencedType);
		}
		
		 for (String pid : displayProperties) {
			if (pid.contains(".")){
				propertyList.addNestedProperty(pid);
	    		container.addNestedContainerProperty(pid);
			}	
		} 
		
	}
	
	private void buildDisplayProperties(Class<?> referencedType){
		PropertyList<X> propertyList = new PropertyList<X>(MetadataFactory.getInstance()
				.getEntityClassMetadata((Class<X>) referencedType));
		List<String> properties = new ArrayList<String>();
		for (Object pid : container.getContainerPropertyIds()) {
			 if (propertyList.getClassMetadata().getProperty(pid.toString())
					.getAnnotation(Id.class) == null)
				if (propertyList.getClassMetadata().getProperty(pid.toString())
						.getAnnotation(EmbeddedId.class) == null)
					if (propertyList.getPropertyKind(pid.toString()).equals(
							PropertyKind.SIMPLE))
								properties.add(pid.toString());
		}
		
		this.displayProperties = properties.toArray(new String[]{});
		
		
	}
	
	private void buildTable() {
	    this.table = new Table(null, this.container);
	    Object[] visibleProperties = this.displayProperties;

	    if (visibleProperties == null) {
	      List asList = new ArrayList(Arrays.asList(getTable().getVisibleColumns()));

	      asList.remove("id");
	      asList.remove(this.backReferencePropertyId);
	      visibleProperties = asList.toArray();
	    }
	    getTable().setPageLength(5);
	    getTable().setVisibleColumns(visibleProperties);
	    getTable().addActionHandler(this);

	    getTable().setEditable(false);
	    getTable().setSelectable(true);
	    
	    for (Object pid : visibleProperties) {
	    		if(Multilingual.class.isAssignableFrom(propertyList.getPropertyType((String)pid)) ){
					table.setConverter(pid, new MultilingualFieldConvert());
				}
	    		if(MultiValueField.class.isAssignableFrom(propertyList.getPropertyType((String)pid)))
					table.setConverter(pid, new AttributeTypeValueConverter());
				
				String message = I18nUtils.getMessage(getType().getSimpleName().toLowerCase() + "." + pid, null);
				if (message != null)
					table.setColumnHeader(pid, message);
			}	
	    
	   getTable().setTableFieldFactory(new CustomFieldFactory());
	  }
	
	@Override
	public com.vaadin.event.Action[] getActions(Object target, Object sender) {
		return this.actions;
	}

	@Override
	public void handleAction(com.vaadin.event.Action action, Object sender,
			Object target) {
		if(!entityItem.isPersistent())
			return ; //do nothing is not persisted
		
		if (action == this.add)
		      addNew();
		   else if(action == this.modify)
			   modify(target);
		   else   
		      remove(target);
		
	}

	private void remove(Object items) {
	    if (items != null) {
	    	// Collection collection = (Collection)getPropertyDataSource().getValue();
	    	// EntityItem item = this.container.getItem(itemId);
	   
    	  if (items  instanceof Collection) {
				for (Object value : (Collection)items) {
					container.removeItem(value);	
				}
			}else{
				container.removeItem(items);
			}	  
    	  //this.container.removeItem(itemId);
	       //collection.remove(item.getEntity());
	      container.commit(); 
	    }
	  }

	  private EntityComponent<X> buildMainComponent(){
		  MasterForm<X> form = new MasterForm<X>(this.container.getEntityClass()) {
		
		};
		return form;
	  }
	  
	  
	  public EntityFormWindow<X> buildEntityWindow() {
			EntityFormWindow<X> editorWindow =  (EntityFormWindow<X> ) PropertiesUtils.get().getEditBean(container.getEntityClass().getSimpleName());	
			if (editorWindow == null){
				editorWindow = new EntityFormWindow<X>();
										editorWindow.addTab(buildMainComponent(), "main");
			}
			editorWindow.setCaption(I18nUtils.getMessage(container.getEntityClass().getSimpleName() , container.getEntityClass().getSimpleName()));
			return editorWindow;
		}
	  
	  private void addNew()
	  {
	    try {
	    	Class<? extends X> mappedClass = null;
	    	SmcPropertyDef def = PropertiesUtils.get().getPropertyForEntity(entityItem.getEntity().getClass(), propertyId.toString());
	     	if (def != null){
	     		mappedClass =  (Class<? extends X>)def.getMappedClass();
	     	}
	     	if (mappedClass == null)
	     		mappedClass = this.container.getEntityClass();
	     	
	      X newInstance = mappedClass.newInstance();
	      BeanItem beanItem = new BeanItem(newInstance);
	      beanItem.getItemProperty(this.backReferencePropertyId).setValue(this.masterEntity);
	      EntityItem<X> item = this.container.createEntityItem(newInstance);
		 
		  EntityFormWindow<X> editorWindow = buildEntityWindow();
		  editorWindow.setNewItem(true);
	      editorWindow.setReadOnly(false);
	      editorWindow.setEntityItem(item);
		      
	      UI.getCurrent().addWindow(editorWindow);
	   
	    }
	    catch (Exception e)
	    {
	    	e.printStackTrace();
	      Logger.getLogger(super.getClass().getName()).warning("Could not instantiate detail instance " + this.container.getEntityClass().getName());
	    }
	  }
	  
	 
	private void modify(Object items){
		if(items != null){
			Object item = null;
			if (items instanceof Collection){
				item = ((Collection) items).iterator().next();
			}else
				item = items;
			
			 EntityFormWindow<X> editorWindow = buildEntityWindow();
		      editorWindow.setNewItem(false);
		      editorWindow.setReadOnly(false);
		      editorWindow.setEntityItem(this.container.getItem(item));
		      UI.getCurrent().addWindow(editorWindow);
		}
	}
	
	@Override
	protected Component initContent() {
		
		CssLayout vl = new CssLayout();
		vl.setWidth("100%");
	    buildTable();
	    getTable().setWidth("100%");
	    vl.addComponent(getTable());

	    CssLayout buttons = new CssLayout();
	    // disable buttons for new item
	    if(!entityItem.isPersistent() || isReadOnly())
	    	buttons.setEnabled(false);
	    
	    buttons.addComponent(new Button(I18nUtils.getMessage("smc.button.add", "Add"), new Button.ClickListener()
	    {
	      public void buttonClick(Button.ClickEvent event) {
	        addNew();
	      }
	    }));
	    buttons.addComponent(new Button(I18nUtils.getMessage("smc.button.modify", "Modify"), new Button.ClickListener()
	    {
	      public void buttonClick(Button.ClickEvent event) {
	    	  if (getTable().getValue() != null	)
	    		  modify(getTable().getValue());
	      }
	    }));
	    buttons.addComponent(new Button(I18nUtils.getMessage("smc.button.remove", "Remove"), new Button.ClickListener()
	    {
	      public void buttonClick(Button.ClickEvent event) {
	    	  remove(getTable().getValue());
	      }
	    }));
	    
	   // vl.addComponent(buttons);
	    setCaption(this.propertyId.toString());
	    return vl;
	    
	   
	}
	
	@Override
	public Class getType() {
		return entityItem.getItemProperty(propertyId).getType();
	}

	protected  String getMappedByProperty(String propertyName)
	  {
	    OneToMany otm = (OneToMany)getAnnotationForProperty(OneToMany.class, masterEntity.getClass(), propertyName);
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
	    } else{
	    	if (entityClass.getSuperclass() != null )
	    		return getAnnotationFromField(annotationType, entityClass.getSuperclass(), propertyName);
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
	    	  if (entityClass.getSuperclass() != null )
	    		return getAnnotationFromPropertyGetter(annotationType, entityClass.getSuperclass(), propertyName);	  
	    	else
	    		  return getAnnotationFromField(annotationType, getter.getDeclaringClass(), propertyName);
	      }
	    }
	    return null;
	  }

	private Class<?> getMappedClass(){
		
		return null;
	}
	
}
