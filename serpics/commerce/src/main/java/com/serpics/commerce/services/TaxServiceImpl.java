package com.serpics.commerce.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.data.model.Store;
import com.serpics.commerce.data.model.Tax;
import com.serpics.commerce.data.repositories.TaxRepository;

@Service("taxService")
@Scope("store")
@Transactional(readOnly = true)
public class TaxServiceImpl implements TaxService {
	
	@Resource
	TaxRepository taxRepository;

	@Override
	public Tax findActiveTax(Store store) {
		
		Tax result = null;
		
		List<Tax> taxes = taxRepository.findActiveTax(store);
		
		if (!taxes.isEmpty()){
			result = taxes.get(0);
		}
		return result;
		
	}

}
