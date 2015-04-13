package com.serpics.vaadin.ui.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import com.vaadin.addon.jpacontainer.EntityContainer;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.provider.ServiceContainerFactory;
import com.vaadin.addon.jpacontainer.util.HibernateUtil;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Table;

public class MasterDetailField<T,X> extends CustomField<T> implements Handler {

	private final Action add = new Action("Add");
	private final Action remove = new Action("Remove");
	private final Action[] actions = {this.add , this.remove};
	
	private EntityContainer<T> containerForProperty ;
	private EntityContainer<X> container;
	private Object itemId;
	private Object propertyId;
	private Object backReferencePropertyId;
	private T masterEntity;
	private Table table;
	private String[] displayProperties;

	public Table getTable() {
		return table;
	}

	public MasterDetailField(
			EntityContainer<T> entityContainer,
			Object itemId, Object propertyId ,String[] displayProperties) {
		super();
		setBuffered(true);
		this.containerForProperty = entityContainer;
		this.itemId = itemId;
		this.propertyId = propertyId;
		this.masterEntity = entityContainer.getItem(itemId).getEntity();
		this.displayProperties = displayProperties;
		buildcontainer();
		
		
	}

	@SuppressWarnings("unchecked")
	@org.springframework.transaction.annotation.Transactional
	private void buildcontainer(){
		Class<T> masterEntityClass = this.containerForProperty.getEntityClass();
		Class<?> referencedType=  ServiceContainerFactory.detectReferencedType(
				containerForProperty.getEntityProvider().getEntityManagerProvider().getEntityManager().getEntityManagerFactory(),
				this.propertyId, masterEntityClass);
		this.container= (EntityContainer<X>) ServiceContainerFactory.make(referencedType);
		this.backReferencePropertyId = HibernateUtil.getMappedByProperty(this.masterEntity, this.propertyId.toString());
		Container.Filter filter = new com.vaadin.data.util.filter.Compare.Equal(backReferencePropertyId, this.masterEntity);
		 this.container.addContainerFilter(filter);
		 
		 for (String pid : displayProperties) {
			if (pid.contains(".")){
				//String _pid = pid.split(".")[0]+".*";
				container.addNestedContainerProperty(pid);
			}	
		} 
		
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

	    getTable().setTableFieldFactory(CustomFieldFactory.get());
	    getTable().setEditable(true);
	    getTable().setSelectable(true);
	  }
	
	@Override
	public com.vaadin.event.Action[] getActions(Object target, Object sender) {
		return this.actions;
	}

	@Override
	public void handleAction(com.vaadin.event.Action action, Object sender,
			Object target) {
		if (action == this.add)
		      addNew();
		    else
		      remove(target);
		
	}

	private void remove(Object itemId) {
	    if (itemId != null) {
	      Collection collection = (Collection)getPropertyDataSource().getValue();

	      EntityItem item = this.container.getItem(itemId);
	      item.getItemProperty(this.backReferencePropertyId).setValue(null);
	      this.container.removeItem(itemId);
	      
	       collection.remove(item.getEntity());
	    }
	  }

	  private void addNew()
	  {
	    try {
	      X newInstance = this.container.getEntityClass().newInstance();
	      BeanItem beanItem = new BeanItem(newInstance);
	      beanItem.getItemProperty(this.backReferencePropertyId).setValue(this.masterEntity);

	      this.container.addEntity(newInstance);
	      if (!(isBuffered()));
	    }
	    catch (Exception e)
	    {
	      Logger.getLogger(super.getClass().getName()).warning("Could not instantiate detail instance " + this.container.getEntityClass().getName());
	    }
	  }
	  
	 
	@Override
	protected Component initContent() {
		CssLayout vl = new CssLayout();
	    buildTable();
	    vl.addComponent(getTable());

	    CssLayout buttons = new CssLayout();
	    buttons.addComponent(new Button("Add", new Button.ClickListener()
	    {
	      public void buttonClick(Button.ClickEvent event) {
	        addNew();
	      }
	    }));
	    buttons.addComponent(new Button("Remove", new Button.ClickListener()
	    {
	      public void buttonClick(Button.ClickEvent event) {
	    	  remove(getTable().getValue());
	      }
	    }));
	    vl.addComponent(buttons);
	    return vl;
	}

	
	@Override
	public Class getType() {
		return this.containerForProperty.getItem(itemId).getItemProperty(propertyId).getType();
	}

}
