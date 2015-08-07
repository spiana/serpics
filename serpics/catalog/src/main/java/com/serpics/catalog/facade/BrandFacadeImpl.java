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
@Transactional(readOnly=true)
public class BrandFacadeImpl implements BrandFacade {
	@Autowired 
	BrandService brandService;
	
	@Resource(name="brandConverter")
	AbstractPopulatingConverter<Brand,	BrandData> brandConverter;
	
	@Override
	public BrandData addBrand(BrandData brand) {
		Brand b = new Brand();
		b.setId(brand.getId());
		b.setLogoSrc(brand.getName());
		b.setName(brand.getName());
		b = brandService.create(b);
		brand = brandConverter.convert(b);
		return brand;
	}
	
	
	@Override
	public Page<BrandData> findAll(Pageable page) {
		List<BrandData> l = new ArrayList<BrandData>();
		Page<Brand> brands = brandService.findAll(page);
		for (Brand brand : brands) {
			l.add(brandConverter.convert(brand));
		}
		
		Page<BrandData> list = new PageImpl<BrandData>(l, page, brands.getTotalElements());
		return list;
	}
	
}