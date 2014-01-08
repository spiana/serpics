package com.serpics.membership.test;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.membership.persistence.User;
import com.serpics.membership.persistence.UsersReg;
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

    @Before
    public void init() {
        baseService.initIstance();
    }

    @Test
    public void first() {
        final List<UsersReg> l = userRegService.findAll();

        Assert.assertEquals(1, l.size());

    }

    @Test
    public void second() {
        final List<User> users = userService.findAll();
        final List<UsersReg> l = userRegService.findAll(new Specification<UsersReg>() {

            @Override
            public Predicate toPredicate(final Root<UsersReg> arg0, final CriteriaQuery<?> arg1,
                    final CriteriaBuilder arg2) {
                return arg2.equal(arg0.get("user"), users.get(0));
            }
        }, new PageRequest(0, 100));

        Assert.assertEquals(1, l.size());

    }

}
