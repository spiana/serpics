package com.serpics.membership.test;

import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.core.session.CommerceSessionContext;
import com.serpics.membership.UserType;
import com.serpics.membership.persistence.MembersRole;
import com.serpics.membership.persistence.PermanentAddress;
import com.serpics.membership.persistence.PrimaryAddress;
import com.serpics.membership.persistence.Role;
import com.serpics.membership.persistence.Store;
import com.serpics.membership.persistence.User;
import com.serpics.membership.services.BaseService;
import com.serpics.membership.services.MemberRoleService;
import com.serpics.membership.services.RoleService;
import com.serpics.membership.services.UserRegService;
import com.serpics.membership.services.UserService;
import com.serpics.test.ExecutionTestListener;

@ContextConfiguration({ "classpath*:META-INF/applicationContext.xml" })
@TestExecutionListeners({ ExecutionTestListener.class,
    DependencyInjectionTestExecutionListener.class })
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@Transactional
public class UserServiceTest {
    Logger log = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    BaseService baseService;
    @Autowired
    CommerceEngine commerceEngine;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    UserRegService userRegService;

    @Autowired
    MemberRoleService memberRoleService;

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
        roleService.create(r);

        final MembersRole m = new MembersRole();
        m.setRole(r);
        m.setMember(u);
        m.setStore((Store) context.getStoreRealm());
        u.getMembersRoles().add(m);
        u = userService.create(u);

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
        u = userService.addAddress(address, u.getMemberId());
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
        roleService.create(r1);

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
        r2 =roleService.create(r2);

        u = userService.addRole(r2, u);
        final java.util.List<User> l2 = userService.findByexample(new User(
                UserType.GUEST, "test", null, null, null));
        Assert.assertEquals(1, l2.size());
        Assert.assertEquals(3, l2.get(0).getMembersRoles().size());

        final Set<MembersRole> roles = l2.get(0).getMembersRoles();
        final MembersRole mr = roles.iterator().next();

        final MembersRole mr1 = memberRoleService.findOne(mr.getId());
        Assert.assertNotNull(mr1);

        final List<MembersRole> l3 = memberRoleService.findAll();
        Assert.assertEquals(3, l3.size());
        memberRoleService.delete(l3.get(0).getId());
        final List<MembersRole> l4 = memberRoleService.findAll();
        Assert.assertEquals(2, l4.size());
    }
}
