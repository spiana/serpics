package com.serpics.vaadin.ui;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.metamodel.Type;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.springframework.util.StringUtils;

import com.serpics.admin.UIUtil;
import com.serpics.core.service.EntityService;
import com.serpics.vaadin.ui.EntityComponent.EntityTableComponent;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.SerpicsPersistentContainer;
import com.vaadin.addon.jpacontainer.metadata.MetadataFactory;
import com.vaadin.addon.jpacontainer.metadata.PropertyKind;
import com.vaadin.addon.jpacontainer.provider.SerpicsCachingLocalEntityProvider;
import com.vaadin.addon.jpacontainer.provider.SerpicsEntityProvider;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class EntityFormWindow<T> extends Window implements Handler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TabSheet tabSheet = new TabSheet();
	static final Action esc = new ShortcutAction("Close window",
			ShortcutAction.KeyCode.ESCAPE, null);
	static final Action[] actions = new Action[] { esc };

	private boolean readOnly = true;
	private boolean newItem = true;

	private List<EntityComponent<?>> componentList = new ArrayList<EntityComponent<?>>(
			0);

	private Button saveButton;
	private Button cancelButton;

	public EntityFormWindow() throws SecurityException {
		init();
	}

	public void init() {

		VerticalLayout vl = new VerticalLayout();
		setContent(vl);

		tabSheet.setWidth("100%");
		tabSheet.setHeight("100%");

		vl.addComponent(tabSheet);

		saveButton = new Button("Save");
		cancelButton = new Button("Cancel");

		HorizontalLayout hl = new HorizontalLayout();
		hl.setWidth("100%");
		hl.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		hl.addComponent(saveButton);
		hl.addComponent(cancelButton);

		vl.addComponent(hl);
		setModal(true);
		setHeight("80.0%");
		setWidth("50.0%");
		center();

		cancelButton.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				for (EntityComponent component : componentList) {
					component.discard();
				}

				EntityFormWindow.this.close();
			}
		});

		saveButton.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					for (EntityComponent component : componentList) {
						component.save();
					}
				} catch (CommitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				EntityFormWindow.this.close();
			}
		});

		addActionHandler(this);
	}

	public void addTab(EntityComponent<?> component, String caption) {
		tabSheet.addTab(component, caption);
		componentList.add(component);
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

	@SuppressWarnings("unchecked")
	public void setEntityItem(EntityItem entityItem) {
		for (@SuppressWarnings("rawtypes")
		EntityComponent c : componentList) {
			if (c instanceof EntityTableComponent) {
				if (!newItem)
					c.setParentEntity(entityItem.getEntity());
			} else
				c.setEntityItem(entityItem);

		}
	}

	@Override
	public void attach() {

		if (readOnly) {
			saveButton.setEnabled(false);
		}else
			saveButton.setEnabled(true);
		
		for (@SuppressWarnings("rawtypes")
		EntityComponent c : componentList) {
			if (c instanceof EntityTableComponent) {
				if (!newItem)
					c.setEnabled(true);
				else
					c.setEnabled(false);
			} else {
				if (readOnly)
					c.setEnabled(false);
				else
					c.setEnabled(true);
			}
		}
		super.attach();
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public boolean isNewItem() {
		return newItem;
	}

	public void setNewItem(boolean newItem) {
		this.newItem = newItem;
	}
}
