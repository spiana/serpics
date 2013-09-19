package com.serpics.admin.ui;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

import org.jsoup.nodes.Entities;

import com.serpics.admin.EntityFormEditor;
import com.serpics.admin.EntityFormEditorWindow;
import com.serpics.core.service.EntityService;
import com.vaadin.addon.jpacontainer.SerpicsCachingLocalEntityProvider;
import com.vaadin.addon.jpacontainer.SerpicsEntityProvider;
import com.vaadin.addon.jpacontainer.SerpicsPersistentContainer;
import com.vaadin.addon.jpacontainer.metadata.PropertyKind;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public class SerpicsEntityTableEditor<T> extends CustomComponent{
	
	private Class entityClass;
	private EntityService service;
	private EntityFormEditor<T> editor;
	private EntityFormEditorWindow<T> editorWindow;
	
	@Transient
	private SerpicsCachingLocalEntityProvider<T> provider;
	


	public SerpicsEntityTableEditor(Class entityClass, EntityService service) {
		super();
		this.entityClass = entityClass;
		this.service = service;
	}

	public void init(){
		SerpicsPersistentContainer<T> cont = new SerpicsPersistentContainer<T>(entityClass);
		
		SerpicsEntityProvider<T> serpicsEntityProvider = new SerpicsEntityProvider<T>(entityClass);
		serpicsEntityProvider.setService((EntityService) service);
		provider = new SerpicsCachingLocalEntityProvider<T>(entityClass, serpicsEntityProvider);
		provider.setCacheEnabled(true);
		
//		provider.setLazyLoadingDelegate(serpicsHibernateLazyLoadingDelegate);
				
		cont.setEntityProvider(provider);
		
		final Table t = new Table(entityClass.getName(), cont);
		t.setSelectable(true);
		t.setImmediate(true);
		t.setSizeFull();
		t.setColumnCollapsingAllowed(true);
		t.setColumnReorderingAllowed(true);
		
		List<Object> propsToShow = new ArrayList<Object>();
		
		for (String id : cont.getContainerPropertyIds()){
			if (cont.getPropertyKind(id) != PropertyKind.SIMPLE && cont.getPropertyKind(id) != PropertyKind.ELEMENT_COLLECTION)
				continue;
			propsToShow.add(id);			
		}
		
		t.setVisibleColumns(propsToShow.toArray());
		
		
//		cont.addNestedContainerProperty("storerelation.*");
//		t.addGeneratedColumn("catalog", new Table.ColumnGenerator() {
//			
//			@Override
//			public Object generateCell(Table source, Object itemId, Object columnId) {
//				return new TextField("catalog");
//			}
//		});
		
		
		editorWindow = new EntityFormEditorWindow<T>(entityClass);
		
		
		t.addValueChangeListener(new Property.ValueChangeListener() {
	        public void valueChange(ValueChangeEvent event) {
	            // Close the form if the item is deselected
	            if (event.getProperty().getValue() == null) {
//	                form.setVisible(false);
	                return;
	            }

	            // Bind the form to the selected item
	            
	            editorWindow.setEntityItem(t.getItem(t.getValue()));	            
	            UI.getCurrent().addWindow(editorWindow);
	            editorWindow.focus();
	          
	          
	        }
	    });
		
//		editorWindow = new Window(caption, content);
		
		setCompositionRoot(t);
		
	}

	public Class getEntityClass() {
		return entityClass;
	}


}
