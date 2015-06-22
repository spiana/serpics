package com.serpics.vaadin.ui.catalog;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.CategoryRelation;
import com.serpics.catalog.data.repositories.CategoryRepository;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.jpacontainer.provider.ServiceContainerFactory;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterDetailTable;
import com.serpics.vaadin.ui.MasterForm;
import com.serpics.vaadin.ui.MultilingualStringConvert;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.ui.Field;


@VaadinComponent("childCategoryRelationTable")
public class ChildCategoryRelationTable extends MasterDetailTable<CategoryRelation, Category> {
    private static final long serialVersionUID = -4806072716873321159L;

    @Autowired
    private CategoryRepository categoryRepository;
      public ChildCategoryRelationTable() {
        super(CategoryRelation.class);
    }

     @Override
    public EntityFormWindow<CategoryRelation> buildEntityWindow() {
    	 EntityFormWindow<CategoryRelation> editorWindow = new EntityFormWindow<CategoryRelation>();
    	 
    	 editorWindow.addTab(new MasterForm<CategoryRelation>(CategoryRelation.class) {

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
        setParentProperty("childCategories");
        entityList.setConverter("childCategory.description", new MultilingualStringConvert());
    }

   
}
