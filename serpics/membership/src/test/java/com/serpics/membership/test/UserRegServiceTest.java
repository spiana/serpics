package com.serpics.membership.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.membership.data.model.Store;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.repositories.StoreRepository;
import com.serpics.membership.repositories.UserRepository;
import com.serpics.membership.services.BaseService;
import com.serpics.membership.services.UserRegService;
import com.serpics.membership.services.UserService;
import com.serpics.test.ExecutionTestListener;

@ContextConfiguration({ "classpath*:META-INF/applicationContext.xml" })
@TestExecutionListeners({ ExecutionTestListener.class, DependencyInjectionTestExecutionListener.class })
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@Transactional
public class UserRegServiceTest {

    @Autowired
    BaseService baseService;

    @Autowired
    UserRegService userRegService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StoreRepository storeRepository;

    @Before
    public void init() {
        baseService.initIstance();
    }

    @Test
    public void first() {
        final List<UsersReg> l = userRegService.findAll();
        Assert.assertEquals(1, l.size());
        UsersReg u = userRegService.findByLoginid("superuser");
        Assert.assertNotNull(u);
    }

    @Test
    public void second() {
        final List<Store> stores = storeRepository.findAll();
        final List<User> l = userRepository.findAllByStore(stores.get(0));
        Assert.assertEquals(1, l.size());

    }

}
