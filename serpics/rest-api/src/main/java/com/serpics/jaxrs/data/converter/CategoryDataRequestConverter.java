package com.serpics.jaxrs.data.converter;


import com.serpics.catalog.facade.data.CategoryData;
import com.serpics.core.facade.Populator;
import com.serpics.jaxrs.data.CategoryDataRequest;

public class CategoryDataRequestConverter implements Populator<CategoryDataRequest, CategoryData>{

	@Override
	public void populate(CategoryDataRequest source, CategoryData target) {
		target.setCode(source.getCode());
		target.setDescription(source.getDescription());
		target.setPublished(source.isPublished());
		
	}
}
