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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serpics.vaadin.data.utils.PropertiesUtils;
import com.serpics.vaadin.ui.component.CustomFieldFactory;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.metadata.MetadataFactory;
import com.vaadin.addon.jpacontainer.metadata.PropertyKind;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

public abstract class SearchForm<T> extends FormLayout{
	private static transient Logger LOG = LoggerFactory.getLogger(SearchForm.class);
	
	private static final long serialVersionUID = 87868509261251877L;
	private BeanFieldGroup<T> formFiledBinding;
	private transient PropertyList<T> propertyList;	
	protected transient Class<T> entityClass;
	protected transient EntityItem<T> item ;
	
	private transient String[] searchProperties;
	
	public SearchForm(Class<T> entityClass , EntityItem<T> item) {
		super();
		this.entityClass = entityClass;
		this.item = item;
		this.propertyList = new PropertyList<T>(MetadataFactory.getInstance()
				.getEntityClassMetadata(entityClass));
		searchProperties = PropertiesUtils.get().getSearchProperty(entityClass.getSimpleName());
		
		if (searchProperties == null){
			this.searchProperties = buildDisplayProperties(entityClass);
		}
		buildContent();
	}
	
	private String[] buildDisplayProperties(Class<?> referencedType){
		List<String> properties = new ArrayList<String>();
		for (Object pid : propertyList.getAllAvailablePropertyNames()) {
			 if (propertyList.getClassMetadata().getProperty(pid.toString())
					.getAnnotation(Id.class) == null)
				if (propertyList.getClassMetadata().getProperty(pid.toString())
						.getAnnotation(EmbeddedId.class) == null)
					if (propertyList.getPropertyKind(pid.toString()).equals(
							PropertyKind.SIMPLE))
								properties.add(pid.toString());
		}
		
		return properties.toArray(new String[]{});
		
		
	}
	
	private T createEmptyInstance(){
		try {
			return (T) entityClass.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private void buildContent(){
		formFiledBinding = new BeanFieldGroup<T>(entityClass);
		formFiledBinding.setItemDataSource(createEmptyInstance());
		for (String pid : searchProperties) {
			if(pid.contains("."))
				propertyList.addNestedProperty(pid);
			
			HorizontalLayout h = new HorizontalLayout();
			h.addComponent(new Label(pid));
			ComboBox c = new ComboBox("", Arrays.asList(new String [] {"iniza con" , "contiene" , "finisce con"}));
			Field<?> field = createField(pid ,h);
			field.setCaption("");
			h.addComponent(c);
			h.addComponent(field);
			formFiledBinding.bind(field, pid);
			h.setWidth("100%");
			addComponent(h);
			
		}
		Button search= new Button("search");
		search.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				search();
			}
		});
		
		addComponent(search);
		
	}
	protected Field<?> createField(final String pid , Component uicontext) {
		@SuppressWarnings("rawtypes")
		final Property p = item.getItemProperty(pid);
		LOG.info("create field : {}", pid);
		final Field<?> f = CustomFieldFactory.get().createField(item,
				pid, uicontext);
		f.setBuffered(true);

		if (f instanceof TextField) {
			((TextField) f).setNullRepresentation("");
		}
		
		f.addValidator(new BeanValidator(entityClass, pid));
		
		if (String.class.isAssignableFrom(p.getType())) {
			f.setWidth("80%");
		}
		if (Number.class.isAssignableFrom(p.getType())) {
			f.setWidth("30%");
		}
		return f;
	}
	
	public void search() {
		try {
			formFiledBinding.commit();
		} catch (CommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
