package com.serpics.base.data.repositories;

import java.util.List;

import com.serpics.base.AvailableforType;
import com.serpics.base.data.model.BaseAttribute;
import com.serpics.core.data.Repository;

public interface BaseAttributeRepository extends Repository<BaseAttribute, Long>{

	public List<BaseAttribute> findByAvailablefor(AvailableforType availablefor) ;

}
