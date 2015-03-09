package com.serpics.vaadin.ui.catalog;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.base.repositories.LocaleRepository;
import com.serpics.base.services.LocaleService;
import com.serpics.catalog.persistence.Category;
import com.serpics.catalog.repositories.CategoryRepository;
import com.serpics.catalog.services.CategoryService;
import com.serpics.core.data.Repository;
import com.serpics.core.service.EntityService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityForm;
import com.serpics.vaadin.ui.EntityTable;
import com.serpics.vaadin.ui.MultilingualStringConvert;
import com.serpics.vaadin.ui.MultilingualTextField;
import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextArea;

@VaadinComponent("categoryTable")
public class CategoryTable extends EntityTable<Category> {
    private static final long serialVersionUID = -8891254200870608192L;

  
    @Autowired
    private transient ChildCategoryRelationTable childCategoryRelatcionTable;

    @Autowired
    private transient ParentCategoryRelationTable parentCategoryRelationTable;

    @Autowired
    private transient CategoryRelationTreeTable categoryRelationTreeTable;

    public CategoryTable() {
        super(Category.class);

    }

  
    @Override
    public void init() {
        super.init();
        setPropertyToShow(new String[] { "code", "url", "description" });

        editorWindow.addTab(new EntityForm<Category>(Category.class) {

            private static final long serialVersionUID = 8129655459345393570L;


            @Override
            public void init() {
                final String[] displayProperties = { "code", "url", "description", "field1",
                        "updated", "created" };
                this.setReadOnlyProperties(new String[] { "updated", "created" });
                setDisplayProperties(displayProperties);
                final FieldFactory fieldFactory = new FieldFactory();
            }


            @Override
            protected Field<?> createField(final String pid) {

                if (pid.equals("description")) {
                    final MultilingualTextField t = new MultilingualTextField();
                    t.setCaption("descrizione");
                    t.setNullRepresentation("");
                    fieldGroup.bind(t, "description");
                    return t;

                } else {
                    return super.createField(pid);
                }
            }

        }, "main");


        editorWindow.addTab(new EntityForm<Category>(Category.class) {
            private static final long serialVersionUID = -7154962966506074107L;

            @Override
            public void init() {
                super.init();
                final String[] displayProperties = { "metaKeyword", "metaDescription" };
                setDisplayProperties(displayProperties);
            }

            @Override
            protected Field<?> createField(final String pid) {

                if (pid.equals("metaDescription")) {
                    final TextArea f = new TextArea();
                    f.setRows(5);
                    f.setNullRepresentation("");
                    f.setWidth("80%");

                    fieldGroup.bind(f, pid);

                    return f;
                }
                return super.createField(pid);
            }
        }, "seo");

        editorWindow.addTab(childCategoryRelatcionTable, "childCategories");
        editorWindow.addTab(parentCategoryRelationTable, "parentCategories");


        editorWindow.setCaption("category.title");

        entityList.setConverter("description", new MultilingualStringConvert());
    }

}
