package com.serpics.membership.services;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.commerce.service.AbstractCommerceEntityService;
import com.serpics.core.data.Repository;
import com.serpics.membership.data.model.BillingAddress;
import com.serpics.membership.data.repositories.BillingAddressRepository;

@Service("billingAdrressService")
@Scope("store")
@Transactional(readOnly=true)
public class BillingAddressServiceImpl extends  AbstractCommerceEntityService<BillingAddress , Long> implements BillingAddressService{

	@Resource
	BillingAddressRepository billingAddressRepository;
	
	@Override
	public Repository<BillingAddress, Long> getEntityRepository() {
		return  billingAddressRepository;
		
	}
	
	
}
