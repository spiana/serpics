package com.serpics.membership.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.core.data.Repository;
import com.serpics.membership.persistence.Store;
import com.serpics.membership.persistence.User;



@Transactional(readOnly=true)
public interface UserRepository extends Repository<User, Long> {

		@Query("select u from User u join u.storeRelation r where r.store = ?1")
		List<User> findAllByStore(Store store);
		
	

}
