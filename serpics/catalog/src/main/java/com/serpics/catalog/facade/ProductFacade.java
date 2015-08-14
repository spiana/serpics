package com.serpics.catalog.facade;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.catalog.facade.data.CategoryData;
import com.serpics.catalog.facade.data.PriceData;
import com.serpics.catalog.facade.data.ProductData;

public interface ProductFacade {

	
	public Page<ProductData> listProduct(Pageable page);
	public Page<ProductData> listProductByCategory(String cUuid, Pageable page);
	
	public ProductData addProduct(ProductData product);
	public ProductData addProduct(ProductData product, String categoryUid);
	public ProductData updateProduct(ProductData product);
	public void deleteProduct(String  uuid);
	
	public void addEntryCategoryParent(String childUuid, String parentUiid);
	public void addPrice(String productUuid, PriceData price);
	public  List<CategoryData>  getParentCategory(ProductData product);
	public ProductData findByName(String name);
	
	
}
