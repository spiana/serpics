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

import com.serpics.jaxrs.data.BrandDataRequest;

public interface BrandRestService {
	
	public Response addBrand(BrandDataRequest brand,String ssid);
	public Response updateBrand(Long brandId,BrandDataRequest brand,String ssid);
	public Response deleteBrand(Long id,String ssid);
	public Response findAllpage(int page , int size,String ssid);
	public Response findBrandById(Long id,String ssid);
	public Response findBrandByName(String name,String ssid);
	public Response findAllList(String ssid);
	public Response findBrandProductsById(Long id,String ssid);
	public Response findBrandProductsByCode(String code,String ssid);
	public Response brandProductsByCodePage(int page , int size,String brandCode, String ssid);
	public Response brandProductsByIdPage(int page , int size, Long brandId, String ssid);
	

	
	

}
