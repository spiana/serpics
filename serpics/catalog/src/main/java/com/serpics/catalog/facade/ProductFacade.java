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
	
	//by Category
	public Page<ProductData> pageProductByCategoryId(Long cId, Pageable page);
	public Page<ProductData> pageProductByCategoryCode(String categoryCode, Pageable page);
	public List<ProductData> listProductByCategoryId(Long categoryId);
	public List<ProductData> listProductByCategoryCode(String categoryCode);
	public List<CategoryData>  getParentCategory(ProductData product);
	//by Brand
	public Page<ProductData> pageProductByBrandId(Long brandId, Pageable page);
	public Page<ProductData> pageProductByBrandCode(String brandCode, Pageable page);
	public List<ProductData> listProductByBrandId(Long brandId);
	public List<ProductData> listProductByBrandCode(String brandCode);
	
	
	public ProductData create(ProductData product);
	public ProductData create(ProductData product, Long categoryId, Long brandId);
	public ProductData createWithCategory(ProductData product, Long brandId);
	public ProductData createWithBrand(ProductData product, Long category);
	
	public ProductData updateProduct(ProductData product);
	public void deleteProduct(Long  id);
	
	public void addEntryCategoryParent(Long childId, Long parentId);
	public ProductData addBrand(Long productId, Long brandId);
	public void addPrice(Long productId, PriceData price);
	
	
	
	public ProductData findByName(String name);
	public ProductData findByCode(String code);
	public ProductData findById(Long id);
	
	public void addMedia(Long productId, MediaData media);
	
	
	
	
}
