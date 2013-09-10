package com.serpics.membership.services;

import java.util.List;

import org.hibernate.transform.ToListResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.serpics.core.service.AbstractService;
import com.serpics.membership.persistence.Role;
import com.serpics.membership.repositories.RoleRepository;

@Service("roleService")
@Scope("store")
public class RoleServiceImpl extends AbstractService implements RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public Role create(Role entity) {
		return roleRepository.saveAndFlush(entity);
	}

	@Override
	public void delete(Role entity) {
		roleRepository.delete(entity);
	}

	@Override
	public Page<Role> findAll(Pageable page) {
		return roleRepository.findAll(page);
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role update(Role entity) {
		return roleRepository.save(entity);
	}

	@Override
	public List<Role> findByexample(Role example) {
		return roleRepository.findAll(roleRepository.makeSpecification(example));
	}

	@Override
	public Role findOne(Long id) {
		return findOne(id);
	}

}
