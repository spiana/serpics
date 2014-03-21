package com.serpics.vaadin.ui.catalog;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.persistence.Category;
import com.serpics.catalog.services.CategoryService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityForm;
import com.serpics.vaadin.ui.EntityTable;

@VaadinComponent("categoryTable")
public class CategoryTable extends EntityTable<Category> {
    private static final long serialVersionUID = -8891254200870608192L;

    @Autowired
    private CategoryService categoryService;

    public CategoryTable() {
        super(Category.class);

    }

    @Override
    public void init() {
        setPropertyToShow(new String[] { "code", "url" });

        editorWindow.addTab(new EntityForm<Category>(Category.class) {
            private static final long serialVersionUID = 8129655459345393570L;

            @Override
            public void init() {
                displayProperties = new String[] { "code", "url", "field1", "field2", "field3", "updated", "created" };
                this.setReadOnlyProperties(new String[] { "updated", "created" });
                super.init();
            }


        }, "main");

        setService(categoryService);
        super.init();
    }

}
