package com.serpics.vaadin.ui;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

import org.springframework.util.Assert;

import com.serpics.core.service.EntityService;
import com.serpics.vaadin.ui.EntityComponent.EntityTableComponent;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.SerpicsPersistentContainer;
import com.vaadin.addon.jpacontainer.metadata.PropertyKind;
import com.vaadin.addon.jpacontainer.provider.SerpicsCachingLocalEntityProvider;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Property;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public abstract class EntityTable<T> extends CustomComponent implements
EntityTableComponent<T> {
    @Transient
    private final Class<T> entityClass;

    private String[] propertyToShow;

    protected EntityFormWindow<T> editorWindow;

    protected Table entityList;

    @Transient
    protected SerpicsPersistentContainer<T> cont;

    @Transient
    protected SerpicsCachingLocalEntityProvider<T> provider;

    public EntityTable(final Class entityClass) {
        super();
        this.entityClass = entityClass;
        cont = new SerpicsPersistentContainer<T>(entityClass);
        provider = new SerpicsCachingLocalEntityProvider<T>(entityClass);
        provider.setCacheEnabled(true);
        cont.setEntityProvider(provider);
    }

    public void init() {
        Assert.notNull(editorWindow, "entitywindow must be set !");

        entityList = new Table();

        entityList.setContainerDataSource(cont);
        entityList.setSelectable(true);
        entityList.setImmediate(true);
        entityList.setSizeFull();
        entityList.setColumnCollapsingAllowed(true);
        entityList.setColumnReorderingAllowed(true);

        if (propertyToShow == null) {
            final List<Object> propsToShow = new ArrayList<Object>();
            for (final String id : cont.getContainerPropertyIds()) {
                if (cont.getPropertyKind(id) != PropertyKind.SIMPLE
                        && cont.getPropertyKind(id) != PropertyKind.ELEMENT_COLLECTION)
                    continue;
                propsToShow.add(id);

            }
            entityList.setVisibleColumns(propsToShow.toArray());
        } else {
            entityList.setVisibleColumns(propertyToShow);
        }



        final VerticalLayout v = new VerticalLayout();
        final HorizontalLayout buttons = new HorizontalLayout();
        // buttons.setWidth("100%");
        buttons.setDefaultComponentAlignment(Alignment.BOTTOM_LEFT);
        v.addComponent(buttons);
        v.addComponent(entityList);



        entityList.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(
                    final com.vaadin.data.Property.ValueChangeEvent event) {
                if (event.getProperty().getValue() == null)
                    return;
                // editorWindow.setReadOnly(true);
                // editorWindow.setNewItem(false);
                // editorWindow.setEntityItem(cont.getItem(entityList.getValue()));
                //
                // UI.getCurrent().addWindow(editorWindow);
            }
        });



        final Button _new = new Button("new");
        buttons.addComponent(_new);

        _new.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(final ClickEvent event) {
                editorWindow.setNewItem(true);
                editorWindow.setReadOnly(false);
                editorWindow.setEntityItem(createEntityItem());
                UI.getCurrent().addWindow(editorWindow);
            }
        });

        final Button _edit = new Button("edit");
        buttons.addComponent(_edit);

        _edit.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(final ClickEvent event) {
                if (entityList.getValue() == null)
                    return;
                editorWindow.setNewItem(false);
                editorWindow.setReadOnly(false);
                editorWindow.setEntityItem(cont.getItem(entityList.getValue()));
                UI.getCurrent().addWindow(editorWindow);
            }
        });

        setCompositionRoot(v);
    }


    public void setService(final EntityService service) {
        provider.setService(service);
    }

    public void setPropertyToShow(final String[] propertyToShow) {
        this.propertyToShow = propertyToShow;
    }

    public void setEditorWindow(final EntityFormWindow<T> editorWindow) {
        this.editorWindow = editorWindow;
    }

    public EntityItem<T> createEntityItem (){

        EntityItem<T> entityItem = null;
        try {
            entityItem = cont.createEntityItem(entityClass.newInstance());
        } catch (final InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return entityItem;
    }

    public void addFilter(final Filter filter){
        cont.addContainerFilter(filter);
    }

    public void removeFilter(final Filter filter){
        cont.removeContainerFilter(filter);
    }

    public void removeAllFilter(){
        cont.removeAllContainerFilters();
    }

}
