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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.commerce.service.AbstractCommerceEntityService;
import com.serpics.catalog.data.model.Brand;
import com.serpics.catalog.data.repositories.BrandRepository;
import com.serpics.catalog.data.specification.BrandSpecification;
import com.serpics.core.data.Repository;

@Service("brandService")
@Scope("store")
@Transactional(readOnly = true)
public class BrandServiceImpl extends AbstractCommerceEntityService<Brand, Long> implements BrandService {
	@Autowired
	BrandRepository brandRepository;

	@Override
	public Repository<Brand, Long> getEntityRepository() {
		return brandRepository;
	}

	@Override
	public Brand findOneByCode(String code) {

		return brandRepository.findOne(BrandSpecification.findOneByCode(code));
	}

	@Override
	public int getBrandProduct(Brand brand) {
		return brandRepository.getCountBrandProduct(brand);
	}
	
}
