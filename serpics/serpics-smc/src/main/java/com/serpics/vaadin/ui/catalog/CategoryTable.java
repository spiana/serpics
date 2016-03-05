package com.serpics.vaadin.ui.catalog;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.data.model.Category;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.data.utils.I18nUtils;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterForm;
import com.serpics.vaadin.ui.MasterTable;
import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.ui.Field;


@VaadinComponent("categoryTable")
public class CategoryTable extends MasterTable<Category> {
    private static final long serialVersionUID = -8891254200870608192L;

  
    @Autowired
    private transient ChildCategoryRelationTable childCategoryRelatcionTable;

    @Autowired
    private transient CategoryRelationTreeTable categoryRelationTreeTable;

    public CategoryTable() {
        super(Category.class);

    }

    @Override
    public EntityFormWindow<Category> buildEntityWindow() {
    	EntityFormWindow<Category> editorWindow = new EntityFormWindow<Category>();
    	editorWindow.addTab(new MasterForm<Category>(Category.class) {

            private static final long serialVersionUID = 8129655459345393570L;


            @Override
            public void init() {
                final String[] displayProperties = { "code", "name", "description","published","primaryImage", "childCategories",
                        "updated", "created" };
                this.setReadOnlyProperties(new String[] { "updated", "created" });
                setDisplayProperties(displayProperties);
                final FieldFactory fieldFactory = new FieldFactory();
            }

            @Override
            public void attach() {
            	super.attach();
            }
            
            @Override
            protected Field<?> createField(final String pid) {
//            	if (pid.equals("childCategories")) { 
//                	MasterDetailField<Category, CategoryRelation> t = 
//                			new MasterDetailField<Category, CategoryRelation>(this.entityItem.getContainer(), this.entityItem, pid, 
//                					new String[] {"childCategory.code" , "sequence"});
//                	fieldGroup.bind(t, pid);
//                	return t;
//                	
//                }else {
                    return super.createField(pid);
//                }
            }

        },  I18nUtils.getMessage("category", ""));


        editorWindow.addTab(new MasterForm<Category>(Category.class) {
            private static final long serialVersionUID = -7154962966506074107L;

            @Override
            public void init() {
                super.init();
                final String[] displayProperties = { "metaKeyword", "metaDescription" };
                setDisplayProperties(displayProperties);
            }
        }, I18nUtils.getMessage("seo",""));

        editorWindow.addTab(childCategoryRelatcionTable, I18nUtils.getMessage("childcategory",""));
        editorWindow.setCaption(I18nUtils.getMessage("category.title",""));

    	return editorWindow;
    }
  
    @Override
    public void init() {
        super.init();
        setPropertyToShow(new String[] { "code", "name" , "published"});
      }

}
