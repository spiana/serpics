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
import com.serpics.membership.services.BillingAddressService;
import com.serpics.membership.services.PermanentAddressService;
import com.serpics.membership.services.UserService;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration({ "classpath*:META-INF/applicationContext-test.xml" })
public class UserServiceTest extends AbstractTransactionalJunit4SerpicTest{
    Logger log = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    BaseService baseService;
    
    @Autowired
    BillingAddressService billingAddressService;
    @Autowired
    PermanentAddressService permanentAddressService;
    
   
    
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
    	if (!baseService.isInitialized())
			baseService.initIstance();
    }

    @Test
    @Transactional
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
    @Transactional
    public void managerUser() throws SerpicsException {
    	createUser();
    	listUser();
    	managerBillingAddress();
    	managerPermaentAddress();
    }
    
   

	private void createUser() {
    	
		
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
		
		reg = userService.registerUser(reg, null);
		
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
    	log.info("elenco utenti numero " + l.size());
    	
		int i = 0;
		String guuid = null;
    	for (Iterator iterator = l.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			
			i++;
		}

    	
    }
    
    private void managerBillingAddress() {
    	User u = userService.findByLogonid("vale01");
    	userService.setCurrentCustomer(u);
    	Assert.assertNotNull( userService.getCurrentCustomer().getEmail());
    	
    	
    	BillingAddress address = new BillingAddress();
    	address.setAddress1("Via di prova numero 1");
    	address.setCity("verbania");
    	userService.addBillingAddress(address, u);
 
    	Assert.assertEquals("non uguale city",  "verbania", userService.getCurrentCustomer().getBillingAddress().getCity());
    	
    	address = userService.getCurrentCustomer().getBillingAddress();
    	address.setAddress1("Via billing aggioranta");
    	address.setCity("verbania");
    	address.setZipcode("123");
    	userService.updateBillingAddress(address);
    	Assert.assertEquals("non uguale zipcode",  "123", userService.getCurrentCustomer().getBillingAddress().getZipcode());
    	
    	userService.deleteBillingAddress(userService.getCurrentCustomer());
    	
    	Assert.assertNull("NON NULL BILLING" , userService.getCurrentCustomer().getBillingAddress());
    	
    	List<BillingAddress> l = billingAddressService.findAll();
    	log.info("totale indirizzi di fatturazione" + l.size());
    }

    
    private void managerPermaentAddress() throws SerpicsException {
    	User u = userService.findByLogonid("vale02");
    	commerceEngine.connect("default-store", u);
    //	userService.setCurrentCustomer(u);
    	Assert.assertNotNull(userService.getCurrentCustomer().getEmail());
   
    	
    	PermanentAddress address = new PermanentAddress();
    	address.setAddress1("Via di prova numero 1");
    	address.setCity("verbania");
    	userService.addPermanentAddress(address, u);
    	Assert.assertEquals(1, userService.getCurrentCustomer().getPermanentAddresses().size());
    	
    	address = new PermanentAddress();
    	address.setAddress1("Via di prova numero ");
    	address.setStreetNumber("2");
    	address.setCity("verbania");
    	userService.addPermanentAddress(address, userService.getCurrentCustomer());
    	
    	address = new PermanentAddress();
    	address.setAddress1("Via di prova numero ");
    	address.setStreetNumber("3");
    	address.setCity("verbania");
    	userService.addPermanentAddress(address, userService.getCurrentCustomer());
   
    	Assert.assertEquals(3, userService.getCurrentCustomer().getPermanentAddresses().size());
    	
    	address = (PermanentAddress) userService.getCurrentCustomer().getPermanentAddresses().toArray()[1];
    	address.setAddress1("Via per aggioranta");
    	address.setCity("verbania");
    	address.setNickname("address1");
    	address.setZipcode("123");
    	userService.updatePermanentAddress(address);
    	Assert.assertEquals("non uguale zipcode",  "123", ((PermanentAddress) userService.getCurrentCustomer().getPermanentAddresses().toArray()[1]).getZipcode());
    	
    	userService.deletePermanentAddress(userService.getCurrentCustomer(), (PermanentAddress) userService.getCurrentCustomer().getPermanentAddresses().toArray()[2]);
    	
    	
    	
    	List<PermanentAddress> l = permanentAddressService.findAll();
    	log.info("totale indirizzi di permanenti" + l.size());
    }
}
