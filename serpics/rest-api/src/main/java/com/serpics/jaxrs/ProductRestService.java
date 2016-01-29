package com.serpics.jaxrs;

import javax.ws.rs.core.Response;

import com.serpics.jaxrs.data.PriceDataRequest;
import com.serpics.jaxrs.data.ProductDataRequest;

public interface ProductRestService {

	public Response insertCategory(ProductDataRequest product, Long categoryId);
	public Response insertBrand(ProductDataRequest product, Long brandId);
	public Response insert(ProductDataRequest product, Long categoryId, Long brandId);
	public Response insert(ProductDataRequest product);
	public Response update(ProductDataRequest product);
	public Response getProduct(Long productid);
	public Response getProductByName(String name);
	public Response delete(Long id);
	public Response getCategory(Long id);
	public Response addCategory(Long productId, Long categoryId);
	public Response addBrand(Long productId, Long brandId);
	public Response addPrice(Long productId, PriceDataRequest priceDataRequest);
	public Response findAll(int page, int size);
	public Response findByCategory(Long categoryId, int page, int size);
	public Response findByBrand(Long brandId, int page, int size);
	public Response findBySearch(String searchText, int page, int size);
	
}
