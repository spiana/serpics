package com.serpics.membership.data.repositories;

import com.serpics.base.data.model.Store;
import com.serpics.core.data.Repository;

public interface StoreRepository extends Repository<Store, Long> {

	public Store findByname(String name);
}
