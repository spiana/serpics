package com.serpics.catalog.services;

import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.catalog.persistence.Catalog;
import com.serpics.catalog.persistence.Product;
import com.serpics.catalog.repositories.ProductRepository;
import com.serpics.commerce.service.AbstractCommerceEntityService;
import com.serpics.core.data.Repository;

@Service("produtService")
@Scope("store")
public class ProductServiceImpl extends AbstractCommerceEntityService<Product, Long> implements ProductService{
	private static final long serialVersionUID = -3989192615962130810L;

	@Autowired
	ProductRepository productRepository;

	@Override
	public Repository<Product, Long> getEntityRepository() {
		return productRepository;
	}

	@Override
	public Specification<Product> getBaseSpec() {
		
		return new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("catalog") , getCurrentContext().getCatalog());
			}
		};
	}

	@Override
	@Transactional
	public Product create(Product entity) {
		if (entity.getCatalog() == null)
			entity.setCatalog((Catalog)getCurrentContext().getCatalog());
	
		if (entity.getCode() == null){
			entity.setCode(UUID.randomUUID().toString());
		}
		
		return productRepository.saveAndFlush(entity);
	}

}
