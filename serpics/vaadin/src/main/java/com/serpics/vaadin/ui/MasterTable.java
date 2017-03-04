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
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serpics.base.MultiValueField;
import com.serpics.base.Multilingual;
import com.serpics.vaadin.data.utils.I18nUtils;
import com.serpics.vaadin.data.utils.PropertiesUtils;
import com.serpics.vaadin.jpacontainer.ServiceContainerFactory;
import com.serpics.vaadin.ui.EntityComponent.MasterTableComponent;
import com.serpics.vaadin.ui.component.FilterComponent;
import com.serpics.vaadin.ui.converters.AttributeTypeValueConverter;
import com.serpics.vaadin.ui.converters.MultilingualFieldConvert;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.metadata.MetadataFactory;
import com.vaadin.addon.jpacontainer.metadata.PropertyKind;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Table;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

public abstract class MasterTable<T> extends CustomComponent implements MasterTableComponent<T> {

	private static Logger LOG = LoggerFactory.getLogger(MasterTable.class);
	
	private static final long serialVersionUID = 8614651463123352933L;

	private boolean initialized = false;

	@Transient
	private transient final Class<T> entityClass;
	@Transient
	private transient PropertyList<T> propertyList;

	private String[] displayProperties;
	@SuppressWarnings("unused")
	private final Set<String> hideProperties = new HashSet<String>();
	private boolean editable = true;
	private boolean searchFormEnable =true;
	
	private String[] searchProperties;
	private MasterTableListner masterTableListner;
	protected Table entityList;

	protected final HorizontalLayout editButtonPanel = new HorizontalLayout();
	protected final HorizontalLayout searchPanel = new HorizontalLayout();
	protected final HorizontalLayout filterPanel = new HorizontalLayout();
	protected FilterComponent<T> filterComponent;
	protected Hashtable<String, String> ht;
	protected MenuBar menuF = new MenuBar();
	
	protected Button newButton ;
	protected Button editButton ;
	protected Button deleteButton ;

	protected transient JPAContainer<T> container;
	
	public Map<String ,FilterComponent<T>> filterMap = new HashMap<String, FilterComponent<T>>();

	public MasterTable(final Class<T> entityClass) {
		super();
		this.entityClass = entityClass;
		this.propertyList = new PropertyList<T>(MetadataFactory.getInstance().getEntityClassMetadata(entityClass));
		
		addAttachListener(new AttachListener() {
			
			@Override
			public void attach(AttachEvent event) {

				if (!initialized) {
					buildContainer();
					buildContent();
					initialized = true;
				}

				
				if (container != null)
					container.refresh();
			}
		});
	}

	@Override
	public Class<T> getEntityType() {
		return this.entityClass;
	}

	@Override
	public void init() {
		if (!initialized) {
			buildContainer();
			buildContent();
			initialized = true;
		}
	}

	protected void buildContainer() {
		if (container == null) {
			container = ServiceContainerFactory.make(entityClass);
		}

	}

	@SuppressWarnings("serial")
	public EntityFormWindow<T> buildEntityWindow() {
		EntityFormWindow<T> editorWindow =  (EntityFormWindow<T> ) PropertiesUtils.get().getEditBean(entityClass.getSimpleName());	
		if (editorWindow == null){
			editorWindow = new EntityFormWindow<T>(entityClass.getSimpleName());
			editorWindow.addTab(new MasterForm<T>(entityClass) {}, I18nUtils.getMessage(entityClass.getSimpleName(),"main"));
		}
		editorWindow.setCaption(entityClass.getSimpleName());
		return editorWindow;
	}
	
	@SuppressWarnings("serial")
	public CustomModalComponent<T> buildSearchForm() {
		CustomModalComponent<T> editorWindow = new CustomModalComponent<T>();
		editorWindow.addTab(new AdvanceSearchForm<T>(entityClass) {

		}, entityClass.getSimpleName());
		return editorWindow;
	}

	@SuppressWarnings({ "static-access", "serial" })
	protected void buildContent() {
		
		this.entityList = new Table();
		entityList.setSelectable(true);
		entityList.setImmediate(true);
		entityList.setMultiSelect(true);
		entityList.setSizeFull();
		entityList.setColumnCollapsingAllowed(true);
		entityList.setColumnReorderingAllowed(true);
		entityList.setContainerDataSource(this.container);

		if(this.searchProperties==null)
			this.searchProperties = PropertiesUtils.get().getSearchProperty(this.entityClass);

		
		if (this.displayProperties == null)
			this.displayProperties = PropertiesUtils.get().getTableProperty(this.entityClass);

		if (this.displayProperties == null)
			this.displayProperties = buildDisplayProperties();
		
		if (this.displayProperties != null) {
			initializeDisplayProperties(this.displayProperties);
			entityList.setVisibleColumns(displayProperties);

		}

		final VerticalLayout v = new VerticalLayout();
		v.setSizeFull();
		this.editButtonPanel.setWidth("100%");
		this.editButtonPanel.setDefaultComponentAlignment(Alignment.BOTTOM_LEFT);
		this.editButtonPanel.setEnabled(isEnabled());

		v.addComponent(editButtonPanel);
		
    	filterPanel.setEnabled(false); 
		filterPanel.setVisible(false);
		filterPanel.setDefaultComponentAlignment(Alignment.BOTTOM_LEFT);
		filterPanel.setStyleName("wrapping");
		
		v.addComponent(filterPanel);
		
		
		v.addComponent(entityList);

		v.setExpandRatio(entityList, 1);

		entityList.addValueChangeListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(final com.vaadin.data.Property.ValueChangeEvent event) {
				if (event.getProperty().getValue() == null)
					return;
			}
		});
		
		entityList.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            private static final long serialVersionUID = 2068314108919135281L;

            public void itemClick(ItemClickEvent event) {
            	 if (event.isDoubleClick()) {
                 	if (entityList.getValue() == null)
     					return;
                 	
     				if (!entityList.isEditable()) {					
     					modify(entityList.getValue());
     				}
                 	
                 }
            }
        });
		

		newButton = new Button(I18nUtils.getMessage("smc.button.add", "Add"));
		editButtonPanel.addComponent(newButton);

		newButton.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(final ClickEvent event) {
					add();
			}
		});

		editButton = new Button(I18nUtils.getMessage("smc.button.modify", "Modify"));
		editButtonPanel.addComponent(editButton);

		editButton.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(final ClickEvent event) {
				if (entityList.getValue() == null)
					return;
				 modify(entityList.getValue());
			}
		});
		
		final Button _advanceSearch = new Button(I18nUtils.getMessage("smc.button.advanceSearch", "Advance Search"));
		_advanceSearch.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(final ClickEvent event) {
				if (!entityList.isEditable()) {
					CustomModalComponent<T> editorWindow = buildSearchForm();				
					editorWindow.setNewItem(true);
					editorWindow.setReadOnly(false);
					editorWindow.setEntityItem(createEntityItem());					
					UI.getCurrent().addWindow(editorWindow);
				} else {
					// createEntityItem();
					// entityList.refreshRowCache();
				}
			}
		});
	
	    

		deleteButton = new Button(I18nUtils.getMessage("smc.button.remove", "Remove"));		
		
		deleteButton.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
				public void buttonClick(final ClickEvent event) {
					if (entityList.getValue() == null)
						return;
					delete(entityList.getValue());
			}
		});
		
		editButtonPanel.addComponent(deleteButton);	
		
		if(searchFormEnable){
			final TextField serchField = (TextField) masterTableListner.get().buildFilterField();				
			masterTableListner.get().filterAllContainerJPA(container, serchField, this.searchProperties);
			serchField.setWidth("100%");
			searchPanel.addComponent(serchField);
			searchPanel.setExpandRatio(serchField, 1F);
			//editButtonPanel.addComponent(_advanceSearch);
	    }
		searchPanel.setWidth("70%");
		editButtonPanel.addComponent(searchPanel);
		editButtonPanel.setExpandRatio(searchPanel, 0.70F);

		String[] filterProperties = null;
		//if(searchProperties == null) 
			filterProperties = buildDisplayProperties();
	//	else 
		//	filterProperties = searchProperties;
		
		MenuItem filtermenu = menuF.addItem(I18nUtils.getMessage("filter.filter", "Filter"), null);	
		ht = new Hashtable<String, String>();
		for (String property : filterProperties) { 
			String desc = I18nUtils.getMessage(container.getEntityClass().getSimpleName().toLowerCase() + "." +property,property);
			ht.put(desc, property);
			MenuItem filterItem = filtermenu.addItem(desc, addFilterComponent);
			filterItem.setCheckable(true);
		}
		
		filtermenu.addSeparator();
		
		MenuItem clear = filtermenu.addItem(I18nUtils.getMessage("filter.clear","clear") , clearCommand);
		
		clear.setCheckable(false);
		
		editButtonPanel.addComponent(menuF);

		setCompositionRoot(v);
		setSizeFull();
	}

	private Command clearCommand = new Command() {
        public void menuSelected(MenuItem selectedItem) {
        	List<MenuItem> li = selectedItem.getParent().getChildren();
        	for (MenuItem mi : li) {
				if(mi.isChecked()) mi.setChecked(false);
			}
        	filterPanel.removeAllComponents();
        	filterPanel.setEnabled(false); 
        	filterPanel.setVisible(false);
        	removeAllFilter();
        }
    };
    
    private Command addFilterComponent = new Command() {
    	public void menuSelected(MenuItem selectedItem) {
    		
    		String propertyId = ht.get(selectedItem.getText());
    		
    		if (selectedItem.isChecked()) {
				if(!filterPanel.isEnabled()) filterPanel.setEnabled(true);
				if(!filterPanel.isVisible()) filterPanel.setVisible(true);
    			
				filterComponent = new FilterComponent(propertyId , container);
	    		filterComponent.buildContent();
	    		filterPanel.addComponent(filterComponent); 
	    		filterMap.put(propertyId, filterComponent);
    		 } else {
    			//SE TOLTI TUTTI GLI ELEMENTI DISABILITO PANNELLO FILTRI
    			 List<MenuItem> li = selectedItem.getParent().getChildren();
    			 boolean disableAll = true;
    			 int countItem = li.size();
    			 for (MenuItem menuItem : li) {
					if(menuItem.isChecked()) {
						disableAll = false;
						break;
					}
				}
    			FilterComponent filterComponent = filterMap.get(propertyId);
    			filterComponent.removeCurrentFilter();
    			filterPanel.removeComponent(filterComponent);
    			 
    			if(disableAll) {
    				filterPanel.setEnabled(false); // rimossi tutti filtri disabulito pannello
    				filterPanel.setVisible(false);
    			}
    			  		
    		}
    		 
        }
    };
	
	public void add(){
		if (!entityList.isEditable()) {
			edit(createEntityItem() , true);
		} 
	}
	public void modify(final Object itemIds){
		if (!entityList.isEditable()) {		
			Object itemId = null;
			if ((itemIds instanceof Collection)) {
				itemId = ((Collection) itemIds).iterator().next();
			}else{
				itemId = itemIds;
			}
			
			EntityItem<T> item = container.getItem(itemId);
			edit(item, false);
			
		}
	}
	
	public void edit(EntityItem<? extends T> item , boolean isNew){
		EntityFormWindow<? extends T> editorWindow = buildEntityWindow();
		edit(editorWindow , item , isNew);
	}

	public void edit(EntityFormWindow<? extends T> editorWindow , EntityItem<? extends T> item , boolean isNew){
		editorWindow.setNewItem(isNew);
		editorWindow.setReadOnly(false);
		if (!isNew)
			item.refresh();
		editorWindow.setEntityItem(item);
		UI.getCurrent().addWindow(editorWindow);	
		
	}
	public void delete(final Object itemId){
		MessageBox.showPlain(Icon.QUESTION, I18nUtils.getMessage("smc.messagebox.delete.title", ""),
		I18nUtils.getMessage("smc.messagebox.delete.text", ""), new MessageBoxListener() {
			@Override
			public void buttonClicked(final ButtonId buttonId) {
				if (buttonId.compareTo(ButtonId.YES) == 0) {
					if (itemId  instanceof Collection) {
						for (Object value : (Collection)itemId) {
							container.removeItem(value);	
						}
					}else{
						container.removeItem(itemId);
					}	
					container.commit();
				}
			}
		}, ButtonId.NO, ButtonId.YES);
	}
	
	
	@Override
	public void setTableFieldFactory(final TableFieldFactory factory) {
		entityList.setTableFieldFactory(factory);
		entityList.setEditable(true);
		editButtonPanel.getComponent(1).setEnabled(false);
		editButtonPanel.getComponent(1).setVisible(false);
		entityList.setBuffered(true);
	}

	public void setPropertyToShow(final String[] propertyToShow) {
		this.displayProperties = propertyToShow;
		if (initialized && container != null){
			initializeDisplayProperties(this.displayProperties);
			entityList.setVisibleColumns(displayProperties);
		}
	}

	public EntityItem<T> createEntityItem() {

		EntityItem<T> entityItem = null;
		try {
			entityItem = container.createEntityItem(entityClass.newInstance());
			// container.refresh();
		} catch (final InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return entityItem;
	}

	public void addFilter(final Filter filter) {
		container.addContainerFilter(filter);
	}

	public void removeFilter(final Filter filter) {
		container.removeContainerFilter(filter);
	}

	public void removeAllFilter() {
		container.removeAllContainerFilters();
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	@Override
	public void attach() {
		if (!isInitialized())
			init();
		editButtonPanel.setEnabled(isEditable());
		// if (container != null)
		// container.refresh();
		super.attach();
	}

	@Override
	public boolean isInitialized() {
		return initialized;
	}

	@Override
	public void save() throws CommitException {
		// TODO Auto-generated method stub
	}

	@Override
	public void discard() {
		// TODO Auto-generated method stub

	}

	public Class<?> getType() {
		return this.entityClass;
	}

	public boolean isSearchFormEnable() {
		return searchFormEnable;
	}

	public void setSearchFormEnable(boolean searchFormEnable) {
		this.searchFormEnable = searchFormEnable;
	}
	
	private String[] buildDisplayProperties(){
		List<String> properties = new ArrayList<String>();
		for (Object pid : container.getContainerPropertyIds()) {
			LOG.info("check property {}.{}" , container.getEntityClass().getSimpleName(), pid);
			if (propertyList.getClassMetadata().getProperty(pid.toString()) != null) 
				 if (propertyList.getClassMetadata().getProperty(pid.toString())
						.getAnnotation(Id.class) == null)
					if (propertyList.getClassMetadata().getProperty(pid.toString())
							.getAnnotation(EmbeddedId.class) == null)
						if (propertyList.getPropertyKind(pid.toString()).equals(
								PropertyKind.SIMPLE) || Multilingual.class.isAssignableFrom(propertyList.getPropertyType(pid.toString())))
									properties.add(pid.toString());
		}
		
		Collections.sort(properties);
		
		return  properties.toArray(new String[]{});
		
		
	}
	private void initializeDisplayProperties(String[] dispayProperties){
		
		for (String string : displayProperties) {
			if (string.contains(".")) {

				container.addNestedContainerProperty(string);
				propertyList.addNestedProperty(string);
			}
			if (Multilingual.class.isAssignableFrom(propertyList.getPropertyType(string))) {
				entityList.setConverter(string, new MultilingualFieldConvert());
			}
			
			if(MultiValueField.class.isAssignableFrom(propertyList.getPropertyType(string)))
				entityList.setConverter(string, new AttributeTypeValueConverter());
			
			String message = I18nUtils.getMessage(entityClass.getSimpleName().toLowerCase() + "." + string, null);
			if (message != null)
				entityList.setColumnHeader(string, message);
		}
	}
	
	public void buttonsEnabler(boolean _new , boolean _edit ,boolean _delete ){
		newButton.setEnabled(_new);
		editButton.setEnabled(_edit);
		deleteButton.setEnabled(_delete);
	}
}
