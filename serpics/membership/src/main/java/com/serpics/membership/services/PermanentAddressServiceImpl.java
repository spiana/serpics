package com.serpics.membership.services;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.commerce.service.AbstractCommerceEntityService;
import com.serpics.core.data.Repository;
import com.serpics.membership.data.model.PermanentAddress;
import com.serpics.membership.data.repositories.PermanentAddressRepository;

@Service("permanentAdrressService")
@Scope("store")
@Transactional(readOnly=true)
public class PermanentAddressServiceImpl extends  AbstractCommerceEntityService<PermanentAddress , Long> implements PermanentAddressService{

	@Resource
	PermanentAddressRepository permanentAddressRepository;
	
	@Override
	public Repository<PermanentAddress, Long> getEntityRepository() {
		return  permanentAddressRepository;
		
	}

}
