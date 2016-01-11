package com.serpics.commerce.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.base.data.model.Store;
import com.serpics.commerce.data.model.Paymethod;
import com.serpics.core.data.Repository;

public interface PaymethodRepository extends Repository<Paymethod, Long> {
	
	@Query("select m from Paymethodlookup as p join p.paymethod as m where p.store = :store and p.active = 1")
	public List<Paymethod> findActivePaymentmethod(@Param("store") Store store);
}
