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
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serpics.base.MultiValueField;
import com.serpics.base.Multilingual;
import com.serpics.base.data.model.Media;
import com.serpics.base.data.model.MultilingualText;
import com.serpics.vaadin.data.utils.PropertiesUtils;
import com.serpics.vaadin.jpacontainer.ServiceContainerFactory;
import com.serpics.vaadin.ui.converters.AttributeTypeDateConverter;
import com.serpics.vaadin.ui.converters.AttributeTypeDoubleConverter;
import com.serpics.vaadin.ui.converters.AttributeTypeIntegerConverter;
import com.serpics.vaadin.ui.converters.AttributeTypeStringConverter;
import com.vaadin.addon.jpacontainer.EntityContainer;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerItem;
import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.addon.jpacontainer.metadata.PropertyKind;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;

public class CustomFieldFactory extends DefaultFieldFactory{

	private static Logger LOG = LoggerFactory.getLogger(CustomFieldFactory.class);
	private static transient String FIELD_WIDTH = "90%";
	/**
	 * 
	 */
	private static final long serialVersionUID = -3915549627883442078L;
	
	private static final  CustomFieldFactory instance = new CustomFieldFactory();

    /**
     * Singleton method to get an instance of CustomFieldFactory.
     * 
     * @return an instance of CustomFieldFactory
     */
    public static CustomFieldFactory get() {
        return instance;
    }
    
    
	@Override
	public Field createField(Container container, Object itemId, Object propertyId, Component uiContext) {
		
		Item item = container.getItem(itemId);
		if (item != null)
			return createField(item, propertyId, uiContext);
					
		return super.createField(container, itemId, propertyId, uiContext);		
	}
    @SuppressWarnings("rawtypes")
	@Override
    public Field<?> createField(Item item, Object propertyId, Component uiContext) {
    
    	
    	final Property p = item.getItemProperty(propertyId);
    	if (p != null)
    		LOG.debug("create field for property {} of type {}" , propertyId , p.getType() );
    	else{
    		LOG.warn ("property {} not found !" , propertyId);
    		return null;
    	}
    	
        if (Multilingual.class.isAssignableFrom(item.getItemProperty(propertyId).getType())){
        	if (MultilingualText.class.isAssignableFrom(item.getItemProperty(propertyId).getType())){
        		MultilingualRichTextField f = new MultilingualRichTextField();
        		f.setWidth(FIELD_WIDTH);
        		return f;
        	}else{
	        	MultilingualTextField f =  new MultilingualTextField();
	            f.setWidth(FIELD_WIDTH);
	            return f;
        	}
        }
        
        if (MultiValueField.class.isAssignableFrom(item.getItemProperty(propertyId).getType())){
        	Field<?> f = createMultiValueAttribute(item, propertyId);
        	return f;
        }
        
        if (Enum.class.isAssignableFrom(item.getItemProperty(propertyId).getType() )) {
        	Field<?> f = createEnumSelect(item.getItemProperty(propertyId).getType(), propertyId);
        	f.setWidth(FIELD_WIDTH);
        	return f;
        }
        
        
        	
        if (item instanceof JPAContainerItem) {
              JPAContainerItem jpaitem = (JPAContainerItem)item;
              EntityContainer container = jpaitem.getContainer();
              if (propertyId.toString().contains("."))
            	  container.addNestedContainerProperty(propertyId.toString());
              
              if (Media.class.isAssignableFrom(item.getItemProperty(propertyId).getType())){
              	MediaSelect f = new MediaSelect(jpaitem, propertyId);
          		f.setWidth(FIELD_WIDTH);
          		return f;
              }

              
             PropertyKind kind =  container.getPropertyKind(propertyId);
             if (LOG.isDebugEnabled())
            	 LOG.debug("create fiedl {} pf type {}" , propertyId , kind);
             switch (kind) {
             case ONE_TO_ONE:
            	 return createOne2OneForm(jpaitem, propertyId);
        	case ONE_TO_MANY:
				return createOneToMany(propertyId, jpaitem);
			case MANY_TO_ONE:
				return createSelect(propertyId, jpaitem);
			case EMBEDDED:
				return createEmbeddedField(jpaitem, propertyId);	
			case MANY_TO_MANY:
				break;
			default:
				break;
			}
        }
        
     
    	Field<?> f = super.createField(item, propertyId, uiContext);
    	if (String.class.isAssignableFrom(p.getType())) {
			f.setWidth(FIELD_WIDTH);
			 ((TextField) f).setNullRepresentation("");
		}
		if (Number.class.isAssignableFrom(p.getType())) {
			f.setWidth("30%");
			 ((TextField) f).setNullRepresentation("");
		}
	
		
    	return f;
    }
	
    
    @SuppressWarnings("rawtypes")
	private Field<?> createSelect(Object propertyId , JPAContainerItem item){
    	 final ComboBox combo = new ComboBox(propertyId.toString());
    	 JPAContainer referencedContainer =buildcontainer(item.getContainer(), propertyId);
    	 String referencedPropertyId = buildReferencedPropertyToShow(item.getContainer(), propertyId);
    	 if (referencedPropertyId.contains("."))
    		 referencedContainer.addNestedContainerProperty(referencedPropertyId);
    	 
    	 item.getItemProperty(propertyId).getType();
  
    	 combo.setContainerDataSource(referencedContainer);
         combo.setItemCaptionMode(ItemCaptionMode.PROPERTY);
         combo.setItemCaptionPropertyId(referencedPropertyId);
         combo.setFilteringMode(FilteringMode.CONTAINS);
         combo.setImmediate(true);
         combo.setConverter(new SingleSelectConverter(combo));
         combo.setWidth(FIELD_WIDTH);
         
         return combo;
    	
    }
    
    private MasterDetailField createOneToMany(Object propertyId , JPAContainerItem item){
    	MasterDetailField t = 
    			new MasterDetailField(item.getContainer(), item, propertyId);
    	t.setWidth(FIELD_WIDTH);
    	return t;
    }
    
    private Field<?> createEnumSelect(Class<?> type , Object pid){
    	 final ComboBox combo = new ComboBox(pid.toString());
    	 populateEnums(type, combo);
         combo.setNullSelectionAllowed(false);
         combo.setWidth(FIELD_WIDTH);
         return combo;
    }
    
    private void populateEnums(Class<?> type, AbstractSelect select) {
        List asList = Arrays.asList(type.getEnumConstants());
        for (Object object : asList) {
        	 select.addItem(object);
        }

      }
    
    private EmbeddedField createEmbeddedField(EntityItem item , Object pid){
    	return new EmbeddedField(item, pid);
    }
    private One2oneField createOne2OneForm(EntityItem item, Object pid){
    	return new One2oneField(item , pid);
    }
	@SuppressWarnings("rawtypes")
	private JPAContainer buildcontainer( EntityContainer containerForProperty , Object propertyId){
		Class<?> masterEntityClass = containerForProperty.getEntityClass();
		Class<?> referencedType=  containerForProperty.getType(propertyId);
		
		return ServiceContainerFactory.make(referencedType);
	}
	
	@SuppressWarnings("rawtypes")
	private String buildReferencedPropertyToShow( EntityContainer containerForProperty , Object propertyId){
		Class<?> referencedType=  containerForProperty.getType(propertyId);
		String referencedProperty = PropertiesUtils.get().getSelectProperty(referencedType.getSimpleName());
		
		if (referencedProperty == null)
			referencedProperty = "uuid"; // this is default
		
		return referencedProperty;
	}
	
	private Field<?> createMultiValueAttribute(Item item , Object propertyId){
			Field<?> field;
			
			//if entity is null return default Field
			if (((EntityItem)item).getEntity() == null){
				field = createFieldByPropertyType(item.getItemProperty(propertyId).getType());
				((AbstractField) field).setConverter(new AttributeTypeStringConverter(field));
				field.setCaption(createCaptionByPropertyId(propertyId));
				return field;
			}
			
			MultiValueField _f = (MultiValueField)((EntityItem)item).getItemProperty(propertyId).getValue();
			
			if (_f == null){
				field = createFieldByPropertyType(item.getItemProperty(propertyId).getType());
				((AbstractField) field).setConverter(new AttributeTypeStringConverter());
				field.setCaption(createCaptionByPropertyId(propertyId));
				return field;
			}
				
			switch (_f.getAttributeType()) {
			case STRING:
				field = createFieldByPropertyType(String.class);
				((AbstractField) field).setConverter(new AttributeTypeStringConverter(field));
				break;
			case INTEGER:
				field = createFieldByPropertyType(Integer.class);
				((AbstractField) field).setConverter(new AttributeTypeIntegerConverter(field));
				break;
			case DOUBLE:
				field = createFieldByPropertyType(Double.class);
				((AbstractField) field).setConverter(new AttributeTypeDoubleConverter(field));
				break;
			case DATE:
				field = createFieldByPropertyType(Date.class);
				((AbstractField) field).setConverter(new AttributeTypeDateConverter(field));
				break;
		
			default:
				field = createFieldByPropertyType(String.class);
				((AbstractField) field).setConverter(new AttributeTypeStringConverter(field));
				break;
			}
			if (TextField.class.isAssignableFrom(field.getClass()))
				((TextField) field).setNullRepresentation("");
			
			field.setCaption(createCaptionByPropertyId(propertyId));
			
			return field;
	}

}
