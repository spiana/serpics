package com.serpics.membership.services;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.commerce.service.AbstractCommerceEntityService;
import com.serpics.core.data.Repository;
import com.serpics.membership.data.model.PrimaryAddress;
import com.serpics.membership.data.repositories.PrimaryAddressRepository;

@Service("primaryAdrressService")
@Scope("store")
@Transactional(readOnly=true)
public class PrimaryAddressServiceImpl extends  AbstractCommerceEntityService<PrimaryAddress , Long> implements PrimaryAddressService{

	@Resource
	PrimaryAddressRepository primaryAddressRepository;
	
	@Override
	public Repository<PrimaryAddress, Long> getEntityRepository() {
		return  primaryAddressRepository;
		
	}

}
