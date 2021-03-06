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

import com.serpics.catalog.data.model.ProductVariant;
import com.serpics.catalog.data.repositories.CategoryRepository;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterTable;


@VaadinComponent("variantTable")
public class ProductVariantTable extends MasterTable<ProductVariant> {
    private static final long serialVersionUID = 6586616418061870098L;

    private CategoryRepository categoryRepository;
    

    
    public ProductVariantTable() {
        super(ProductVariant.class);
    }

    
    @Override
    public EntityFormWindow<ProductVariant> buildEntityWindow() {
    	VariantEditWindow editorWindow = new VariantEditWindow();
    	return  editorWindow;
    }
    
    @Override
    public void init() {
        super.init();
        setPropertyToShow(new String[] { "code", "name","description" , "buyable" });
    }

    
    
  
    
	


}

