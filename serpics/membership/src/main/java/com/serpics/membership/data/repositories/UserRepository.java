package com.serpics.membership.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.core.data.Repository;
import com.serpics.membership.data.model.Store;
import com.serpics.membership.data.model.User;



@Transactional(readOnly=true)
public interface UserRepository extends Repository<User, Long> {

    @Query("select u from User u  join u.stores s where s =?1")
    List<User> findAllByStore(Store store);

    @Query("select u from User u where u.userType='ANONYMOUS'")
    public List<User> findAnonymous();
    
 

}
