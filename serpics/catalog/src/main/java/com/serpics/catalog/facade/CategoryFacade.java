package com.serpics.catalog.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.catalog.facade.data.CategoryData;

public interface CategoryFacade {
	public CategoryData findCategoryByCode(String code);
	public Page<CategoryData> listCategory(Pageable page);
	public CategoryData addCategory(CategoryData category);
	public CategoryData addCategory(CategoryData category, String parentUid);
	public void addCategoryParent(String childUuid, String parentUiid);
	
	
}
