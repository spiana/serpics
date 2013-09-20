package com.serpics.admin.ui;

import javax.annotation.Resource;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.serpics.core.data.Repository;
import com.serpics.core.service.AbstractEntityService;
import com.serpics.membership.persistence.Store;
import com.serpics.membership.persistence.User;
import com.serpics.membership.repositories.UserRepository;
import com.serpics.membership.repositories.UserSpecification;

@Service("userEntityService")
public class UserEntityServiceImpl  extends AbstractEntityService<User, Long>{

	@Resource
	UserRepository userRepository;
	
	
	@Override
	public User create(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Repository<User, Long> getEntityRepository() {
		return userRepository;
	}

	@Override
	public Specification<User> getBaseSpec() {
		return UserSpecification.isUserInStore((Store) getCurrentContext().getStoreRealm());
	}

}
