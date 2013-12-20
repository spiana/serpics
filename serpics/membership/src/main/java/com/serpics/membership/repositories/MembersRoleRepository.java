package com.serpics.membership.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.core.data.Repository;
import com.serpics.membership.persistence.Member;
import com.serpics.membership.persistence.MembersRole;
import com.serpics.membership.persistence.MembersRolePK;
import com.serpics.membership.persistence.Store;

public interface MembersRoleRepository extends Repository<MembersRole, MembersRolePK> {

    @Query("select mr from MembersRole mr where mr.member= :user and mr.store=:store")
    public List<MembersRole> findUserRoles(@Param("user") Member member, @Param("store") Store store);
}
