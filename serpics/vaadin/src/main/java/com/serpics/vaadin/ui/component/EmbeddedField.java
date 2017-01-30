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


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serpics.vaadin.data.utils.I18nUtils;
import com.serpics.vaadin.data.utils.PropertiesUtils;
import com.serpics.vaadin.ui.PropertyList;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.metadata.MetadataFactory;
import com.vaadin.addon.jpacontainer.metadata.PersistentPropertyMetadata.AccessType;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;

public class EmbeddedField<M ,T> extends CustomField<T> {
	private static final long serialVersionUID = -6909188345491634488L;
	private static transient Logger LOG = LoggerFactory
			.getLogger(EmbeddedField.class);
	private transient PropertyList<T> propertyList;
	private transient String[] displayProperties;
	private transient final Set<String> hideProperties = new HashSet<String>(0);
	private transient Set<String> readOnlyProperties  = new HashSet<String>(0);
	private EntityItem<T> entityItem;
	private transient EntityItem<M> masterEntity;
	private transient Object parentPropertyId;
	

	private FieldGroup fieldGroup ;

	@SuppressWarnings("unchecked")
	public EmbeddedField(EntityItem<M> item, Object parentPropertyId) {
		this.fieldGroup = new FieldGroup();
		this.fieldGroup.setBuffered(true);
		
		this.masterEntity = item;
		this.parentPropertyId = parentPropertyId;
	}

	@SuppressWarnings("unused")
	private T createEntity() {

		T item = null;
        try {
        	Class<? extends T> entityClass =getType();
            item = entityClass.newInstance();
        } catch (final InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
        return item;
    }
	
	private void buildContent(FormLayout layout) {
		
		
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
			if (!hideProperties.contains(pid))
				layout.addComponent(createField(pid));
		}

	}

	protected Field<?> createField(final String pid) {
	
		LOG.debug("create field : {}", pid);
		String _pid = parentPropertyId + "." +pid;
	
		final Field<?> f = CustomFieldFactory.get().createField( entityItem,
				_pid, this);
	
		fieldGroup.bind(f, _pid);
		f.setBuffered(true);
		f.addValidator(new BeanValidator(masterEntity.getEntity().getClass(), _pid));
		if (isReadOnly())
			f.setReadOnly(true);
		String message = I18nUtils.getMessage(getType().getSimpleName().toLowerCase() +"." + pid , pid);
		if (message != null)
			f.setCaption(message);
		PropertiesUtils.get().setFieldProperty(getType(), pid, f , !entityItem.isPersistent());
		
		return f;
	}

	@Override
	protected Component initContent() {
		FormLayout layout = new FormLayout();
		layout.setCaption(parentPropertyId.toString());
		
		propertyList = new PropertyList<T>(MetadataFactory.getInstance()
				.getClassMetadata((Class<T>) getType() , AccessType.FIELD));
		
	//	propertyList.addNestedProperty(parentPropertyId.toString()+".*");
	//	masterEntity.addNestedContainerProperty(parentPropertyId.toString()+".*");
		
		T value = (T) masterEntity.getItemProperty(parentPropertyId).getValue();
	
		if (value == null){
			 T item = createEntity();
			 masterEntity.getItemProperty(parentPropertyId).setValue(item);
		}
		
		this.entityItem = (EntityItem<T>) masterEntity.getItemProperty(parentPropertyId).getItem();
		this.entityItem.addNestedContainerProperty(parentPropertyId+".*");
		
		//BeanItem<T> b = new BeanItem<T>(this.entityItem);
		fieldGroup.setItemDataSource(this.entityItem);
		fieldGroup.setBuffered(true);		
			
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
			// masterEntity.getItemProperty(parentPropertyId).setValue(entityItem);
             masterEntity.commit();
		//	 entityItem.getContainer().commit();
			 
		} catch (CommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
