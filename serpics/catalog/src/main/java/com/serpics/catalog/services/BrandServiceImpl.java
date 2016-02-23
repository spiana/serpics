package com.serpics.catalog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.catalog.data.model.Brand;
import com.serpics.catalog.data.repositories.BrandRepository;
import com.serpics.catalog.data.specification.BrandSpecification;
import com.serpics.commerce.service.AbstractCommerceEntityService;
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
