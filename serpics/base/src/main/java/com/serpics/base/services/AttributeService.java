package com.serpics.base.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.serpics.base.AvailableforType;
import com.serpics.base.data.model.BaseAttribute;
import com.serpics.core.service.EntityService;

public interface AttributeService extends EntityService<BaseAttribute, Long>{

    public List<BaseAttribute> findbyAvailablefor(AvailableforType availablefor, Pageable page);

}
