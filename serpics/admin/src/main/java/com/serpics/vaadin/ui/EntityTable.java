package com.serpics.vaadin.ui;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.core.data.Repository;
import com.serpics.core.data.RepositoryInitializer;
import com.serpics.vaadin.ui.EntityComponent.EntityTableComponent;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.provider.ServiceContainerFactory;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TableFieldFactory;
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
    private transient final Class<T> entityClass;

    private String[] displayProperties;
    private final Set<String> hideProperties = new HashSet<String>();
    private boolean editable= true;
    
   // protected EntityFormWindow<T> editorWindow;
    protected Table entityList;

    private final HorizontalLayout editButtonPanel = new HorizontalLayout();

    protected transient JPAContainer<T> container;
    
    public EntityTable(final Class<T> entityClass) {
        super();
        this.entityClass = entityClass;
    }

    @Override
    public void init() {
    	buildContainer();
    	buildTable();
   
    }

    protected void buildContainer(){
    	if (container == null) {
            container = ServiceContainerFactory.make(entityClass);
        }
        this.initialized = true;
    }
    
    public abstract EntityFormWindow<T>  buildEntityWindow();
    
    protected void buildTable() {
    	this.entityList = new Table();
        entityList.setSelectable(true);
        entityList.setImmediate(true);
        entityList.setSizeFull();
        entityList.setColumnCollapsingAllowed(true);
        entityList.setColumnReorderingAllowed(true);
        entityList.setContainerDataSource(this.container);

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
                if(!entityList.isEditable()){
                	EntityFormWindow<T> editorWindow = buildEntityWindow();
                    editorWindow.setNewItem(true);
                    editorWindow.setReadOnly(false);
                    editorWindow.setEntityItem(createEntityItem());
                    UI.getCurrent().addWindow(editorWindow);
                }else{
                    createEntityItem();
                    entityList.refreshRowCache();
                }
            }
        });

        final Button _edit = new Button("edit");
        editButtonPanel.addComponent(_edit);

        _edit.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(final ClickEvent event) {
                if (entityList.getValue() == null)
                    return;
                if (!entityList.isEditable()) {
                	EntityFormWindow<T> editorWindow = buildEntityWindow();
                    editorWindow.setNewItem(false);
                    editorWindow.setReadOnly(false);
                    editorWindow.setEntityItem(container.getItem(entityList.getValue()));
                    UI.getCurrent().addWindow(editorWindow);
                }
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
                            if (!container.removeItem(entityList.getValue()))
                                System.out.println("Errore !");

                        }

                    }

                }, ButtonId.NO, ButtonId.YES);

            }
        });

        setCompositionRoot(v);
        setSizeFull();
    }

    @Override
    public void setTableFieldFactory(final TableFieldFactory factory) {
        entityList.setTableFieldFactory(factory);
        entityList.setEditable(true);
        editButtonPanel.getComponent(1).setEnabled(false);
        editButtonPanel.getComponent(1).setVisible(false);
        entityList.setBuffered(true);
    }

    public void setPropertyToShow(final String[] propertyToShow) {
        this.displayProperties = propertyToShow;
        if (initialized && container != null)
            entityList.setVisibleColumns(displayProperties);
    }

     public EntityItem<T> createEntityItem() {

        EntityItem<T> entityItem = null;
        try {
            entityItem = container.createEntityItem(entityClass.newInstance());
            container.refresh();
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
        container.addContainerFilter(filter);
    }

    public void removeFilter(final Filter filter) {
        container.removeContainerFilter(filter);
    }

    public void removeAllFilter() {
        container.removeAllContainerFilters();
    }

    public boolean isEditable() {
        return editable;
    }

    
    public void setEditable(boolean editable) {
		this.editable = editable;
	}

	@Override
    public void attach() {
        if (!isInitialized())
            init();
        editButtonPanel.setEnabled(isEditable());
        super.attach();
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }

    @Override
    public void save() throws CommitException {
        // TODO Auto-generated method stub
    }

    @Override
    public void discard() {
        // TODO Auto-generated method stub

    }
   
}
