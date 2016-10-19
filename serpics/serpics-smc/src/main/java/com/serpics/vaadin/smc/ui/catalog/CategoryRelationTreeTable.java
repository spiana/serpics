/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.vaadin.smc.ui.catalog;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.data.model.CategoryRelation;
import com.serpics.catalog.data.repositories.CategoryRelationRepository;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.jpacontainer.ServiceContainerFactory;
import com.serpics.vaadin.ui.EntityComponent;
import com.serpics.vaadin.ui.converters.MultilingualFieldConvert;
import com.vaadin.addon.jpacontainer.JPAContainer;
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



        treeTable.setConverter("childCategory.description", new MultilingualFieldConvert());

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
