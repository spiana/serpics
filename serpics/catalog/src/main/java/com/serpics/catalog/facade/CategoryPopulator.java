package com.serpics.catalog.facade;

import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.facade.data.CategoryData;
import com.serpics.core.facade.Populator;


public class CategoryPopulator implements Populator<Category, CategoryData>    {
 
	@Override
	public void populate(Category source, CategoryData target)  {
		
		target.setMetaKey(source.getMetaKeyword());
		target.setMetaDescription(source.getMetaDescription());
		
		target.setCode(source.getCode());
		target.setUuid(source.getUuid());
		target.setUrl(source.getUrl());
		if(source.getDescription() != null)
			target.setDescription(source.getDescription().getText("it"));
	}

}
