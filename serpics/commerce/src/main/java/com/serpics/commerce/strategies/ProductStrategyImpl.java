/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.commerce.strategies;

import java.util.Map;

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
import com.serpics.catalog.data.model.ProductVariant;
import com.serpics.catalog.data.repositories.AbstractProductRepository;
import com.serpics.catalog.data.repositories.ProductRepository;
import com.serpics.commerce.strategies.AbstractStrategy;
import com.serpics.stereotype.StoreStrategy;

@StoreStrategy("productStrategy")
public class ProductStrategyImpl extends AbstractStrategy implements ProductStrategy {

	@Resource
	AbstractProductRepository abstractProductRepository;
	
	@Resource
	ProductRepository productRepository;
	
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

	/* (non-Javadoc)
	 * @see com.serpics.catalog.strategies.ProductStrategy#resolveVariant(java.lang.String, java.util.Map)
	 */
	@Override
	public ProductVariant resolveVariant(final String sku,
			Map<String, String> attributeMap) {

			Product product = productRepository.findOne(new Specification<Product>() {
			
			@Override
			public Predicate toPredicate(Root<Product> arg0,
					CriteriaQuery<?> arg1, CriteriaBuilder arg2) {
				return arg2.equal(arg0.get("code"), sku);
			}
		});
	
		if (product != null)
			;
		
		return null;
	}

}
