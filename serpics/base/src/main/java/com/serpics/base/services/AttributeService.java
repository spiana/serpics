package com.serpics.base.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.serpics.base.AvailableforType;
import com.serpics.base.data.model.BaseAttribute;

public interface AttributeService {

    public List<BaseAttribute> findbyAvailablefor(AvailableforType availablefor, Pageable page);

}
