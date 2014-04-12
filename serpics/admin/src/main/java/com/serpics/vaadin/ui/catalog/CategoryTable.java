package com.serpics.vaadin.ui.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.serpics.admin.SerpicsContainerFactory;
import com.serpics.base.persistence.Locale;
import com.serpics.base.services.LocaleService;
import com.serpics.catalog.persistence.Category;
import com.serpics.catalog.persistence.CtentryDescr;
import com.serpics.catalog.services.CategoryService;
import com.serpics.catalog.services.CtentryDescrService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityForm;
import com.serpics.vaadin.ui.EntityTable;
import com.vaadin.addon.jpacontainer.SerpicsPersistentContainer;
import com.vaadin.data.Container;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Table;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;

@VaadinComponent("categoryTable")
public class CategoryTable extends EntityTable<Category> {
    private static final long serialVersionUID = -8891254200870608192L;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CtentryDescrService ctentryDescrService;

    @Autowired
    LocaleService localeService;

    public CategoryTable() {
        super(Category.class);

    }


    @Override
    public void init() {
        setPropertyToShow(new String[] { "code", "url" });

        editorWindow.addTab(new EntityForm<Category>(Category.class) {
            private static final long serialVersionUID = 8129655459345393570L;

            final SerpicsPersistentContainer<CtentryDescr> c = SerpicsContainerFactory.make(CtentryDescr.class,
                    ctentryDescrService);

            private final Table t = new Table();

            @Override
            public void attach() {
                if (entityItem.isPersistent()) {
                    t.setEnabled(true);
                    initializeDescriptions();
                    t.setEditable(true);
                } else
                    t.setEnabled(false);
                super.attach();
            }

            @Override
            public void init() {
                displayProperties = new String[] { "code", "url", "description", "field1", "field2", "field3",
                        "updated", "created" };
                this.setReadOnlyProperties(new String[] { "updated", "created" });
                super.init();
            }

            private void initializeDescriptions() {
                c.removeAllContainerFilters();
                c.addContainerFilter(new Compare.Equal("ctentry", entityItem.getEntity()));

                if (c.getItemIds().isEmpty()) {
                    final Locale l = localeService.findByLanguage("it");
                    Assert.notNull(l);
                    final CtentryDescr ce = new CtentryDescr();
                    // ce.setId(new CtentryDescrPK(entityItem.getEntity().getCtentryId(), l.getLocaleId()));
                    ce.setName("test");
                    ce.setLocale(l);
                    ce.setCtentry(entityItem.getEntity());
                    // c.createEntityItem(ce);
                    c.addEntity(ce);
                    c.refresh();
                }

            }

            @Override
            protected Field<?> createField(final String pid) {

                if (pid.equals("description")) {
                    t.setContainerDataSource(c);
                    t.setVisibleColumns(new Object[] { "name" });
                    t.setTableFieldFactory(new TableFieldFactory() {
                        @Override
                        public Field<?> createField(final Container container, final Object itemId,
                                final Object propertyId, final Component uiContext) {
                            return new TextField((String) propertyId);
                        }
                    });
                    t.setHeight("100px");
                    t.setWidth("250px");
                    t.setEditable(true);
                    t.setImmediate(true);
                    t.setBuffered(true);
                    return t;

                } else {
                    return super.createField(pid);
                }
            }

            @Override
            public void discard() {
                t.discard();
                super.discard();
            }

            @Override
            public void save() throws CommitException {
                t.commit();
                super.save();
            }

        }, "main");

        setService(categoryService);
        super.init();
    }

}
