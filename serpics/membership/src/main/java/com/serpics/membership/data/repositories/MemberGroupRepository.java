package com.serpics.membership.data.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.base.data.model.Store;
import com.serpics.core.data.Repository;
import com.serpics.membership.data.model.Membergroup;
import com.serpics.membership.data.model.User;

public interface MemberGroupRepository extends Repository<Membergroup, Long> {

    @Query("select mg from Membergrouprel mgr join mgr.member u join mgr.membergroup mg where mg.store = :store and mgr.member = :user")
    public Set<Membergroup> findMembergroupByUser(@Param("user") User user, @Param("store") Store store);
}
