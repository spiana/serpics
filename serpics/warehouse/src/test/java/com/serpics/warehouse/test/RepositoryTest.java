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
package com.serpics.warehouse.test;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.data.model.Store;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.data.repositories.ProductRepository;
import com.serpics.warehouse.data.model.Inventory;
import com.serpics.warehouse.data.model.Warehouse;
import com.serpics.warehouse.data.repositories.InventoryRepository;
import com.serpics.warehouse.data.repositories.WarehouseRepository;

public class RepositoryTest extends WarehouseBaseTest {

	@Resource
	WarehouseRepository warehouseRepository;
	@Resource
	InventoryRepository inventoryRepository;
	
	@Resource
	ProductRepository productRepository;
	
	@Test
	@Transactional
	public void repositoryTest(){
	
			Warehouse w = new Warehouse();
			w.setStore((Store)commerceEngine.getCurrentContext().getStoreRealm());
			w.setName("W1");
			w.setPrecedence(1D);
			warehouseRepository.saveAndFlush(w);
			
			
			Warehouse w1 = new Warehouse();
			w1.setStore((Store)commerceEngine.getCurrentContext().getStoreRealm());
			w1.setName("W2");
			w1.setPrecedence(2D);
			warehouseRepository.saveAndFlush(w1);
			
			
			Product p = new Product();
			p.setCode("p1");
			productRepository.saveAndFlush(p);
			
			Inventory i = new Inventory();
			i.setProduct(p);
			i.setWarehouse(w);
			i.setAvailable(10D);
			inventoryRepository.saveAndFlush(i);
			

			Inventory i1 = new Inventory();
			i1.setProduct(p);
			i1.setWarehouse(w1);
			i1.setAvailable(20D);
			inventoryRepository.saveAndFlush(i1);
			
			
			Assert.assertEquals(new Double(30), inventoryRepository.getAvailable(p, (Store)commerceEngine.getCurrentContext().getStoreRealm()));
			Assert.assertEquals(2, warehouseRepository.findAll().size());
			
			warehouseRepository.detach(w);
			Warehouse w5 = warehouseRepository.findOne(new Specification<Warehouse>() {
				
				@Override
				public Predicate toPredicate(Root<Warehouse> arg0, CriteriaQuery<?> arg1,
						CriteriaBuilder arg2) {
					
					return arg2.equal(arg0.get("name"), "W1");
				}
			});
			Assert.assertNotNull(w5.getInventories());
		
	}
}
