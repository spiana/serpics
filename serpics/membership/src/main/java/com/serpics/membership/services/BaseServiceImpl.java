package com.serpics.membership.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.base.data.model.Currency;
import com.serpics.base.data.model.Locale;
import com.serpics.base.data.repositories.CurrencyRepository;
import com.serpics.base.services.LocaleService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.core.service.AbstractService;
import com.serpics.core.session.SessionContext;
import com.serpics.membership.MemberType;
import com.serpics.membership.UserRegStatus;
import com.serpics.membership.UserType;
import com.serpics.membership.data.model.PrimaryAddress;
import com.serpics.membership.data.model.Role;
import com.serpics.membership.data.model.Store;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.data.repositories.StoreRepository;
import com.serpics.membership.data.repositories.UserRepository;

@Service("baseService")
public class BaseServiceImpl extends AbstractService implements BaseService {
    @Autowired
    UserRepository memberFactory;

    @Autowired
    StoreRepository storeFactory;

    @Resource
    CurrencyRepository currencyRepository;

    @Autowired
    MembershipService m;

    @Resource
    CommerceEngine commerceEngine;

    @Autowired
    RoleService roleService;

    @Autowired
    LocaleService localeService;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void initIstance() {
        if (isInitialized())
            return;

        Currency currency = new Currency();
        currency.setIsoCode("EUR");
        currency.setDescriprion("Euro");
        currency = currencyRepository.saveAndFlush(currency);

        Locale locale = new Locale();
        locale.setCountry("IT");
        locale.setlanguage("it");
        locale.setName("Italiano");
        localeService.create(locale);

        locale = new Locale();
        locale.setCountry("US");
        locale.setlanguage("en");
        locale.setName("English");
        localeService.create(locale);

        Store s = new Store();
        s.setName("default-store");
        s.setCurrency(currency);
        s = m.createStore(s);
        final User anonymous = new User();
        anonymous.setMemberType(MemberType.USER);
        anonymous.setLastname("Anonymous");
        memberFactory.saveAndFlush(anonymous);

        createDefaultRoles();

        try {

            final List<Role> roles = roleService.findByexample(new Role("administrator"));
            Assert.notEmpty(roles);
            final SessionContext c = commerceEngine.connect("default-store");

            final UsersReg ug = new UsersReg();
            ug.setLastname("Superuser");
            ug.setUserType(UserType.SUPERSUSER);
            ug.setLogonid("superuser");
            ug.setPassword("admin");
            ug.setStatus(UserRegStatus.ACTIVE);
            m.registerUser(ug, new PrimaryAddress());

        } catch (final SerpicsException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean isInitialized() {
        final Store s = storeFactory.findByname("default-store");
        return s == null ? false : true;
    }

    @Override
    public void createStore(final String storeName) {
        final Currency example = new Currency();
        example.setIsoCode("EUR");
        final Currency currency = currencyRepository.findOne(currencyRepository.makeSpecification(example));
        Assert.notNull(currency);

        Store s = new Store();
        s.setName(storeName);
        s.setCurrency(currency);
        s = m.createStore(s);

    }

    private void createDefaultRoles() {
        final Role role = new Role("employee");
        final Role role1 = new Role("administrator");

        roleService.create(role);
        roleService.create(role1);
    }

}
