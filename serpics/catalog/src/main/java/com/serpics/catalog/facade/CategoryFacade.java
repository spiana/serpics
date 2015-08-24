package com.serpics.catalog.facade;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.catalog.facade.data.CategoryData;

public interface CategoryFacade {
	public CategoryData findCategoryByCode(String code);
	
	public Page<CategoryData> listCategory(Pageable page);
	public List<CategoryData> listTopCategory();
	public List<CategoryData> listChildCategories(String uuid);
	public CategoryData create(CategoryData category);
	public CategoryData create(CategoryData category, String parentUid);
	public void addCategoryParent(String childUuid, String parentUuid);
	
	
}
