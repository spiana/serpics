package com.serpics.warehouse.test;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.data.model.Store;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.services.ProductService;
import com.serpics.warehouse.InventoryNotAvailableException;
import com.serpics.warehouse.data.model.Inventory;
import com.serpics.warehouse.data.model.Warehouse;
import com.serpics.warehouse.data.model.WarehouseStoreConfig;
import com.serpics.warehouse.data.repositories.InventoryRepository;
import com.serpics.warehouse.data.repositories.WarehouseRepository;
import com.serpics.warehouse.data.repositories.WarehouseStoreConfigRepository;
import com.serpics.warehouse.service.InventoryService;
import com.serpics.warehouse.service.WareHouseService;

@TransactionConfiguration(defaultRollback=true)
public class InventoryTest extends WarehouseBaseTest{

	@Resource
	WarehouseRepository warehouseRepository;
	@Resource
	InventoryRepository inventoryRepository;
	
	@Resource
	WarehouseStoreConfigRepository storeConfigRepository;
	
	
	@Resource
	InventoryService inventoryService;
	
	@Resource
	ProductService productService;
	
	@Resource
	WareHouseService warehouseService;
	
	@Before
	public void initeTest(){
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
		productService.create(p);
		
		Inventory i = new Inventory();
		i.setProduct(p);
		i.setWarehouse(w);
		i.setAvailable(10D);
		inventoryRepository.saveAndFlush(i);
		

		Inventory i1 = new Inventory();
		i1.setProduct(p);
		i1.setWarehouse(w1);
		i1.setAvailable(5D);
		inventoryRepository.saveAndFlush(i1);
		
		WarehouseStoreConfig storeConfig = new WarehouseStoreConfig();
		storeConfig.setAlwaysInstock(false);
		storeConfig.setStore((Store)commerceEngine.getCurrentContext().getStoreRealm());
		storeConfigRepository.saveAndFlush(storeConfig);
		
		
	}
	
	
	@Test
	@Transactional
	public void findPreferredWarehouse(){
		AbstractProduct p = productService.findByName("p1");
		Warehouse w =  warehouseService.findPreferredForReserve(p , 1.0);
		Assert.assertNotNull(w);
		Assert.assertEquals("W2", w.getName());
		w =  warehouseService.findPreferredForReserve(p , 10.0);
		Assert.assertNotNull(w);
		Assert.assertEquals("W1", w.getName());
		
	}
	
	@Test
	@Transactional
	public void reserveandreleasetest() throws InventoryNotAvailableException{
		AbstractProduct p = productService.findByName("p1");
		double available = inventoryService.getStockLevelAmount(p);
		inventoryService.reserve(p, 15.0);
		inventoryService.release(p, 10.0);
		Assert.assertEquals(available, inventoryService.getStockLevelAmount(p) ,5.0);
		
	}
	

	@Test
	@Transactional
	public void reserveWithAlwaysInstockWarehouse() throws InventoryNotAvailableException{
		AbstractProduct p = productService.findByName("p1");
		double available = inventoryService.getStockLevelAmount(p);
		inventoryService.reserve(p, 10.0);
		Assert.assertEquals(0, inventoryService.getStockLevelAmount(p) ,5.0);
		
		Warehouse w2 = new Warehouse();
		w2.setStore((Store)commerceEngine.getCurrentContext().getStoreRealm());
		w2.setName("W3");
		w2.setPrecedence(3D);
		w2.setForceInStock(true);
		warehouseRepository.saveAndFlush(w2);
		
		inventoryService.reserve(p, 3.0);
		Assert.assertEquals(0, inventoryService.getStockLevelAmount(p) ,2.0);
		

		inventoryService.reserve(p, 5.0);
		Assert.assertEquals(0, inventoryService.getStockLevelAmount(p) ,0.0);
		
		w2.setForceInStock(true);
		warehouseRepository.saveAndFlush(w2);
		
		inventoryService.release(p, 5.0);
		Assert.assertEquals(0, inventoryService.getStockLevelAmount(p) ,5.0);
		
		
		inventoryService.release(p, 20.0);
		Assert.assertEquals(0, inventoryService.getStockLevelAmount(p) ,15.0);
		
		
		
	}
}
