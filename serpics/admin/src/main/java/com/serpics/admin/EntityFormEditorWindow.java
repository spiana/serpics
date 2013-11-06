package com.serpics.admin;

import com.vaadin.data.Item;
import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class EntityFormEditorWindow<T> extends Window implements Handler {
	private EntityFormEditor<T> formEditor;
		
	static final Action esc = new ShortcutAction("Close window", ShortcutAction.KeyCode.ESCAPE, null);
	static final Action[] actions = new Action[] { esc };
			
	public EntityFormEditorWindow(Class entityClass){
		
		
		
		VerticalLayout vl = new VerticalLayout();
		setContent(vl);
		
//		TabSheet tabSheet = new TabSheet();
//		vl.addComponent(tabSheet);
		
		formEditor = new EntityFormEditor<T>(entityClass);
//		tabSheet.addTab(formEditor);
		vl.addComponent(formEditor);
		
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
		
		addActionHandler(this);
		
	}
	
	public void setEntityItem(Item entityItem){
		formEditor.setEntityItem(entityItem);
	}

	@Override
	public Action[] getActions(Object target, Object sender) {
		return actions;
	}

	@Override
	public void handleAction(Action action, Object sender, Object target) {
		if (action == esc) {
			close();
		}
		
	}
}
