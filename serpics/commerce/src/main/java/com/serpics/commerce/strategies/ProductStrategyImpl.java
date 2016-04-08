package com.serpics.commerce.strategies;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.data.ProductType;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.data.repositories.AbstractProductRepository;
import com.serpics.stereotype.StoreStrategy;

@StoreStrategy("productStrategy")
public class ProductStrategyImpl extends AbstractStrategy implements ProductStrategy {

	@Resource
	AbstractProductRepository abstractProductRepository;
	
	@Override
	public AbstractProduct resolveSKU(final String sku) throws ProductNotFoundException {
	
		AbstractProduct product = abstractProductRepository.findOne(new Specification<AbstractProduct>() {
			
			@Override
			public Predicate toPredicate(Root<AbstractProduct> arg0,
					CriteriaQuery<?> arg1, CriteriaBuilder arg2) {
				
				return arg2.equal(arg0.get("code"), sku);
			}
		});
		
		if (product == null)
			throw new ProductNotFoundException(String.format("product not found for SKU [%s] !", sku));
		
		if (Product.class.isAssignableFrom(product.getClass()))
			if (((Product) product).getProductType().equals(ProductType.CONFIGURABLE))
				throw new ProductNotFoundException(String.format("invalid produt type for SKU [%s] !", sku));
			
		return product;
	}

}
