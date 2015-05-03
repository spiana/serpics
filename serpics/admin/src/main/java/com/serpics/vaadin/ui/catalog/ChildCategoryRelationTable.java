package com.serpics.vaadin.ui.catalog;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.CategoryRelation;
import com.serpics.catalog.repositories.CategoryRelationRepository;
import com.serpics.catalog.repositories.CategoryRepository;
import com.serpics.catalog.services.CategoryRelationService;
import com.serpics.catalog.services.CategoryService;
import com.serpics.core.data.Repository;
import com.serpics.core.data.RepositoryInitializer;
import com.serpics.core.service.EntityService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityForm;
import com.serpics.vaadin.ui.EntityFormWindow;
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

@VaadinComponent("childCategoryRelationTable")
public class ChildCategoryRelationTable extends EntityTableChild<CategoryRelation, Category> {
    private static final long serialVersionUID = -4806072716873321159L;

    @Autowired
    private CategoryRepository categoryRepository;
      public ChildCategoryRelationTable() {
        super(CategoryRelation.class);
    }

     @Override
    public EntityFormWindow<CategoryRelation> buildEntityWindow() {
    	 EntityFormWindow<CategoryRelation> editorWindow = new EntityFormWindow<CategoryRelation>();
    	 
    	 editorWindow.addTab(new EntityForm<CategoryRelation>(CategoryRelation.class) {

             JPAContainer<Category> categories;

             @Override
             public void init() {
                 super.init();
                 setDisplayProperties(new String[] { "childCategory", "sequence" });
                 categories = ServiceContainerFactory.make(Category.class );
             }

             @Override
             protected Field<?> createField(final String pid) {
//
//                 if (pid.equals("childCategory")) {
//                     final ComboBox combo = new ComboBox("childCategory");
//                     combo.setContainerDataSource(categories);
//                     combo.setItemCaptionMode(ItemCaptionMode.PROPERTY);
//                     combo.setItemCaptionPropertyId("code");
//                     combo.setFilteringMode(FilteringMode.CONTAINS);
//                     combo.setImmediate(true);
//                     combo.setConverter(new SingleSelectConverter(combo));
//                     fieldGroup.bind(combo, "childCategory");
//                     return combo;
//                 } else
                     return super.createField(pid);
             }
         }, "main");

    	 return editorWindow;
    }
     
    @Override
    public void init() {
        super.init();
        container.addNestedContainerProperty("childCategory.*");
        setPropertyToShow(new String[] { "childCategory.code", "childCategory.description", "sequence" });
        setParentProperty("parentCategory");
        entityList.setConverter("childCategory.description", new MultilingualStringConvert());
    }

    @Override
    public EntityItem<CategoryRelation> createEntityItem() {
        final CategoryRelation categoryRelation = new CategoryRelation();
        categoryRelation.setParentCategory(parent.getEntity());
        return container.createEntityItem(categoryRelation);
    }
}
