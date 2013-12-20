package com.serpics.vaadin.ui;

import com.serpics.core.persistence.jpa.Entity;
import com.serpics.vaadin.ui.EntityComponent.EntityFormComponent;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.SerpicsStringToNumberConverter;
import com.vaadin.addon.jpacontainer.metadata.MetadataFactory;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroupFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;


public class EntityForm<T> extends FormLayout implements
FieldGroupFieldFactory, EntityFormComponent<T> {
    private static final long serialVersionUID = -7816433625437405000L;

    private transient PropertyList<T> propertyList;

    private final FieldGroup fieldGroup;

    private boolean initialized = false;

    protected String[] displayProperties;

    EntityItem<T> entityItem;

    Entity parentEntity ;

    private String prefix;

    private boolean readOnly = true;



    public EntityForm(final Class<T> clazz) {

        propertyList = new PropertyList<T>(MetadataFactory.getInstance()
                .getEntityClassMetadata(clazz));

        setSizeUndefined();
        fieldGroup = new FieldGroup();
        fieldGroup.setFieldFactory(this);
        setImmediate(false);
        setWidth("100%");
        setHeight("100%");
        setMargin(true);
        setSpacing(true);

    }

    /**
     * @return the caption of the editor window
     */
    private String buildCaption() {
        // return String.format("%s %s", entityItem.getItemProperty("firstName")
        // .getValue(), entityItem.getItemProperty("lastName").getValue());
        return "main";

    }

    @Override
    public <T extends Field> T createField(final Class<?> dataType, final Class<T> fieldType) {
        final DefaultFieldGroupFieldFactory fa = new DefaultFieldGroupFieldFactory();
        final T f = fa.createField(dataType, fieldType);
        if (f instanceof TextField)
            f.setWidth("500px");

        return f;
    }

    public Item getEntityItem() {
        return entityItem;
    }

    @Override
    public void setEntityItem(final EntityItem<T> entityItem) {
        this.entityItem = entityItem;
        fieldGroup.setItemDataSource(entityItem);
        fieldGroup.setBuffered(true);

        if (!initialized) {
            if (displayProperties != null)
                addField(displayProperties);
            else
                addField(propertyList.getPropertyNames().toArray(
                        new String[] {}));
            initialized = true;
        }

    }

    private void addField(final String[] propertyNames) {
        for (final String pid : propertyNames) {
            addComponent(createField(pid));
        }

    }

    protected Field<?> createField(final String pid) {
        final Property p = entityItem.getItemProperty((prefix != null ? prefix : "")
                + pid);

        final Field<?> f = fieldGroup.buildAndBind((prefix != null ? prefix : "")
                + pid, (prefix != null ? prefix : "") + pid);
        f.setBuffered(true);
        if (f instanceof TextField) {
            if (Number.class.isAssignableFrom(p.getType()))
                ((TextField) f)
                .setConverter(new SerpicsStringToNumberConverter());
            ((TextField) f).setNullRepresentation("");
        }

        return f;

    }


    @Override
    public void save() throws CommitException {

        fieldGroup.commit();
        if (entityItem.getItemId() == null)
            entityItem.getContainer().addEntity(entityItem.getEntity());
    }

    @Override
    public void discard() {
        fieldGroup.discard();
    }

    public void setPrefix(final String prefix) {
        this.prefix = prefix + ".";
    }


    @Override
    public void attach() {
        if(readOnly){
            fieldGroup.setEnabled(false);
        } else
            fieldGroup.setEnabled(true);
        super.attach();
    }
    public void setDisplayProperties(final String[] displayProperties) {
        this.displayProperties = displayProperties;
    }

    @Override
    public void setParentEntity(final Object entity) {
        this.parentEntity = (Entity) entity;
    }

    @Override
    public boolean isReadOnly() {
        return readOnly;
    }

    @Override
    public void setReadOnly(final boolean readOnly) {
        this.readOnly = readOnly;
    }



}
