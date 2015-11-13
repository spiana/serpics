package com.serpics.jaxrs;

import javax.ws.rs.core.Response;

import com.serpics.catalog.facade.data.PriceData;
import com.serpics.catalog.facade.data.ProductData;

public interface ProductRestService {

	public Response insertCategory(ProductData product, Long categoryId);
	public Response insertBrand(ProductData product, Long brandId);
	public Response insert(ProductData product, Long categoryId, Long brandId);
	public Response insert(ProductData product);
	public Response update(ProductData product);
	public Response getProduct(Long productid);
	public Response getProductByName(String name);
	public Response delete(Long id);
	public Response getCategory(Long id);
	public Response addCategory(Long productId, Long categoryId);
	public Response addBrand(Long productId, Long brandId);
	public Response addPrice(Long productId, PriceData price);
	public Response findAll(int page, int size);
	public Response findByCategory(Long categoryId, int page, int size);
	public Response findByBrand(Long brandId, int page, int size);
	
}
