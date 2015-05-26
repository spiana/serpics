package com.serpics.catalog.data.interceptors;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.data.model.Catalog;
import com.serpics.catalog.data.model.Category;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.Engine;
import com.serpics.core.data.CreateInterceptor;

public class CategoryCreateInterceptor implements CreateInterceptor<Category>{
	
	@Autowired
	CommerceEngine engine;
	
	@Override
	public void beforeCreate(Category entity) {
		entity.setCatalog((Catalog) engine.getCurrentContext().getCatalog());
	}

	@Override
	public void afterCreate(Category entity) {
		// TODO Auto-generated method stub
		
	}

}
