package com.serpics.vaadin.ui;

import java.util.ArrayList;
import java.util.List;

import com.serpics.vaadin.ui.EntityComponent.EntityComponentChild;
import com.serpics.vaadin.ui.EntityComponent.EntityFormComponent;
import com.serpics.vaadin.ui.EntityComponent.EntityTableComponent;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class EntityFormWindow<T> extends Window implements Handler {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final TabSheet tabSheet = new TabSheet();
    static final Action esc = new ShortcutAction("Close window",
            ShortcutAction.KeyCode.ESCAPE, null);
    static final Action[] actions = new Action[] { esc };

    private boolean readOnly = true;
    private boolean newItem = true;

    private final List<EntityComponent<?>> componentList = new ArrayList<EntityComponent<?>>(
            0);

    private Button saveButton;
    private Button cancelButton;

    public EntityFormWindow() throws SecurityException {
        init();
    }

    public void init() {

        final VerticalLayout vl = new VerticalLayout();
        vl.setSizeFull();
        setContent(vl);

        tabSheet.setSizeFull();

        vl.addComponent(tabSheet);
        vl.setExpandRatio(tabSheet, 1);

        saveButton = new Button("Save");
        cancelButton = new Button("Cancel");

        final HorizontalLayout hl = new HorizontalLayout();
        hl.setWidth("100%");
        hl.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        hl.addComponent(cancelButton);
        hl.addComponent(saveButton);

        vl.addComponent(hl);
        setModal(true);
        setHeight("80.0%");
        setWidth("50.0%");
        center();

        cancelButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(final ClickEvent event) {
                for (final EntityComponent component : componentList) {
                    if (component instanceof EntityFormComponent) {
                        if (component.isEnabled() && !component.isReadOnly())
                            ((EntityFormComponent) component).discard();
                    }

                }

                EntityFormWindow.this.close();
            }
        });

        saveButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(final ClickEvent event) {
                try {
                    for (final EntityComponent component : componentList) {
                        if (component instanceof EntityFormComponent) {
                            if (component.isEnabled() && !component.isReadOnly())
                                ((EntityFormComponent) component).save();
                        }
                    }
                } catch (final CommitException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                EntityFormWindow.this.close();
            }
        });

        addActionHandler(this);
    }

    public void addTab(final EntityComponent<?> component, final String caption) {
        if (!component.isInitialized())
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

    @SuppressWarnings("unchecked")
    public void setEntityItem(final EntityItem entityItem) {
        for (@SuppressWarnings("rawtypes") final
                EntityComponent c : componentList) {
            if (c instanceof EntityComponentChild) {
                if (!newItem)
                    ((EntityComponentChild) c).setParentEntity(entityItem);
            } else if (c instanceof EntityFormComponent)
                ((EntityFormComponent) c).setEntityItem(entityItem);

        }
    }

    @Override
    public void attach() {

        if (readOnly) {
            saveButton.setEnabled(false);
        }else
            saveButton.setEnabled(true);

        int position = 0;
        for (@SuppressWarnings("rawtypes") final
                EntityComponent c : componentList) {
            if (c instanceof EntityTableComponent) {
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
}
