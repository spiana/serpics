package com.serpics.catalog.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.catalog.facade.data.CategoryData;
import com.serpics.catalog.facade.data.ProductData;

public interface CtentryFacade {
	public CategoryData findCategoryByCode(String code);
	public Page<CategoryData> listCategory(Pageable page);
	public CategoryData addCategory(CategoryData category);
	public CategoryData addCategory(CategoryData category, String parentUid);
	public void addCategoryParent(String childUuid, String parentUiid);
	
	public Page<ProductData> listProduct(Pageable page);
	public ProductData addProduct(ProductData product);
	public ProductData addProduct(ProductData product, String categoryUid);
	public void addEntryCategoryParent(String childUuid, String parentUiid);
}
