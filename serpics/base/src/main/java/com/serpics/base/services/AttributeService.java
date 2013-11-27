package com.serpics.base.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.serpics.base.persistence.BaseAttribute;
import com.serpics.core.service.EntityService;

public interface AttributeService extends EntityService<BaseAttribute, Long>{

	public List<BaseAttribute> findbyAvailablefor(String availablefor , Pageable page);

}
