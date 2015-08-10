package com.serpics.catalog.facade;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.catalog.facade.data.CategoryData;
import com.serpics.catalog.facade.data.PriceData;
import com.serpics.catalog.facade.data.ProductData;

public interface CtentryFacade {
	public CategoryData findCategoryByCode(String code);
	public Page<CategoryData> listCategory(Pageable page);
	public CategoryData addCategory(CategoryData category);
	public CategoryData addCategory(CategoryData category, String parentUid);
	public void addCategoryParent(String childUuid, String parentUiid);
	
	public Page<ProductData> listProduct(Pageable page);
	public Page<ProductData> listProductByCategory(String cUuid, Pageable page);
	public ProductData addProduct(ProductData product);
	public ProductData addProduct(ProductData product, String categoryUid);
	public void addEntryCategoryParent(String childUuid, String parentUiid);
	public void addPrice(String productUuid, PriceData price);
	public  List<CategoryData>  getParentCategory(ProductData product);
}
