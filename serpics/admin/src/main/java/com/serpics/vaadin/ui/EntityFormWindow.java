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

	private EntityForm<T> formEditor;
	private TabSheet tabSheet = new TabSheet();
	static final Action esc = new ShortcutAction("Close window",
			ShortcutAction.KeyCode.ESCAPE, null);
	static final Action[] actions = new Action[] { esc };
	
	
	private transient PropertyList<T> propertyList;
	private List<EntityComponent> componentList = new ArrayList<EntityComponent>(0);

	private Button saveButton;
	private Button cancelButton;

	public EntityFormWindow(Class entityClass) throws SecurityException {

		this.propertyList = new PropertyList<T>(MetadataFactory.getInstance()
				.getEntityClassMetadata(entityClass));

		VerticalLayout vl = new VerticalLayout();
		setContent(vl);
		
		tabSheet.setWidth("100%");
		tabSheet.setHeight("100%");

		vl.addComponent(tabSheet);

		saveButton = new Button("Save");
		cancelButton = new Button("Cancel");

		HorizontalLayout hl = new HorizontalLayout();
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

	public void addTab( EntityComponent component , String caption){
		tabSheet.addTab(component,caption);
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

	public EntityForm<T> getFormEditor() {
		return formEditor;
	}
}
