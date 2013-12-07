package com.serpics.membership.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.serpics.core.data.Repository;
import com.serpics.membership.persistence.MembersRole;
import com.serpics.membership.persistence.MembersRolePK;
import com.serpics.membership.persistence.Role;
import com.serpics.membership.persistence.Store;
import com.serpics.membership.persistence.User;

public interface MembersRoleRepository extends Repository<MembersRole, MembersRolePK> {

	@Query("select role from MembersRole where user= :user and store=:store")
	public List<Role> findUserRoles(User user , Store store);
}
