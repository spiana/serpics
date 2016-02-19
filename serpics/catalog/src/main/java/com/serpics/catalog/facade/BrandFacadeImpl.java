package com.serpics.catalog.facade;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.catalog.data.model.Brand;
import com.serpics.catalog.facade.data.BrandData;
import com.serpics.catalog.services.BrandService;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.stereotype.StoreFacade;

@StoreFacade("brandFacade")
@Transactional(readOnly = true)
public class BrandFacadeImpl implements BrandFacade {
	@Autowired
	BrandService brandService;

	@Resource(name = "brandConverter")
	AbstractPopulatingConverter<Brand, BrandData> brandConverter;

	protected Brand buildBrand(BrandData brandData, Brand entity) {

			entity.setCode(brandData.getCode());
			entity.setLogoSrc(brandData.getLogo());
			entity.setPublished(brandData.isPublished());
			
		return entity;
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
	public BrandData findBrandByName(String name) {

		Brand brand = null;
		BrandData brandData = null;
		brand = brandService.findOneByName(name);

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
