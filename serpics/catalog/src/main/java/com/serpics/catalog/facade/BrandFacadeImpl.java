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

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.commerce.session.CommerceSessionContext;
import com.serpics.catalog.data.model.Brand;
import com.serpics.catalog.facade.data.BrandData;
import com.serpics.catalog.services.BrandService;
import com.serpics.core.Engine;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.i18n.data.model.MultilingualString;
import com.serpics.i18n.data.model.MultilingualText;
import com.serpics.stereotype.StoreFacade;

@StoreFacade("brandFacade")
@Transactional(readOnly = true)
public class BrandFacadeImpl implements BrandFacade {
	@Autowired
	BrandService brandService;
	
	@Autowired
	Engine<CommerceSessionContext> engine;

	@Resource(name = "brandConverter")
	AbstractPopulatingConverter<Brand, BrandData> brandConverter;

	protected Brand buildBrand(BrandData brandData, Brand brand) {

		brand.setCode(brandData.getCode());
		brand.setLogoSrc(brandData.getLogo());
		brand.setPublished(brandData.isPublished());
		
		String locale = engine.getCurrentContext().getLocale().getLanguage();
		final MultilingualText description = new MultilingualText(locale, brandData.getDescription());
		
		brand.setDescription(description);

		brand.setMetaDescription(new MultilingualString(locale, brandData.getMetaDescription()));
		brand.setMetaKeyword(new MultilingualString(locale, brandData.getMetaKeyword()));
		brand.setName(new MultilingualString(locale, brandData.getName()));
		return brand;

	}

	@Override
	@Transactional
	public BrandData addBrand(BrandData brandData) {

		Brand brand = buildBrand(brandData, new Brand());
		brand = brandService.create(brand);
		brandData = brandConverter.convert(brand);
		return brandData;
	}

	@Override
	@Transactional
	public BrandData updateBrand(BrandData brandData) {

		Brand brand = brandService.findOne(brandData.getId());
		brand = buildBrand(brandData, brand);
		brand = brandService.update(brand);
		brandData = brandConverter.convert(brand);
		return brandData;

	}

	@Override
	public Page<BrandData> pageBrand(Pageable page) {

		List<BrandData> l = new ArrayList<BrandData>();
		Page<Brand> brands = brandService.findAll(page);
		for (Brand brand : brands) {
			l.add(brandConverter.convert(brand));
		}

		Page<BrandData> list = new PageImpl<BrandData>(l, page, brands.getTotalElements());
		return list;
	}

	@Override
	public List<BrandData> listBrand() {
		List<BrandData> l = new ArrayList<BrandData>();
		List<Brand> brands = brandService.findAll();
		for (Brand brand : brands) {
			l.add(brandConverter.convert(brand));
		}
		return l;
	}

	@Override
	@Transactional
	public void deleteBrand(Long id) {

		brandService.delete(id);

	}

	@Override
	public BrandData findBrandByCode(String code) {

		Brand brand = null;
		BrandData brandData = null;
		brand = brandService.findOneByCode(code);

		if (brand != null) {
			brandData = brandConverter.convert(brand);
		}
		return brandData;

	}

	@Override
	public BrandData findBrandById(Long id) {

		Brand b = null;
		BrandData brand = null;
		b = brandService.findOne(id);

		if (b != null) {
			brand = brandConverter.convert(b);
		}
		return brand;
	}

}
