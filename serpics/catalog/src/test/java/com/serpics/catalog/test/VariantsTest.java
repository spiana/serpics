/**
 * Copyright 2015-2016 StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.serpics.catalog.test;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.catalog.data.ProductType;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.data.model.ProductVariant;
import com.serpics.catalog.data.repositories.ProductRepository;
import com.serpics.catalog.data.repositories.ProductVariantRepository;

/**
 * @author spiana
 *
 */
public class VariantsTest extends CatalogBaseTest{

	
	@Resource
	ProductVariantRepository productVariantRepository;
	
	@Resource
	ProductRepository productRepository;
	
	@Transactional
	@Test
	public void variantTest(){
		Product p = new Product();
		p.setProductType(ProductType.CONFIGURABLE);
		p.setCode("P");
		productRepository.save(p);
		
		ProductVariant pv1 = new ProductVariant();
		pv1.setCode("P.1");
		pv1.setParentProduct(p);
		pv1.setSequence(1D);
		
		productVariantRepository.save(pv1);
		
		ProductVariant pv0 = new ProductVariant();
		pv0.setCode("P.0");
		pv0.setParentProduct(p);
		pv0.setSequence(0D);
		
		productVariantRepository.save(pv0);
		
		ProductVariant pv2 = new ProductVariant();
		pv2.setCode("P.1.5");
		pv2.setParentProduct(p);
		pv2.setSequence(0.5D);
		
		productVariantRepository.save(pv2);
		
		productRepository.detach(p);

		p = productRepository.findOne(p.getId());
		
		Assert.assertEquals(3, p.getVariants().size());
		Assert.assertEquals("P.0", p.getVariants().iterator().next().getCode());
		Assert.assertEquals("P.1.5", p.getVariants().toArray(new ProductVariant[]{})[1].getCode());
		
		
	}
}
