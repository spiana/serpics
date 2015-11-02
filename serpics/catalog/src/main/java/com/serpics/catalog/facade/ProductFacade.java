package com.serpics.catalog.facade;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.catalog.facade.data.CategoryData;
import com.serpics.catalog.facade.data.MediaData;
import com.serpics.catalog.facade.data.PriceData;
import com.serpics.catalog.facade.data.ProductData;

public interface ProductFacade {

	
	public Page<ProductData> listProduct(Pageable page);
	public Page<ProductData> listProductByCategory(Long cId, Pageable page);
	
	public ProductData create(ProductData product);
	public ProductData create(ProductData product, Long categoryId);
	public ProductData updateProduct(ProductData product);
	public void deleteProduct(Long  id);
	
	public void addEntryCategoryParent(Long childId, Long parentId);
	public void addPrice(Long productId, PriceData price);
	public  List<CategoryData>  getParentCategory(ProductData product);
	public ProductData findByName(String name);
	
	
	public void addMedia(Long productId, MediaData media);
	
}
