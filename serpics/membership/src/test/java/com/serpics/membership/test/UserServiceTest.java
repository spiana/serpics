package com.serpics.membership.test;

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

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.core.test.AbstractTransactionalJunit4SerpicTest;
import com.serpics.membership.UserType;
import com.serpics.membership.data.model.MembersRole;
import com.serpics.membership.data.model.PermanentAddress;
import com.serpics.membership.data.model.PrimaryAddress;
import com.serpics.membership.data.model.Role;
import com.serpics.membership.data.model.Store;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.repositories.MembersRoleRepository;
import com.serpics.membership.data.repositories.RoleRepository;
import com.serpics.membership.services.BaseService;
import com.serpics.membership.services.UserService;

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
}
