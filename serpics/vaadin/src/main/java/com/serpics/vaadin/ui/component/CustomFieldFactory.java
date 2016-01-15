package com.serpics.vaadin.ui.component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serpics.base.MultiValueField;
import com.serpics.base.Multilingual;
import com.serpics.vaadin.data.utils.PropertiesUtils;
import com.serpics.vaadin.jpacontainer.ServiceContainerFactory;
import com.serpics.vaadin.ui.converters.AttributeTypeDateConverter;
import com.serpics.vaadin.ui.converters.AttributeTypeDoubleConverter;
import com.serpics.vaadin.ui.converters.AttributeTypeIntegerConverter;
import com.serpics.vaadin.ui.converters.AttributeTypeStringConverter;
import com.serpics.vaadin.ui.converters.MultilingualFieldConvert;
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
import com.vaadin.ui.ComboBox;
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
	
	private static final CustomFieldFactory instance = new CustomFieldFactory();

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
    @Override
    public Field<?> createField(Item item, Object propertyId, Component uiContext) {
    
    	LOG.debug("create field {}" , propertyId);
    	
        if (Multilingual.class.isAssignableFrom(item.getItemProperty(propertyId).getType())){
        	Field<?> f = super.createField(item, propertyId, uiContext);
            ((TextField) f).setConverter(new MultilingualFieldConvert());
            f.setWidth(FIELD_WIDTH);
            ((TextField) f).setNullRepresentation("");
            return f;
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
			case MANY_TO_MANY:
				break;
			default:
				break;
			}
        }
        
        final Property p = item.getItemProperty(propertyId);
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
    	t.setWidth("90%");
    	return t;
    }
    
    private Field<?> createEnumSelect(Class<?> type , Object pid){
    	 final ComboBox combo = new ComboBox(pid.toString());
    	 populateEnums(type, combo);
         combo.setNullSelectionAllowed(false);
         combo.setWidth("90%");
         return combo;
    }
    
    private void populateEnums(Class<?> type, AbstractSelect select) {
        List asList = Arrays.asList(type.getEnumConstants());
        for (Object object : asList) {
        	 select.addItem(object);
        }

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
			referencedProperty = "code"; // this is default
		
		return referencedProperty;
	}
	
	private Field<?> createMultiValueAttribute(Item item , Object propertyId){
			Field<?> field;
			
			//if entity is null return default Field
			if (((EntityItem)item).getEntity() == null){
				field = createFieldByPropertyType(item.getItemProperty(propertyId).getType());
				((AbstractField) field).setConverter(new AttributeTypeStringConverter());
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
				((AbstractField) field).setConverter(new AttributeTypeStringConverter());
				break;
			case INTEGER:
				field = createFieldByPropertyType(Integer.class);
				((AbstractField) field).setConverter(new AttributeTypeIntegerConverter());
				break;
			case DOUBLE:
				field = createFieldByPropertyType(Double.class);
				((AbstractField) field).setConverter(new AttributeTypeDoubleConverter());
				break;
			case DATE:
				field = createFieldByPropertyType(Date.class);
				((AbstractField) field).setConverter(new AttributeTypeDateConverter());
				break;
		
			default:
				field = createFieldByPropertyType(String.class);
				((AbstractField) field).setConverter(new AttributeTypeStringConverter());
				break;
			}
			
			field.setCaption(createCaptionByPropertyId(propertyId));
			return field;
	}

}
