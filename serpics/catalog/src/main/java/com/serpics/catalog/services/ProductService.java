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
package com.serpics.catalog.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.catalog.data.model.Brand;
import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.CtentryMedia;
import com.serpics.catalog.data.model.Product;
import com.serpics.core.service.EntityService;

public interface ProductService extends EntityService<Product, Long>{
	
	public Product create(Product product, Category category, Brand brand);
	
	public Product addParentCategory(Product product, Category category);
	
	public Page<Product> findProductByCategory(Category category,Pageable pagination);
	
	public Page<Product> findProductByBrand(Brand brand,Pageable pagination);
	
	public List<Product> listProductByCategory(Category category);
	public List<Product> listProductByBrand(Brand brand);
	
	public Product addMedia(Product product, CtentryMedia media);
	
	public Product findByName(String name);

	public Product addBrand(Product product, Brand brand);
	
	public Page<Product> findProductsBySearch(String searchText, Pageable pagination);
	
	public Product findByCode(String code);
	
}
