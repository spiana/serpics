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

import com.serpics.base.AvailableforType;
import com.serpics.base.data.model.BaseAttribute;
import com.serpics.base.data.repositories.BaseAttributeRepository;
import com.serpics.commerce.service.AbstractCommerceService;

@Service("attributeService")
public class AttributeServiceImpl extends AbstractCommerceService implements AttributeService{

    @Autowired
    BaseAttributeRepository baseAttributeRepository;

       @Override
    public List<BaseAttribute> findbyAvailablefor(final AvailableforType availablefor, final Pageable page) {
        return baseAttributeRepository.findAll(new Specification<BaseAttribute>() {
			@Override
			public Predicate toPredicate(Root<BaseAttribute> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				   return cb.equal(root.get("availablefor"), availablefor);
            }
        	
        });
       }       

    
}
