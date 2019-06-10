package com.serpics.catalog.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.base.commerce.session.CommerceSessionContext;
import com.serpics.catalog.data.model.MemberPricelistRelation;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.data.model.Pricelist;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.data.repositories.MemberPricelistRepository;
import com.serpics.catalog.data.repositories.PriceListRepository;
import com.serpics.catalog.data.repositories.PriceRepository;
import com.serpics.catalog.data.repositories.ProductRepository;
import com.serpics.catalog.services.PriceService;
import com.serpics.core.SerpicsException;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.repositories.UserRepository;

public class MemberPriceListTest extends CatalogBaseTest{

	@Resource
	ProductRepository productRepository;
	
	@Resource
	PriceListRepository pricelistRepository;
	
	@Resource
	MemberPricelistRepository memberPricelistRepository;
	
	@Resource
	UserRepository userRepositoy;
	
	@Autowired
	PriceRepository priceRepository;
	
	@Autowired
	PriceService priceService;
	
	@Test
	@org.springframework.transaction.annotation.Transactional
	public void test1() throws SerpicsException{
		CommerceSessionContext context = commerceEngine.getCurrentContext();
		
		
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
		
		Price price = new Price();
		price.setProduct(p);
		price.setCurrency((com.serpics.i18n.data.model.Currency)context.getCurrency() );
		price.setCurrentPrice(10.0);
		priceRepository.save(price);
		
		
		Price price1 = new Price();
		price1.setProduct(p);
		price1.setCurrency((com.serpics.i18n.data.model.Currency)context.getCurrency() );
		price1.setCurrentPrice(5.0);
		price1.setPricelist(pl);
		priceRepository.save(price1);
		
		Price p1 = priceService.findProductPrice(p);
		Assert.assertEquals(10.0, p1.getCurrentPrice().doubleValue() , 0);
		
		
		
		
		MemberPricelistRelation ur= new MemberPricelistRelation();
		ur.setMember(u);
		ur.setPriceList(pl);
		memberPricelistRepository.save(ur);
		List<MemberPricelistRelation> list = memberPricelistRepository.findAll();
		Assert.assertEquals(1, list.size());
		
		context.setCustomer(u);
		Price p2 = priceService.findProductPrice(p);
		Assert.assertEquals(10.0, p2.getCurrentPrice().doubleValue(),0 );
		
		commerceEngine.disconnect(context);
		context = commerceEngine.connect("default-store");
		
		context.setCustomer(u);
		Price p3 = priceService.findProductPrice(p);
		Assert.assertEquals(5.0, p3.getCurrentPrice().doubleValue(),0 );
		
	}
}
