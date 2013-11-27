package com.serpics.base.services;

import java.util.List;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.serpics.base.persistence.BaseAttribute;
import com.serpics.base.repositories.BaseAttributeRepository;
import com.serpics.core.data.Repository;
import com.serpics.core.service.AbstractEntityService;
import static org.springframework.data.jpa.domain.Specifications.where;

@Service("attributeService")
public class AttributeServiceImpl extends AbstractEntityService<BaseAttribute, Long> implements AttributeService{
	
	@Autowired
	BaseAttributeRepository baseAttributeRepository;
	
	@Override
	public Repository<BaseAttribute, Long> getEntityRepository() {
		return baseAttributeRepository;
	}

	@Override
	public Specification<BaseAttribute> getBaseSpec() {
		return new Specification<BaseAttribute>(){
			@Override
			public Predicate toPredicate(Root<BaseAttribute> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("storeId"), getCurrentContext().getStoreId());
			}
		};
	}

	@Override
	public List<BaseAttribute> findbyAvailablefor(final String availablefor , final Pageable page) {
		return findAll(where(getBaseSpec()).and(new Specification<BaseAttribute>() {
			@Override
			public Predicate toPredicate(Root<BaseAttribute> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("availablefor"), availablefor);
			}
		}), page);
	}

	@Override
	public BaseAttribute create(BaseAttribute entity) {
		entity.setStoreId(getCurrentContext().getStoreId());
		return super.create(entity);
	}
}
