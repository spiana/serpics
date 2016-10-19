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

import com.serpics.catalog.facade.data.BrandData;

public interface BrandFacade {
	
	public BrandData addBrand(BrandData entity);
	public BrandData updateBrand(BrandData entity);
	public void deleteBrand(Long id);
	
	public Page<BrandData> pageBrand(Pageable page);
	public BrandData findBrandById(Long id);
	public BrandData findBrandByCode(String code);
	public List<BrandData> listBrand();
	
}
