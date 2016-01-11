package com.serpics.membership.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.membership.UserRegStatus;
import com.serpics.membership.UserType;
import com.serpics.membership.data.model.PermanentAddress;
import com.serpics.membership.data.model.PrimaryAddress;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.data.repositories.UserregRepository;
import com.serpics.membership.services.AddressService;
import com.serpics.membership.services.BaseService;
import com.serpics.membership.services.MembershipService;
import com.serpics.membership.services.PrimaryAddressService;
import com.serpics.membership.services.UserService;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration({ "classpath:META-INF/base-serpics.xml" , "classpath:META-INF/membership-serpics.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MembershipTestCase extends AbstractTransactionalJunit4SerpicTest {

    @Autowired
    BaseService baseService;

    @Autowired
    MembershipService m;
    @Autowired
    CommerceEngine ce;

    @Autowired
    UserService userService;

    @Autowired
    UserregRepository userRegRepository;

    @Autowired
    AddressService addressService;
    
    @Autowired
    PrimaryAddressService primaryAddressService;

    @Before
    @Transactional
    public void beforetest(){
    	if (!baseService.isInitialized())
			baseService.initIstance();
    }
    
    byte[] buffer;
   
    
    @Test
    @Transactional
    public void test0() throws SerpicsException{
//        final CommerceSessionContext context = 
        		ce.connect("default-store",
                "superuser", "admin".toCharArray());
        final Page<User> p = userService.findAll(new PageRequest(0, 10));
        assertEquals(0, p.getContent().size());
        final List<User> l = userService.findAll();
        assertEquals(0, l.size());
    }
    
    @Test
    @Transactional
    public void test() throws SerpicsException {

        CommerceSessionContext context = ce.connect("default-store",
                "superuser", "admin".toCharArray());
        assertNotNull("not connect with context !", context);
        assertNotNull("notstore in context !", context.getStoreRealm());

        context = ce.connect(context, "superuser", "admin".toCharArray());
        assertNotNull("not connect with context !", context);
        registerTestUser(context);
        context = ce
                .connect("default-store", "test1", "password".toCharArray());
        assertNotNull("not connect with context !", context);
        final UsersReg u = (UsersReg) context.getUserPrincipal();
        assertArrayEquals("verify is test user", "test1".toCharArray(), u.getLogonid().toCharArray());
        assertArrayEquals("primaryAddress lastName", "testmembership".toCharArray(), u.getPrimaryAddress()
                .getLastname().toCharArray());
        
        final UsersReg example = new UsersReg();
        example.setUserType(UserType.REGISTERED);
        example.setLastname("testmembership");

        List<UsersReg> l1 = userService.findByexample(example);
        assertEquals(1, l1.size());

        final UsersReg r = new UsersReg();
        r.setLogonid("test1");
        List<UsersReg> l = userService.findByexample(r);
        assertEquals(1, l.size());

        l = userRegRepository.findAll(new Specification<UsersReg>() {

            @Override
            public Predicate toPredicate(final Root<UsersReg> arg0, final CriteriaQuery<?> arg1,
                    final CriteriaBuilder arg2) {
                return arg2.equal(arg0.get("logonid"), "test1");
            }

        });

        assertEquals(1, l.size());
        userService.addAddress(new PermanentAddress(), u.getId());

        userRegRepository.detach(u);

        u.setEmail("aaaa");

        final UsersReg u1 = userRegRepository.saveAndFlush(u);
        assertEquals("aaaa", u1.getEmail());

        // assertEquals(0, l1.get(0).getPermanentAddresses().size());

        final List<UsersReg> lu1 = userService.findByexample(example);
        assertEquals(1, lu1.size());

        final Set<PermanentAddress> addresses = userService.getUserAddress(lu1.get(0));
        assertEquals(1, addresses.size());

      //  Address _a = addressService.findByUUID(lu1.get(0).getPrimaryAddress().getUuid());
        Assert.assertNotNull(lu1.get(0).getPrimaryAddress().getId());
        PrimaryAddress _a = primaryAddressService.findOne(lu1.get(0).getPrimaryAddress().getId());
        Assert.assertNotNull(_a);
//        

        List<User> l2 = userService.findAll();
        assertEquals(1, l2.size());
        
    }

    @Test
    @Transactional
    public void test1() throws SerpicsException, IOException, ClassNotFoundException{
    	CommerceSessionContext context = ce.connect("default-store",
                "superuser", "admin".toCharArray());
    	Assert.assertNotNull(context.getUserPrincipal().getUuid());
//    	String sessionid = 
    			context.getSessionId();
    	serialize(context);	
    	CommerceSessionContext context1 = deserialize();
    	
    	Assert.assertNotNull(context1.getUserPrincipal().getUuid());
    	
    }
    
    @Test
    public void reconnectService() throws SerpicsException{
    	CommerceSessionContext context = ce.connect("default-store","superuser", "admin".toCharArray());
    	String sessionId = context.getSessionId();
    	Assert.assertEquals(UserType.SUPERSUSER, ((User)context.getUserPrincipal()).getUserType());
    	ce.disconnect(sessionId);
    	context = ce.bind(sessionId);
    	Assert.assertEquals(UserType.ANONYMOUS, ((User)context.getUserPrincipal()).getUserType());
    	Assert.assertEquals(sessionId, context.getSessionId());
    }
    
    private void serialize(CommerceSessionContext e) throws IOException{
    
    		ByteArrayOutputStream fileOut =new ByteArrayOutputStream();
    	   ObjectOutputStream out = new ObjectOutputStream(fileOut);
           out.writeObject(e);
           out.close();
           buffer= fileOut.toByteArray();
    
    }
    private CommerceSessionContext deserialize() throws IOException, ClassNotFoundException{
    	InputStream infile = new ByteArrayInputStream(buffer);
    	 ObjectInputStream in = new ObjectInputStream(infile);
    	 CommerceSessionContext c = (CommerceSessionContext) in.readObject();
    	 in.close();
    	 return c;
    	 
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
