package com.serpics.vaadin.ui;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Id;

import com.serpics.base.persistence.MultilingualString;
import com.serpics.vaadin.ui.EntityComponent.EntityFormComponent;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.metadata.MetadataFactory;
import com.vaadin.addon.jpacontainer.metadata.PropertyKind;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroupFieldFactory;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

public abstract class EntityForm<T> extends FormLayout implements FieldGroupFieldFactory, EntityFormComponent<T> {
    private static final long serialVersionUID = -7816433625437405000L;

    private transient PropertyList<T> propertyList;

    protected final FieldGroup fieldGroup;

    private boolean initialized = false;

    private String[] displayProperties;
    private final Set<String> hideProperties = new HashSet<String>(0);
    private String[] readOnlyProperties = {};

    protected EntityItem<T> entityItem;

    private boolean readOnly = true;

    final DefaultFieldGroupFieldFactory fa = new DefaultFieldGroupFieldFactory();

    Class<T> entityClass;

    public EntityForm(final Class<T> clazz) {

        propertyList = new PropertyList<T>(MetadataFactory.getInstance().getEntityClassMetadata(clazz));

        this.entityClass = clazz;
        fieldGroup = new FieldGroup();
        fieldGroup.setFieldFactory(this);
        setWidth("100%");
        setImmediate(false);
        setMargin(true);
        setSpacing(true);

    }

    @Override
    public void init() {
        // do nothing
    }

    @Override
    public <T extends Field> T createField(final Class<?> dataType, final Class<T> fieldType) {
        final T f = fa.createField(dataType, fieldType);
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
            init();

            if (displayProperties != null)
                addField(displayProperties);
            else {
                for (final String pid : propertyList.getAllAvailablePropertyNames()) {
                    if (propertyList.getPropertyKind(pid).equals(PropertyKind.SIMPLE))
                        // exclude field with @Id annotation
                        if (propertyList.getClassMetadata().getProperty(pid).getAnnotation(Id.class) == null)
                            if (!hideProperties.contains(pid))
                                addComponent(createField(pid));
                }
            }
            initialized = true;
        }
    }

    private void addField(final String[] propertyNames) {
        for (final String pid : propertyNames) {
            addComponent(createField(pid));
        }

    }

    @SuppressWarnings("unchecked")
    protected Field<?> createField(final String pid) {
        final Property p = entityItem.getItemProperty(pid);

        final Field<?> f = fieldGroup.buildAndBind(pid);
        f.setBuffered(true);

        if (f instanceof TextField) {
            if (MultilingualString.class.isAssignableFrom(p.getType()))
                ((TextField) f).setConverter(new MultilingualStringConvert());

            ((TextField) f).setNullRepresentation("");

        }
        f.addValidator(new BeanValidator(entityClass, pid));
        if (String.class.isAssignableFrom(p.getType())) {
            f.setWidth("80%");
        }
        return f;
    }

    @Override
    public void save() throws CommitException {
        if (fieldGroup.isModified()) {
            fieldGroup.commit();
            // test if new item
            if (!entityItem.isPersistent()) {
                entityItem.getContainer().addEntity(entityItem.getEntity());
            }
        }
    }

    public void setFieldFactory(final FieldGroupFieldFactory fieldFactory) {
        fieldGroup.setFieldFactory(fieldFactory);
    }

    @Override
    public void discard() {
        fieldGroup.discard();
    }

    @Override
    public void attach() {
        setLocale(UI.getCurrent().getSession().getLocale());

        if (readOnly) {
            fieldGroup.setEnabled(false);
        } else {
            fieldGroup.setEnabled(true);
            for (final String pid : readOnlyProperties) {
                if (fieldGroup.getField(pid) != null)
                    fieldGroup.getField(pid).setReadOnly(true);
            }
        }
        super.attach();
    }

    public void setDisplayProperties(final String[] displayProperties) {
        this.displayProperties = displayProperties;
    }

    @Override
    public boolean isReadOnly() {
        return readOnly;
    }

    @Override
    public void setReadOnly(final boolean readOnly) {
        this.readOnly = readOnly;
    }

    public void setHideProperties(final String[] hideProperties) {
        this.hideProperties.addAll(Arrays.asList(hideProperties));
    }

    public void setReadOnlyProperties(final String[] readOnlyProperties) {
        this.readOnlyProperties = readOnlyProperties;
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }

    @Override
    public boolean isModifield() {
        return fieldGroup.isModified();

    }

    @Override
    public boolean isValid() {
        return fieldGroup.isValid();
    }
}
