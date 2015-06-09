package com.serpics.vaadin.ui.catalog;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.CategoryRelation;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityForm;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterTable;
import com.serpics.vaadin.ui.MultilingualStringConvert;
import com.serpics.vaadin.ui.component.MasterDetailField;
import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextArea;


@VaadinComponent("categoryTable")
public class CategoryTable extends MasterTable<Category> {
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
    public EntityFormWindow<Category> buildEntityWindow() {
    	EntityFormWindow<Category> editorWindow = new EntityFormWindow<Category>();
    	editorWindow.addTab(new EntityForm<Category>(Category.class) {

            private static final long serialVersionUID = 8129655459345393570L;


            @Override
            public void init() {
                final String[] displayProperties = { "code", "url", "description", "field1", "childCategories",
                        "updated", "created" };
                this.setReadOnlyProperties(new String[] { "updated", "created" });
                setDisplayProperties(displayProperties);
                final FieldFactory fieldFactory = new FieldFactory();
            }

            @Override
            public void attach() {
            	container.refresh();
            	super.attach();
            }
            
            @Override
            protected Field<?> createField(final String pid) {
            	if (pid.equals("childCategories")) { 
                	MasterDetailField<Category, CategoryRelation> t = 
                			new MasterDetailField<Category, CategoryRelation>(this.entityItem.getContainer(), this.entityItem, pid, 
                					new String[] {"childCategory.code" , "sequence"});
                	fieldGroup.bind(t, pid);
                	return t;
                	
                }else {
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

    	return editorWindow;
    }
  
    @Override
    public void init() {
        super.init();
        setPropertyToShow(new String[] { "code", "url", "description" });
        entityList.setConverter("description", new MultilingualStringConvert());
    }

}
