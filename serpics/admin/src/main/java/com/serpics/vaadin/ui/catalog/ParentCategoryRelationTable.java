package com.serpics.vaadin.ui.catalog;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.CategoryRelation;
import com.serpics.catalog.data.repositories.CategoryRepository;
import com.serpics.vaadin.jpacontainer.ServiceContainerFactory;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterDetailTable;
import com.serpics.vaadin.ui.MasterForm;
import com.serpics.vaadin.ui.MultilingualStringConvert;
import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;



//@VaadinComponent("parentCategoryRelationTable")
public class ParentCategoryRelationTable extends MasterDetailTable<CategoryRelation, Category> {
    private static final long serialVersionUID = -4806072716873321159L;

    @Autowired
    private CategoryRepository categoryRepository;
  
    public ParentCategoryRelationTable() {
        super(CategoryRelation.class);

    }
    
    @Override
    public EntityFormWindow<CategoryRelation> buildEntityWindow() {
    	 EntityFormWindow<CategoryRelation> editorWindow = new EntityFormWindow<CategoryRelation>();
    	 editorWindow.addTab(new MasterForm<CategoryRelation>(CategoryRelation.class) {

    		 @Override
             public void init() {
                 super.init();
                 setDisplayProperties(new String[] { "parentCategory", "sequence" });
                
             }
             @Override
             protected Field<?> createField(final String pid) {
                 if (pid.equals("parentCategory")) {
                     final ComboBox combo = new ComboBox(pid);
                     combo.setContainerDataSource(ServiceContainerFactory.make(Category.class ));
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
    	 
    	 return editorWindow;
    }
   
    @Override
    public void init() {
        super.init();
        container.addNestedContainerProperty("parentCategory.*");
        setPropertyToShow(new String[] { "parentCategory.code", "parentCategory.description", "sequence" });
        setParentProperty("childCategory");
        entityList.setConverter("parentCategory.description", new MultilingualStringConvert());
    }

}
