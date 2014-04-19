package com.serpics.vaadin.ui.catalog;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.admin.SerpicsContainerFactory;
import com.serpics.catalog.persistence.Category;
import com.serpics.catalog.persistence.CategoryRelation;
import com.serpics.catalog.services.CategoryRelationService;
import com.serpics.catalog.services.CategoryService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityForm;
import com.serpics.vaadin.ui.EntityTableChild;
import com.serpics.vaadin.ui.MultilingualStringConvert;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.SerpicsPersistentContainer;
import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;


@VaadinComponent("parentCategoryRelationTable")
public class ParentCategoryRelationTable extends EntityTableChild<CategoryRelation, Category> {
    private static final long serialVersionUID = -4806072716873321159L;

    @Autowired
    private transient CategoryRelationService categoryRelationService;

    @Autowired
    private transient CategoryService categoryService;

    public ParentCategoryRelationTable() {
        super(CategoryRelation.class);

    }

    @Override
    public void init() {
        cont.addNestedContainerProperty("parentCategory.*");
        setPropertyToShow(new String[] { "parentCategory.code", "parentCategory.description", "sequence" });
        setService(categoryRelationService);


        editorWindow.addTab(new EntityForm<CategoryRelation>(CategoryRelation.class) {

            SerpicsPersistentContainer<Category> categories;

            @Override
            public void init() {
                setDisplayProperties(new String[] { "parentCategory", "sequence" });
                categories = SerpicsContainerFactory.make(Category.class,
                        categoryService);

                super.init();
            }

            @Override
            protected Field<?> createField(final String pid) {

                if (pid.equals("parentCategory")) {
                    final ComboBox combo = new ComboBox(pid);
                    combo.setContainerDataSource(categories);
                    combo.setItemCaptionMode(ItemCaptionMode.PROPERTY);
                    combo.setItemCaptionPropertyId("code");
                    combo.setFilteringMode(FilteringMode.CONTAINS);
                    combo.setImmediate(true);
                    combo.setConverter(new SingleSelectConverter(combo));
                    fieldGroup.bind(combo, pid);
                    return combo;
                } else
                    return super.createField(pid);
            }
        }, "main");

        super.init();
        entityList.setConverter("parentCategory.description", new MultilingualStringConvert());

    }

    @Override
    public void setParentEntity(final EntityItem<Category> parent) {
        super.setParentEntity(parent);
        cont.removeAllContainerFilters();
        cont.addContainerFilter(new Compare.Equal("childCategory", parent.getEntity()));
    }

    @Override
    public EntityItem<CategoryRelation> createEntityItem() {
        final CategoryRelation categoryRelation = new CategoryRelation();
        categoryRelation.setChildCategory(parent.getEntity());
        return cont.createEntityItem(categoryRelation);
    }
}
