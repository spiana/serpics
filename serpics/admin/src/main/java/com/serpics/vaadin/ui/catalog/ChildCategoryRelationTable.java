package com.serpics.vaadin.ui.catalog;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.CategoryRelation;
import com.serpics.catalog.data.repositories.CategoryRepository;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.data.utils.I18nUtils;
import com.serpics.vaadin.jpacontainer.ServiceContainerFactory;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterDetailTable;
import com.serpics.vaadin.ui.MasterForm;
import com.serpics.vaadin.ui.converters.MultilingualFieldConvert;
import com.vaadin.addon.jpacontainer.JPAContainer;


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
           
         }, I18nUtils.getMessage("childcategory.main", ""));
    	 editorWindow.setCaption(I18nUtils.getMessage("childcategory.title",""));
    	 return editorWindow;
    }
     
    @Override
    public void init() {
        super.init();
        container.addNestedContainerProperty("childCategory.*");
        setPropertyToShow(new String[] { "childCategory.code", "childCategory.description", "sequence" });
        setParentProperty("childCategories");
        entityList.setConverter("childCategory.description", new MultilingualFieldConvert());
    }

   
}
