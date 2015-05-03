package com.serpics.membership.repositories;

import com.serpics.core.data.Repository;
import com.serpics.membership.data.model.Store;

public interface StoreRepository extends Repository<Store, Long> {

	public Store findByname(String name);
}
