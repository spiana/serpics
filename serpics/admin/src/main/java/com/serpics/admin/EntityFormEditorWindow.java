package com.serpics.admin;

import com.vaadin.data.Item;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class EntityFormEditorWindow<T> extends Window {
	private EntityFormEditor<T> formEditor;
	
	public EntityFormEditorWindow(Class entityClass){
		formEditor = new EntityFormEditor<T>(entityClass);
		setContent(formEditor);
		setModal(true);
		setHeight("80.0%");
		setWidth("50.0%");
		center();
		
		formEditor.getCancelButton().addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				EntityFormEditorWindow.this.close();				
			}
		});
	}
	
	public void setEntityItem(Item entityItem){
		formEditor.setEntityItem(entityItem);
	}
}
