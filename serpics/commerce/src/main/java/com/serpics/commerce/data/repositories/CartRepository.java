package com.serpics.commerce.data.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.base.data.model.Store;
import com.serpics.commerce.data.model.Cart;
import com.serpics.core.data.Repository;
import com.serpics.membership.data.model.Member;

public interface CartRepository extends Repository<Cart, Long> {

	public Cart findByCookie(String cookie);
	
		
	@Query("select c from Cart c where c.user= :user and c.customer = :customer and c.store = :store and c.cookie != :sessionId")
	public Page<Cart> findAllCartByUserId(@Param("user") Member user, @Param("customer") Member customer, @Param("store") Store store,@Param("sessionId") String sessionId, Pageable pageable );

}
