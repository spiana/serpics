package com.serpics.catalog.facade;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.base.facade.data.MediaData;
import com.serpics.catalog.facade.data.CategoryData;
import com.serpics.catalog.facade.data.PriceData;
import com.serpics.catalog.facade.data.ProductData;

public interface ProductFacade {
	
	public Page<ProductData> listProduct(Pageable page);
	public Page<ProductData> searchProducts(Pageable page, String searchText);
	public Page<ProductData> listProductByCategory(Long cId, Pageable page);
	public Page<ProductData> listProductByBrand(Long brandId, Pageable page);
	
	public ProductData create(ProductData product);
	public ProductData create(ProductData product, Long categoryId, Long brandId);
	public ProductData createWithCategory(ProductData product, Long brandId);
	public ProductData createWithBrand(ProductData product, Long category);
	
	public ProductData updateProduct(ProductData product);
	public void deleteProduct(Long  id);
	
	public void addEntryCategoryParent(Long childId, Long parentId);
	public void addPrice(Long productId, PriceData price);
	public  List<CategoryData>  getParentCategory(ProductData product);
	public ProductData findByName(String name);
	
	public ProductData findById(Long id);
	
	public void addMedia(Long productId, MediaData media);
	
	public ProductData addBrand(Long productId, Long brandId);
	public Page<ProductData> listProductByCategoryCode(String categoryCode, Pageable page);
	
}
