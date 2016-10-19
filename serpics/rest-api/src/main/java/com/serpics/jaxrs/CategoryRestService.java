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

import com.serpics.jaxrs.data.CategoryDataRequest;

public interface CategoryRestService {
	
	public Response createParent(CategoryDataRequest category, Long parentId,String ssid);
	public Response create(CategoryDataRequest category,String ssid);
	public Response addParent(Long childId, Long parentId,String ssid);
	public Response getChild(Long parentId,String ssid);
	public Response getCategoryById(Long categoryId,String ssid);
	public Response getCategoryByCode(String categoryCode,String ssid);
	public Response update(Long categoryId,CategoryDataRequest category,String ssid);
	public Response delete(Long categoryId,String ssid);
	public Response findAllPage(int page, int size,String ssid);
	public Response getTop(String ssid);
	public Response getChildByCode(String code, String ssid);
	
	public Response categoryProductsByCodePage(int page , int size,String categoryCode, String ssid);
	public Response categoryProductsByIdPage(int page , int size, Long categoryId, String ssid);
	
}
