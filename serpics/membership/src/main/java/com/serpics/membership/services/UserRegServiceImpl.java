package com.serpics.membership.services;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.serpics.core.data.Repository;
import com.serpics.core.service.AbstractEntityService;
import com.serpics.membership.persistence.Store;
import com.serpics.membership.persistence.UsersReg;
import com.serpics.membership.repositories.UserRegrepository;

@Service("usersRegService")
@Scope("store")
public class UserRegServiceImpl extends AbstractEntityService<UsersReg, Long> implements UserRegService {

	@Autowired
	UserRegrepository userRegrepository;
	@Override
	public Repository<UsersReg, Long> getEntityRepository() {
		
		return userRegrepository;
	}

	@Override
	public Specification<UsersReg> getBaseSpec() {
		return new Specification<UsersReg>() {
			@Override
			public Predicate toPredicate(Root<UsersReg> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				return cb.equal(root.join("user").join("storeRelation").get("store") , (Store) getCurrentContext().getStoreRealm() );
			}
			};
	}

	@Override
	public UsersReg findByLoginid(String loginid) {
		return userRegrepository.findBylogonid(loginid);
	}

	
}
