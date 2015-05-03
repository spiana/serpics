package com.serpics.vaadin.ui.catalog;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.data.model.CategoryRelation;
import com.serpics.catalog.repositories.CategoryRelationRepository;
import com.serpics.catalog.repositories.CategoryRepository;
import com.serpics.catalog.services.CategoryRelationService;
import com.serpics.catalog.services.CategoryService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityComponent;
import com.serpics.vaadin.ui.MultilingualStringConvert;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.provider.ServiceContainerFactory;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;


@VaadinComponent("categoryRelationTreeTable")
public class CategoryRelationTreeTable extends CustomComponent implements EntityComponent<CategoryRelation> {
	
    private static final long serialVersionUID = -4806072716873321159L;

   
    private JPAContainer<CategoryRelation> cont;
    
    @Autowired
    private CategoryRelationRepository categoryRelationRepository;
   
    TreeTable treeTable = new TreeTable();

    private boolean initialized = false;

    @Override
    public Class<CategoryRelation> getEntityType() {
    	return CategoryRelation.class;
    }
    @Override
    public void init() {
        cont = ServiceContainerFactory.make(CategoryRelation.class);
        cont.addNestedContainerProperty("parentCategory.*");
        cont.addNestedContainerProperty("childCategory.*");
        // cont.setParentProperty("parentCategory");

        final String [] propertyToShow=  { "parentCategory.code", "childCategory.code", "childCategory.description",
        "sequence" };
        treeTable.setContainerDataSource(cont);
        treeTable.setVisibleColumns(propertyToShow);



        treeTable.setConverter("childCategory.description", new MultilingualStringConvert());

        final VerticalLayout v = new VerticalLayout();
        v.setSizeFull();
        v.addComponent(treeTable);

        setCompositionRoot(v);
        setSizeFull();
        initialized = true;

    }



    @Override
    public boolean isInitialized() {

        return initialized;
    }

    @Override
    public void save() throws CommitException {
        // TODO Auto-generated method stub

    }

    @Override
    public void discard() {
        // TODO Auto-generated method stub

    }



	
}
