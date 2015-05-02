package com.serpics.vaadin.ui.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import com.serpics.vaadin.ui.EntityComponent;
import com.serpics.vaadin.ui.EntityForm;
import com.serpics.vaadin.ui.EntityFormWindow;
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
import com.vaadin.ui.UI;

public class MasterDetailField<T,X> extends CustomField<T> implements Handler {
	private static final long serialVersionUID = -7646209629067140150L;
	private final Action add = new Action("Add");
	private final Action modify = new Action("Modify");
	private final Action remove = new Action("Remove");
	private final Action[] actions = {this.add ,this.modify, this.remove};
	
	private EntityContainer<T> containerForProperty ;
	private EntityContainer<X> container;
	private EntityItem<T> entityItem;
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
	@org.springframework.transaction.annotation.Transactional
	private void buildcontainer(){
		Class<T> masterEntityClass = this.containerForProperty.getEntityClass();
		Class<?> referencedType=  ServiceContainerFactory.detectReferencedType(
				containerForProperty.getEntityProvider().getEntityManagerProvider().getEntityManager().getEntityManagerFactory(),
				this.propertyId, masterEntityClass);
		this.container= (EntityContainer<X>) ServiceContainerFactory.make(referencedType);
		this.backReferencePropertyId = HibernateUtil.getMappedByProperty(this.masterEntity, this.propertyId.toString());
		if (entityItem.isPersistent()){
			Container.Filter filter = new com.vaadin.data.util.filter.Compare.Equal(backReferencePropertyId, this.masterEntity);
		 	this.container.addContainerFilter(filter);
		}else{
			Container.Filter filter = new com.vaadin.data.util.filter.IsNull(backReferencePropertyId);
		 	this.container.addContainerFilter(filter);
		}
		 
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

	    getTable().setEditable(false);
	    getTable().setSelectable(true);
	  //  getTable().setSizeFull();
	   getTable().setTableFieldFactory(new CustomFieldFactory());
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
		   else if(action == this.modify)
			   modify(target);
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

	  private EntityComponent<X> buildMainComponent(){
		  EntityForm<X> form = new EntityForm<X>(this.container.getEntityClass()) {
			  @Override
			public void init() {
				 setReadOnlyProperties(new String[] { "updated", "created" });
	     	}
		};
		return form;
	  }
	  
	  private void addNew()
	  {
	    try {
	      X newInstance = this.container.getEntityClass().newInstance();
	      BeanItem beanItem = new BeanItem(newInstance);
	      beanItem.getItemProperty(this.backReferencePropertyId).setValue(this.masterEntity);
	      EntityItem<X> item = this.container.createEntityItem(newInstance);
		      
		  EntityFormWindow<X> editorWindow = new EntityFormWindow<X>();
	      editorWindow.setNewItem(true);
	      editorWindow.setReadOnly(false);
	      editorWindow.addTab(buildMainComponent(), "main");
	      editorWindow.setEntityItem(item);
		      
	      UI.getCurrent().addWindow(editorWindow);
	   
	    }
	    catch (Exception e)
	    {
	    	e.printStackTrace();
	      Logger.getLogger(super.getClass().getName()).warning("Could not instantiate detail instance " + this.container.getEntityClass().getName());
	    }
	  }
	  
	 
	private void modify(Object item){
		if(item != null){
			 EntityFormWindow<X> editorWindow = new EntityFormWindow<X>();
		      editorWindow.setNewItem(false);
		      editorWindow.setReadOnly(false);
		      editorWindow.addTab(buildMainComponent(), "main");
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
	    if(!entityItem.isPersistent())
	    	buttons.setEnabled(false);
	    
	    buttons.addComponent(new Button("Add", new Button.ClickListener()
	    {
	      public void buttonClick(Button.ClickEvent event) {
	        addNew();
	      }
	    }));
	    buttons.addComponent(new Button("Modify", new Button.ClickListener()
	    {
	      public void buttonClick(Button.ClickEvent event) {
	    	  if (getTable().getValue() != null	)
	    		  modify(getTable().getValue());
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
		return entityItem.getItemProperty(propertyId).getType();
	}

}
