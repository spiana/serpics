package com.serpics.vaadin.ui.component;

import com.serpics.base.persistence.MultilingualString;
import com.serpics.vaadin.ui.MultilingualStringConvert;
import com.vaadin.addon.jpacontainer.EntityContainer;
import com.vaadin.addon.jpacontainer.JPAContainerItem;
import com.vaadin.addon.jpacontainer.metadata.PropertyKind;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;

public class CustomFieldFactory extends DefaultFieldFactory{

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
    public Field<?> createField(Item item, Object propertyId, Component uiContext) {
    
        if (MultilingualString.class.isAssignableFrom(item.getItemProperty(propertyId).getType())){
        	Field<?> f = super.createField(item, propertyId, uiContext);
            ((TextField) f).setConverter(new MultilingualStringConvert());
            return f;
        }
    	
//        if (item instanceof JPAContainerItem) {
//              JPAContainerItem jpaitem = (JPAContainerItem)item;
//              EntityContainer container = jpaitem.getContainer();
//             PropertyKind kind =  container.getPropertyKind(propertyId);
//             switch (kind) {
//			case ONE_TO_MANY:
//				
//				break;
//
//			default:
//				break;
//			}
//        }
    	return super.createField(item, propertyId, uiContext);
    }
	
//	@Override
//	protected JPAContainer<?> createJPAContainerFor(
//			EntityContainer<?> referenceContainer, Class<?> type,
//			boolean buffered) {
//		
//		return ServiceContainerFactory.make(type);
//		
//	}
//	@Override
//	protected EntityManagerFactory getEntityManagerFactory(
//			EntityContainer<?> containerForProperty) {
//		return containerForProperty.getEntityProvider().getEntityManager().getEntityManagerFactory();
//	}
}
