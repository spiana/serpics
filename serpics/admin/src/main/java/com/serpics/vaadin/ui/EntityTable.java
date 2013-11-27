package com.serpics.vaadin.ui;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

import com.serpics.admin.UIUtil;
import com.serpics.core.service.EntityService;
import com.serpics.vaadin.ui.memeship.UserEditorComponent;
import com.vaadin.addon.jpacontainer.SerpicsPersistentContainer;
import com.vaadin.addon.jpacontainer.metadata.PropertyKind;
import com.vaadin.addon.jpacontainer.provider.SerpicsCachingLocalEntityProvider;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class EntityTable<T> extends Table implements EntityComponent {
	@Transient
	private Class<T> entityClass;

	private EntityService service;

	private String[] propertyToShow;

	public EntityTable(Class<T> entityClass, EntityService service) {
		super();
		this.entityClass = entityClass;
		this.service = service;
	}

	public void init() {
		final SerpicsPersistentContainer<T> cont = new SerpicsPersistentContainer<T>(
				entityClass);

		final SerpicsCachingLocalEntityProvider<T> provider = new SerpicsCachingLocalEntityProvider<T>(
				entityClass);
		provider.setService(service);
		provider.setCacheEnabled(true);
		cont.setEntityProvider(provider);

		final EntityFormWindow<T> editorWindow;

		setContainerDataSource(cont);
		setSelectable(true);
		setImmediate(true);
		setSizeFull();
		setColumnCollapsingAllowed(true);
		setColumnReorderingAllowed(true);

		
		if (propertyToShow == null) {
			List<Object> propsToShow = new ArrayList<Object>();
			for (String id : cont.getContainerPropertyIds()) {
				if (cont.getPropertyKind(id) != PropertyKind.SIMPLE
						&& cont.getPropertyKind(id) != PropertyKind.ELEMENT_COLLECTION)
					continue;
				propsToShow.add(id);

			}
			setVisibleColumns(propsToShow.toArray());
		} else {
			setVisibleColumns(propertyToShow);
		}

		editorWindow = new EntityFormWindow<T>(entityClass);
		final EntityForm ue = UIUtil.getEntityEditor(entityClass);
		editorWindow.addTab(ue, "General");

		addValueChangeListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				if (event.getProperty().getValue() == null)
					return;

				ue.setEntityItem(cont.getItem(getValue()));
				UI.getCurrent().addWindow(editorWindow);
			}
		});

		final VerticalLayout v = new VerticalLayout();
		final Button _new = new Button("new");

		_new.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					ue.setEntityItem(cont.createEntityItem((T) entityClass
							.newInstance()));

				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				UI.getCurrent().addWindow(editorWindow);
			}
		});

	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public EntityService getService() {
		return service;
	}

	public void setService(EntityService service) {
		this.service = service;
	}

	@Override
	public void save() throws CommitException {
		// TODO Auto-generated method stub

	}

}
