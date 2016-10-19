/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
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
	public Response findByCategoryCode(String categoryCode, int page, int size, String ssid);
	public Response getProductByCode(String productCode, String ssid);
	
}
