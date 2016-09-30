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

