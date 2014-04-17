package com.serpics.vaadin.ui.catalog;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.base.services.LocaleService;
import com.serpics.catalog.persistence.Category;
import com.serpics.catalog.services.CategoryService;
import com.serpics.catalog.services.CtentryDescrService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityForm;
import com.serpics.vaadin.ui.EntityTable;
import com.serpics.vaadin.ui.MultilingualStringConvert;
import com.serpics.vaadin.ui.MultilingualTextField;
import com.vaadin.ui.Field;

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
    public void attach() {
        entityList.setConverter("description", new MultilingualStringConvert());
        super.attach();
    }

    @Override
    public void init() {
        setPropertyToShow(new String[] { "code", "url", "description" });



        editorWindow.addTab(new EntityForm<Category>(Category.class) {

            private static final long serialVersionUID = 8129655459345393570L;

            @Override
            public void init() {
                displayProperties = new String[] { "code", "url", "description", "field1", "field2",
                        "updated", "created" };
                this.setReadOnlyProperties(new String[] { "updated", "created" });
                super.init();
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

        editorWindow.setCaption("category.title");
        setService(categoryService);
        super.init();
    }

}
