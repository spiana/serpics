package com.serpics.jaxrs;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.data.domain.Page;

import com.serpics.catalog.facade.data.CategoryData;
import com.serpics.catalog.facade.data.PriceData;
import com.serpics.catalog.facade.data.ProductData;

public interface ProductRestService {

	public Response insertCategory(ProductData product, Long categoryId);
	public Response insert(ProductData product);
	public Response update(ProductData product);
	public ProductData getProduct(Long productid);
	public ProductData getProductByName(String name);
	public Response delete(Long id);
	public List<CategoryData> getCategory(Long id);
	public Response addCategory(Long productId, Long categoryId);
	public Response addPrice(Long productId, PriceData price);
	public Page<ProductData> findAll(int page, int size);
	public Page<ProductData> findByCategory(Long categoryId, int page, int size);
	
}
