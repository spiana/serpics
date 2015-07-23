package com.serpics.membership.test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.data.model.Country;
import com.serpics.base.data.model.Geocode;
import com.serpics.base.data.model.MultilingualString;
import com.serpics.base.data.repositories.CountryRepository;
import com.serpics.base.data.repositories.GeoCodeRepository;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.membership.UserType;
import com.serpics.membership.data.model.BillingAddress;
import com.serpics.membership.data.model.MembersRole;
import com.serpics.membership.data.model.PermanentAddress;
import com.serpics.membership.data.model.PrimaryAddress;
import com.serpics.membership.data.model.Role;
import com.serpics.membership.data.model.Store;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.data.repositories.MembersRoleRepository;
import com.serpics.membership.data.repositories.RoleRepository;
import com.serpics.membership.services.BaseService;
import com.serpics.membership.services.UserService;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration({ "classpath*:META-INF/applicationContext.xml" })
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class UserServiceTest extends AbstractTransactionalJunit4SerpicTest{
    Logger log = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    BaseService baseService;
    @Autowired
    CommerceEngine commerceEngine;
    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;
  
    @Autowired
    MembersRoleRepository memberRoleRepository;

    @Autowired
    CountryRepository coutnryRepository;
    
    @Autowired
    GeoCodeRepository geocodeRepository;
    
    @Before
    public void init() {
       baseService.initIstance();
    }

    @Test
    public void first() throws SerpicsException {
    	final CommerceSessionContext context = commerceEngine
                .connect("default-store");

        User u = new User();
        u.setFirstname("test");
        final PrimaryAddress a = new PrimaryAddress();
        a.setCity("mailan");
        u.setPrimaryAddress(a);

        final Role r = new Role();
        r.setName("user");
        r.setDescription("User");
        roleRepository.create(r);

        u = userService.create(u);
        final java.util.List<User> l0 = userService.findAll();
        Assert.assertEquals(2, l0.size());
        
        final MembersRole m = new MembersRole();
        m.setRole(r);
        m.setMember(u);
        m.setStore((Store) context.getStoreRealm());
        u.getMembersRoles().add(m);
        u = userService.update(u);

        final java.util.List<User> l = userService.findAll();
        Assert.assertEquals(2, l.size());
        
        final java.util.List<User> u1 = userService.findByexample(new User(
                UserType.GUEST, "test", null, null, null));
        Assert.assertEquals(1, u1.size());
        Assert.assertEquals(1, u1.get(0).getMembersRoles().size());
        Assert.assertEquals("user", u1.get(0).getMembersRoles().iterator().next().getRole().getName());

        // try add a new address
        final PermanentAddress address = new PermanentAddress();
        address.setCity("Napoli");
        u = userService.addAddress(address, u.getId());
        Assert.assertEquals(1, u.getPermanentAddresses().size());

        // try add another address directly
        final PermanentAddress add1 = new PermanentAddress();
        add1.setMember(u);
        u.getPermanentAddresses().add(add1);
        u = userService.update(u);
        Assert.assertEquals(2, u.getPermanentAddresses().size());

        // try add role
        final Role r1 = new Role();
        r1.setName("employer");
        roleRepository.create(r1);

        final MembersRole m1 = new MembersRole();
        m1.setRole(r1);
        m1.setMember(u);
        m1.setStore((Store) context.getStoreRealm());
        u.getMembersRoles().add(m1);
        u = userService.update(u);

        final java.util.List<User> l1 = userService.findByexample(new User(
                UserType.GUEST, "test", null, null, null));
        Assert.assertEquals(1, l1.size());
        Assert.assertEquals(2, l1.get(0).getMembersRoles().size());

        //try nother role
        Role r2 = new Role("boss");
        r2 =roleRepository.create(r2);

        u = userService.addRole(r2, u);
        final java.util.List<User> l2 = userService.findByexample(new User(
                UserType.GUEST, "test", null, null, null));
        Assert.assertEquals(1, l2.size());
        Assert.assertEquals(3, l2.get(0).getMembersRoles().size());

        final Set<MembersRole> roles = l2.get(0).getMembersRoles();
        final MembersRole mr = roles.iterator().next();

        final MembersRole mr1 = memberRoleRepository.findOne(mr.getId());
        Assert.assertNotNull(mr1);

        final List<MembersRole> l3 = memberRoleRepository.findAll();
        Assert.assertEquals(3, l3.size());
   
//        memberRoleService.delete(l3.get(2).getId());
//        final List<MembersRole> l4 = memberRoleService.findAll();
//        Assert.assertEquals(2, l4.size());
    }
    
    
    @Test    
    public void managerUser() throws SerpicsException {
    	createUser();
    	listUser();
    	addBillingAddress();
    	addPermanentAddress();
    	updatePrimaryAddress();
    	updateBillingAddress();
    	updatePermanentAddress();
    	deletePrimaryAddress();
    	deleteBillingAddress();
    	deletePermanentAddress();
    }
    
   

	private void createUser() {
    	User u = new User();
		u.setFirstname("vale guest service 1");
		u.setLastname("ranc");
		u.setUserType(UserType.GUEST);
		u.setEmail("valeser01@prova.it");
		u = userService.create(u);
		
		u = new User();
		u.setFirstname("vale guest service 2");
		u.setLastname("ranc");
		u.setUserType(UserType.GUEST);
		u.setEmail("valeser02@prova.it");
		u = userService.create(u);
		
		
		Geocode g = new Geocode();
		g.setDescription(new MultilingualString("it","geo ita"));
		g.setCode("it");
		g = geocodeRepository.create(g);
		
		Country c = new Country();
		c.setDescription(new MultilingualString("it","ITALIA"));
		c.setIso2Code("it");
		c.setIso3Code("ita");
		c.setGeocode(g);
		c = coutnryRepository.create(c);
		
		PrimaryAddress pa = new PrimaryAddress();
		
		UsersReg reg = new UsersReg();
		reg.setFirstname("vale reg 1");
		reg.setLastname("ranc");
		reg.setLogonid("vale01");
		reg.setPassword("vale");
		reg.setEmail("vale01@prova.it");
		
		

		
		reg = userService.registerUser(reg, pa);
		
		reg = new UsersReg();
		reg.setFirstname("vale reg 2");
		reg.setLastname("ranc");
		reg.setLogonid("vale02");
		reg.setPassword("vale");
		reg.setEmail("vale02@prova.it");
		reg.setPhone("123");
		
		pa = new PrimaryAddress();
		pa.setAddress1("vai registrato2");
		pa.setCity("verbania");
		pa.setZipcode("28921");
		reg = userService.registerUser(reg, pa);
		log.info("EXIT TEST SERVICE");
		
		
    }
    
    private void listUser() {
    	List<User> l = userService.findAll();
    	Assert.assertEquals("not equals" ,5, l.size());
    	
		int i = 0;
		String guuid = null;
    	for (Iterator iterator = l.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			if(user.getUserType() == UserType.REGISTERED){
				
				String uuid = user.getUuid();
				UsersReg ur = userService.findByRegUUID(uuid);
				Assert.assertNotNull(ur);
//				ur.getLogonid();
//				ur.setLogonid("nuovo"+i);
//				userService.update((User)ur);
				
			} else if(user.getUserType().equals(UserType.GUEST)) {
				guuid=user.getUuid();
			}
			i++;
		}

    	
    }
    
    private void addBillingAddress() {
    	List<User> lu = userService.findByexample(new User(UserType.GUEST,null,null,null,null));
    	Assert.assertEquals("ERROR NOT EQUALS ", 2, lu.size());
    	User _u = lu.get(0);
    	Assert.assertEquals("ERROR NOT USER GUEST", "valeser01@prova.it", _u.getEmail());
    	String _uuid = _u.getUuid();
    	
    	BillingAddress ba = new BillingAddress();
    	ba.setAddress1("GUEST ADDR 1");
    	ba.setCity("verbania");
    	userService.addBillingAddress(ba, _u);
    	
    	 _u = lu.get(1);
    	Assert.assertEquals("ERROR NOT USER GUEST", "valeser02@prova.it", _u.getEmail());
    	 _uuid = _u.getUuid();
    	
    	ba = new BillingAddress();
    	ba.setAddress1("GUEST ADDR 2");
    	ba.setCity("verbania");
    	userService.addBillingAddress(ba, _u); 
    	
    	
    	lu = userService.findByexample(new User(UserType.GUEST,null,null,null,null));
    	for (Iterator iterator = lu.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			Assert.assertNotNull("IS NULL", user.getBillingAddress());
			Assert.assertNotNull("IS NULL", user.getPermanentAddresses());
		}
    	
    	
    	UsersReg u = userService.findByLogonid("vale01");
    	ba = new BillingAddress();
    	ba.setAddress1("nuovo bil addr");
    	ba.setCity("verbania");
    	userService.addBillingAddress(ba, u);
    	
    	
    }
    
    private void addPermanentAddress() {
    	List<User> lu = userService.findByexample(new User(UserType.REGISTERED,null,null,null,null));
    	Assert.assertEquals("ERROR NOT EQUALS ", 2, lu.size());
    	User _u = lu.get(0);
    	Assert.assertEquals("ERROR NOT USER GUEST", "vale01@prova.it", _u.getEmail());
    	String _uuid = _u.getUuid();
    	
    	PermanentAddress pa = new PermanentAddress();
    	pa.setAddress1("REG ADDR 1");
    	pa.setCity("verbania");
    	userService.addPermanentAddress(pa, _u);
    	
    	 _u = lu.get(1);
    	Assert.assertEquals("ERROR NOT USER GUEST", "vale02@prova.it", _u.getEmail());
    	 _uuid = _u.getUuid();
    	
    	pa = new PermanentAddress();
    	pa.setAddress1("REG ADDR 2");
    	pa.setCity("verbania");
    	userService.addPermanentAddress(pa, _u); 
    	
    	pa = new PermanentAddress();
    	pa.setAddress1("REG ADDR 2.2");
    	pa.setCity("verbania");
    	userService.addPermanentAddress(pa, _u); 
    	
    	
    	List<User> lu2 = userService.findByexample(new User(UserType.REGISTERED,null,null,null,null));
    	for (Iterator iterator = lu2.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			Assert.assertNotNull("IS NULL", user.getPermanentAddresses());
		}
    	
		UsersReg u = userService.findByLogonid("vale01");
		
		pa = new PermanentAddress();
		pa.setAddress1("REG ADDR 3.1");
		pa.setCity("verbania");
		userService.addPermanentAddress(pa, u); 
		
		pa = new PermanentAddress();
		pa.setAddress1("REG ADDR 3.2");
		pa.setCity("verbania");
		userService.addPermanentAddress(pa, u); 
    }
    
    private void deletePermanentAddress() {
		// TODO Auto-generated method stub
		
	}

	private void deleteBillingAddress() {
		UsersReg u = userService.findByLogonid("vale01");
		Assert.assertNotNull("ERROR NOT FIND BILLING", u.getBillingAddress());
		
		
		
	}

	private void deletePrimaryAddress() {
		// TODO Auto-generated method stub
		
	}

	private void updatePermanentAddress() {
		UsersReg u = userService.findByLogonid("vale01");
		Assert.assertNotNull(" FOUND PERMANENT", u.getPermanentAddresses());
		
		Set<PermanentAddress> list = u.getPermanentAddresses();
		String uuid = list.iterator().next().getUuid();
		PermanentAddress address = list.iterator().next();
		address.setAddress1("TEST CHANGE ADDRESS");
		address.setZipcode("123");
		address = userService.updatePermanentAddress(address);
		Assert.assertEquals("NOT CHANGE", "123", u.getPermanentAddresses().iterator().next().getZipcode());
	}

	private void updateBillingAddress() {
		UsersReg  u = userService.findByLogonid("vale01");
		Assert.assertNotNull("not FOUND billing", u.getBillingAddress());
		String paUuid = u.getBillingAddress().getUuid();
		
		BillingAddress address = u.getBillingAddress();
		address.setAddress1("TEST_BILL1");
		address = userService.updateBillingAddress(address);
		Assert.assertEquals("NOT CHANGE", "TEST_BILL1", u.getBillingAddress().getAddress1());
		
	}

	private void updatePrimaryAddress() {
		UsersReg u = userService.findByLogonid("vale01");
		Assert.assertNotNull("NOT FOUNT PRIMARY", u.getPrimaryAddress());
		String paUuid = u.getPrimaryAddress().getUuid();
		
		PrimaryAddress address = u.getPrimaryAddress();
		address.setAddress1("TEST1");
		address = userService.updatePrimaryAddress(address);
		Assert.assertEquals("NOT CHANGE", "TEST1", u.getPrimaryAddress().getAddress1());
	}
}
