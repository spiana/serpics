package com.serpics.jaxrs;

import javax.ws.rs.core.Response;

import com.serpics.jaxrs.data.PriceDataRequest;
import com.serpics.jaxrs.data.ProductDataRequest;

public interface ProductRestService {

	public Response insertCategory(ProductDataRequest product, Long categoryId, String ssid);
	public Response insertBrand(ProductDataRequest product, Long brandId, String ssid);
	public Response insert(ProductDataRequest product, Long categoryId, Long brandId, String ssid);
	public Response insert(ProductDataRequest product, String ssid);
	public Response update(ProductDataRequest product, String ssid);
	public Response getProduct(Long productid, String ssid);
	public Response getProductByName(String name, String ssid);
	public Response delete(Long id, String ssid);
	public Response getCategory(Long id, String ssid);
	public Response addCategory(Long productId, Long categoryId, String ssid);
	public Response addBrand(Long productId, Long brandId, String ssid);
	public Response addPrice(Long productId, PriceDataRequest priceDataRequest, String ssid);
	public Response findAll(int page, int size, String ssid);
	public Response findByCategory(Long categoryId, int page, int size, String ssid);
	public Response findByBrand(Long brandId, int page, int size, String ssid);
	public Response findBySearch(String searchText, int page, int size, String ssid);
	
}
