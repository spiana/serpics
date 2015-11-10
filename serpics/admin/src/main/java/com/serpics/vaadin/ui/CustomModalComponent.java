package com.serpics.vaadin.ui;

import java.util.ArrayList;
import java.util.List;

import com.serpics.vaadin.ui.EntityComponent.EntityComponentChild;
import com.serpics.vaadin.ui.EntityComponent.EntityFormComponent;
import com.serpics.vaadin.ui.EntityComponent.MasterTableComponent;
import com.vaadin.addon.jpacontainer.EntityContainer;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class CustomModalComponent<T> extends Window implements Handler {
	
    private static final long serialVersionUID = 2590755708760150150L;
    private final TabSheet tabSheet = new TabSheet();
    static final Action esc = new ShortcutAction("Close window", ShortcutAction.KeyCode.ESCAPE, null);
    static final Action[] actions = new Action[] { esc };
    private boolean readOnly = true;
    private boolean newItem = true;
    private final List<EntityComponent<?>> componentList = new ArrayList<EntityComponent<?>>(0);    
    private transient EntityContainer<T> container;
    private transient EntityItem<T> item;

    public CustomModalComponent() throws SecurityException {
        build();
    }

    protected void build() {
        final VerticalLayout layout = new VerticalLayout();        
        layout.setSizeFull();
        setContent(layout);
        tabSheet.setWidth("100%");
        tabSheet.setHeight("110%");
        tabSheet.addStyleName("framed");
        layout.addComponent(tabSheet);
        layout.setExpandRatio(tabSheet, 1);

        final HorizontalLayout panel = new HorizontalLayout();
        panel.setWidth("100%");
        panel.setHeight("50px");
    
        panel.setDefaultComponentAlignment(Alignment.TOP_CENTER);
    
        layout.addComponent(panel);
        setModal(true);
        setDraggable(false);
        setHeight("45.0%");
        setWidth("45.0%");
        center();
    }

    public void addTab(final EntityComponent<?> component, final String caption) {
        component.init();
        tabSheet.addTab(component, caption);
        componentList.add(component);
    }

    @Override
    public Action[] getActions(final Object target, final Object sender) {
        return actions;
    }

    @Override
    public void handleAction(final Action action, final Object sender, final Object target) {
        if (action == esc) {
            close();
        }

    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void setEntityItem(final EntityItem entityItem) {
   	
    	this.item = entityItem;
    	
        for (final EntityComponent c : componentList) {
            if (c instanceof EntityComponentChild) {
                if (!newItem)
                    ((EntityComponentChild) c).setParentEntity(entityItem);
            } else if (c instanceof EntityFormComponent)
                ((EntityFormComponent) c).setEntityItem(entityItem);

        }
    }

    @Override
    public void attach() {     

        int position = 0;
        for (@SuppressWarnings("rawtypes")
        final EntityComponent c : componentList) {
            if (c instanceof MasterTableComponent) {
                if (!newItem)
                    c.setEnabled(true);
                else
                    c.setEnabled(false);
            } else {
                c.setReadOnly(readOnly);
            }
            if (newItem) {
                if (position > 0)
                    tabSheet.getTab(c).setVisible(false);
            } else
                tabSheet.getTab(c).setVisible(true);

            position++;
        }
        tabSheet.setSelectedTab(0);
        super.attach();
    }

    @Override
    public boolean isReadOnly() {
        return readOnly;
    }

    @Override
    public void setReadOnly(final boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean isNewItem() {
        return newItem;
    }

    public void setNewItem(final boolean newItem) {
        this.newItem = newItem;
    }

    public int getTabComponentCount() {
        return tabSheet.getComponentCount() ;
    }

	public EntityContainer<T> getContainer() {
		return container;
	}

	public void setContainer(EntityContainer<T> container) {
		this.container = container;
	}

}


