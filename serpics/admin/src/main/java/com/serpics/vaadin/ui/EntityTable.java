package com.serpics.vaadin.ui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Transient;

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

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

public abstract class EntityTable<T> extends CustomComponent implements EntityTableComponent<T> {

    private static final long serialVersionUID = 8614651463123352933L;

    private boolean initialized = false;

    @Transient
    private final Class<T> entityClass;

    private String[] displayProperties;
    private final Set<String> hideProperties = new HashSet<String>();

    protected EntityFormWindow<T> editorWindow;
    protected Table entityList;

    private final HorizontalLayout editButtonPanel = new HorizontalLayout();

    @Transient
    protected SerpicsPersistentContainer<T> cont;

    @Transient
    protected SerpicsCachingLocalEntityProvider<T> provider;

    public EntityTable(final Class<T> entityClass) {
        super();
        this.entityClass = entityClass;
        cont = new SerpicsPersistentContainer<T>(entityClass);
        provider = new SerpicsCachingLocalEntityProvider<T>(entityClass);
        provider.setCacheEnabled(true);
        cont.setEntityProvider(provider);
        editorWindow = new EntityFormWindow<T>();
    }

    @Override
    public void init() {
        if (initialized)
            return;
        entityList = new Table();
        entityList.setContainerDataSource(cont);
        entityList.setSelectable(true);
        entityList.setImmediate(true);
        entityList.setSizeFull();
        entityList.setColumnCollapsingAllowed(true);
        entityList.setColumnReorderingAllowed(true);

        if (displayProperties == null) {
            final List<Object> propsToShow = new ArrayList<Object>();
            for (final String id : cont.getContainerPropertyIds()) {
                if (cont.getPropertyKind(id).equals(PropertyKind.SIMPLE))
                    if (!hideProperties.contains(id))
                        propsToShow.add(id);
            }
            entityList.setVisibleColumns(propsToShow.toArray());
        } else {
            entityList.setVisibleColumns(displayProperties);
        }

        final VerticalLayout v = new VerticalLayout();
        v.setSizeFull();

        this.editButtonPanel.setDefaultComponentAlignment(Alignment.BOTTOM_LEFT);
        this.editButtonPanel.setEnabled(isEnabled());

        v.addComponent(editButtonPanel);
        v.addComponent(entityList);
        v.setExpandRatio(entityList, 1);

        entityList.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(final com.vaadin.data.Property.ValueChangeEvent event) {
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
        editButtonPanel.addComponent(_new);

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
        editButtonPanel.addComponent(_edit);

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
        final Button _delete = new Button("delete");
        editButtonPanel.addComponent(_delete);

        _delete.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(final ClickEvent event) {
                if (entityList.getValue() == null)
                    return;
                MessageBox.showPlain(Icon.QUESTION, "Attenzione !", "vuoi cancellare l'oggetto selezionato ?",
                        new MessageBoxListener() {
                    @Override
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.compareTo(ButtonId.YES) == 0) {
                            if (!cont.removeItem(entityList.getValue()))
                                System.out.println("Errore !");

                        }

                    }

                }, ButtonId.NO, ButtonId.YES);

            }
        });

        setCompositionRoot(v);
        setSizeFull();

        this.initialized = true;
    }

    public void setService(final EntityService service) {
        provider.setService(service);
    }

    public void setPropertyToShow(final String[] propertyToShow) {
        this.displayProperties = propertyToShow;
    }

    public void setEditorWindow(final EntityFormWindow<T> editorWindow) {
        this.editorWindow = editorWindow;
    }

    public EntityItem<T> createEntityItem() {

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

    public void addFilter(final Filter filter) {
        cont.addContainerFilter(filter);
    }

    public void removeFilter(final Filter filter) {
        cont.removeContainerFilter(filter);
    }

    public void removeAllFilter() {
        cont.removeAllContainerFilters();
    }

    public boolean isEditable() {
        return editorWindow != null;
    }

    @Override
    public void attach() {
        editButtonPanel.setEnabled(isEditable());
        super.attach();
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }
}
