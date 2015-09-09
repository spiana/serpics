package com.serpics.membership.test;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.Engine;
import com.serpics.core.SerpicsException;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.membership.UserRegStatus;
import com.serpics.membership.data.model.Address;
import com.serpics.membership.data.model.PrimaryAddress;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.facade.UserFacade;
import com.serpics.membership.facade.data.AddressData;
import com.serpics.membership.facade.data.UserData;
import com.serpics.membership.services.AddressService;
import com.serpics.membership.services.BaseService;
import com.serpics.membership.services.MembershipService;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;


@ContextConfiguration({  "classpath*:META-INF/applicationContext-test.xml"})
@Transactional
public class ConvertTest extends AbstractTransactionalJunit4SerpicTest {

	@Autowired
	BaseService baseService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	UserFacade userFacade;
	@Autowired
	MembershipService m ;
	
	@Autowired
	Engine<CommerceSessionContext> ce;
	
	@Before
	public void beforetest(){
		if (!baseService.isInitialized())
			baseService.initIstance();
	}
	
	@Resource(name="addressConverter")
	AbstractPopulatingConverter<Address, AddressData>  addressConverter;
	
	@Test
	public void converterTest(){
		
		Address a = new Address();
		a.setFirstname("user");
		a.setLastname("test");
		a.setCity("Milan");
		
		AddressData addressData = addressConverter.convert(a);
		Assert.assertEquals("test",addressData.getLastname());
		
	}
	
	@Test
	public void userFacadeTest() throws SerpicsException{
		 final CommerceSessionContext context = ce.connect("default-store",
	                "superuser", "admin".toCharArray());
		 
		Page<UserData> ulist = userFacade.findAllUser(new PageRequest(0, 10));
		Assert.assertEquals(0,ulist.getTotalElements());
		registerTestUser(context);
		ulist = userFacade.findAllUser(new PageRequest(0, 10));
		Assert.assertEquals(1,ulist.getTotalElements());
		Assert.assertEquals("testmembership" , ulist.getContent().get(0).getLastname());
		Assert.assertEquals("testmembership" , ulist.getContent().get(0).getContactAddress().getLastname());
	//	registerTestUser(context);
	}
	
	
	 private void registerTestUser(final CommerceSessionContext context) {


	        UsersReg ur = new UsersReg();
	        ur.setLastname("testmembership");
	        ur.setLogonid("test1");
	        ur.setPassword("password");
	        ur.setStatus(UserRegStatus.ACTIVE);
	        final PrimaryAddress a = new PrimaryAddress();
	        a.setLastname("testmembership");
	        ur = m.registerUser(ur, a);

	    }	
}
