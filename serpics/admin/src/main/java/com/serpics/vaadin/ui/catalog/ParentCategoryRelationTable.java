package com.serpics.vaadin.ui.catalog;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.persistence.Category;
import com.serpics.catalog.persistence.CategoryRelation;
import com.serpics.catalog.repositories.CategoryRelationRepository;
import com.serpics.catalog.repositories.CategoryRepository;
import com.serpics.catalog.services.CategoryRelationService;
import com.serpics.catalog.services.CategoryService;
import com.serpics.core.data.Repository;
import com.serpics.core.service.EntityService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityForm;
import com.serpics.vaadin.ui.EntityTableChild;
import com.serpics.vaadin.ui.MultilingualStringConvert;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.addon.jpacontainer.provider.ServiceContainerFactory;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;


@VaadinComponent("parentCategoryRelationTable")
public class ParentCategoryRelationTable extends EntityTableChild<CategoryRelation, Category> {
    private static final long serialVersionUID = -4806072716873321159L;

    @Autowired
    private transient CategoryRelationRepository CategoryRelationRepository;

    @Autowired
    private transient CategoryRepository categoryRepository;

    public ParentCategoryRelationTable() {
        super(CategoryRelation.class);

    }

    @Override
    public Repository getRepository() {
    
    	return CategoryRelationRepository;
    }
   
    @Override
    public void init() {
        super.init();
        container.addNestedContainerProperty("parentCategory.*");
        setPropertyToShow(new String[] { "parentCategory.code", "parentCategory.description", "sequence" });

        setParentProperty("childCategory");

        editorWindow.addTab(new EntityForm<CategoryRelation>(CategoryRelation.class) {

            JPAContainer<Category> categories;

            @Override
            public void init() {
                super.init();
                setDisplayProperties(new String[] { "parentCategory", "sequence" });
                categories = ServiceContainerFactory.make(Category.class,
                        categoryRepository);
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
        entityList.setConverter("parentCategory.description", new MultilingualStringConvert());
    }



    @Override
    public EntityItem<CategoryRelation> createEntityItem() {
        final CategoryRelation categoryRelation = new CategoryRelation();
        categoryRelation.setChildCategory(parent.getEntity());
        return container.createEntityItem(categoryRelation);
    }
}
