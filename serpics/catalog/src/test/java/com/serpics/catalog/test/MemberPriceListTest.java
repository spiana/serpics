package com.serpics.catalog.test;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;

import com.serpics.catalog.data.model.Pricelist;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.data.model.UserPricelistRelation;
import com.serpics.catalog.data.repositories.PriceListRepository;
import com.serpics.catalog.data.repositories.ProductRepository;
import com.serpics.catalog.data.repositories.UserPricelistRepository;
import com.serpics.core.SerpicsException;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.repositories.UserRepository;

public class MemberPriceListTest extends CatalogBaseTest{

	@Resource
	ProductRepository productRepository;
	
	@Resource
	PriceListRepository pricelistRepository;
	
	@Resource
	UserPricelistRepository userPricelistRepository;
	
	@Resource
	UserRepository userRepositoy;
	
	@Test
	@Transactional
	public void test1() throws SerpicsException{
		//CommerceSessionContext context = commerceEngine.connect("default-store");
		
		
		Product p = new Product();
		p.setCode("PPPP1");
		productRepository.save(p);
		
		Pricelist pl = new Pricelist();
		pl.setName("pricelist1");
		pricelistRepository.save(pl);
		
		User u = new User();
		u.setLastname("test");
		u.setCommonName("cn=test");
		userRepositoy.save(u);
		
		UserPricelistRelation ur= new UserPricelistRelation();
		ur.setUser(u);
		ur.setPriceList(pl);
		userPricelistRepository.save(ur);
		
		List<UserPricelistRelation> list = userPricelistRepository.findAll();
		
		Assert.assertEquals(1, list.size());
		
		
	}
}
