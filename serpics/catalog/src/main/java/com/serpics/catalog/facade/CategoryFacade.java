package com.serpics.catalog.facade;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.catalog.facade.data.CategoryData;

public interface CategoryFacade {
	
	public CategoryData findCategoryByCode(String code);
	public CategoryData findCategoryById(Long id);
	
	public Page<CategoryData> listCategory(Pageable page);
	public List<CategoryData> listTopCategory();
	public List<CategoryData> listChildCategories(Long id);
	public CategoryData create(CategoryData category);
	public CategoryData create(CategoryData category, Long parentId);
	public void addCategoryParent(Long childId, Long parentId);
	
	public CategoryData updateCategory(CategoryData category);
	public void deleteCategory(Long id);
	
}
