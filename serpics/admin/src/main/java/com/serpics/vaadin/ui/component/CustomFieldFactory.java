package com.serpics.vaadin.ui.component;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serpics.base.persistence.MultilingualString;
import com.serpics.membership.Member2GroupRelType;
import com.serpics.vaadin.ui.MultilingualStringConvert;
import com.vaadin.addon.jpacontainer.EntityContainer;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerItem;
import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.addon.jpacontainer.metadata.PropertyKind;
import com.vaadin.addon.jpacontainer.provider.ServiceContainerFactory;
import com.vaadin.addon.jpacontainer.util.HibernateUtil;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;

public class CustomFieldFactory extends DefaultFieldFactory{

	private static Logger LOG = LoggerFactory.getLogger(CustomFieldFactory.class);
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
	public Field createField(Container container, Object itemId,
			Object propertyId, Component uiContext) {
		// TODO Auto-generated method stub
		
		Item item = container.getItem(itemId);
		if (item != null)
			return createField(item, propertyId, uiContext);
					
		return super.createField(container, itemId, propertyId, uiContext);		
	}
    @Override
    public Field<?> createField(Item item, Object propertyId, Component uiContext) {
    
    	LOG.info("create field {}" , propertyId);
    	
        if (MultilingualString.class.isAssignableFrom(item.getItemProperty(propertyId).getType())){
        	Field<?> f = super.createField(item, propertyId, uiContext);
            ((TextField) f).setConverter(new MultilingualStringConvert());
            return f;
        }
        
        if (Enum.class.isAssignableFrom(item.getItemProperty(propertyId).getType() )) {
        	Field<?> f = createEnumSelect(item.getItemProperty(propertyId).getType(), propertyId);
        	return f;
        }
        if (item instanceof JPAContainerItem) {
              JPAContainerItem jpaitem = (JPAContainerItem)item;
              EntityContainer container = jpaitem.getContainer();
             PropertyKind kind =  container.getPropertyKind(propertyId);
             if (LOG.isDebugEnabled())
            	 LOG.debug("create fiedl {} pf type {}" , propertyId , kind);
             switch (kind) {
			case ONE_TO_MANY:
				break;
			
			case MANY_TO_ONE:
				return createSelect(propertyId, (JPAContainerItem) item);
			case MANY_TO_MANY:
				break;
			default:
				break;
			}
        }
    	return super.createField(item, propertyId, uiContext);
    }
	
    
    @SuppressWarnings("rawtypes")
	private Field<?> createSelect(Object propertyId , JPAContainerItem item){
    
    	 final ComboBox combo = new ComboBox(propertyId.toString());
         combo.setContainerDataSource(buildcontainer(item.getContainer(), propertyId));
         combo.setItemCaptionMode(ItemCaptionMode.PROPERTY);
         combo.setItemCaptionPropertyId("code");
         combo.setFilteringMode(FilteringMode.CONTAINS);
         combo.setImmediate(true);
         combo.setConverter(new SingleSelectConverter(combo));
         return combo;
    	
    }
    
    private Field<?> createEnumSelect(Class<?> type , Object pid){
    	 final ComboBox combo = new ComboBox(pid.toString());
    	 populateEnums(type, combo);
         combo.setNullSelectionAllowed(false);
         return combo;
    }
    
    private void populateEnums(Class<?> type, AbstractSelect select) {
        List asList = Arrays.asList(type.getEnumConstants());
        for (Object object : asList) {
        	 select.addItem(object);
        }

      }
    
	@SuppressWarnings("rawtypes")
	private JPAContainer buildcontainer( EntityContainer containerForProperty , Object propertyId){
		Class<?> masterEntityClass = containerForProperty.getEntityClass();
		Class<?> referencedType=  containerForProperty.getType(propertyId);
		
		return ServiceContainerFactory.make(referencedType);
	}

}
