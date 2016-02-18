package com.serpics.vaadin.ui;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.serpics.vaadin.jpacontainer.ServiceContainerFactory;
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
        Object backReferencePropertyId = getMappedByProperty();
        DETAIL value = (DETAIL) masterEntity.getItemProperty(parentPropertyId).getValue();
		JPAContainer<DETAIL> container = ServiceContainerFactory.make(masterEntity
					.getItemProperty(parentPropertyId).getType());
		if (value == null){
			this.entityItem = createEntityItem(container);
			//if (entityItem.getItemProperty(backReferencePropertyId) != null)
			//	this.entityItem.getItemProperty(backReferencePropertyId).setValue(masterEntity.getEntity());
		}else{
			this.entityItem = container.getItem(container.getEntityProvider()
				.getIdentifier(value));
		}
        buildContent();
    }
    
    
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

    @SuppressWarnings("unchecked")
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
    
    protected  String getMappedByProperty()
	  {
    	return StringUtils.uncapitalize(masterEntity.getEntity().getClass().getSimpleName());
	  }

    
	  	@SuppressWarnings("unchecked")
		public Class<? extends DETAIL> getType() {
			return masterEntity.getItemProperty(parentPropertyId).getType();
		}
}
