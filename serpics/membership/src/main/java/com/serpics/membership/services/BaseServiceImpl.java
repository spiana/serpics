package com.serpics.membership.services;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.base.persistence.Currency;
import com.serpics.base.repositories.CurrencyRepository;
import com.serpics.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.core.service.AbstractService;
import com.serpics.membership.MemberType;
import com.serpics.membership.UserRegStatus;
import com.serpics.membership.UserType;
import com.serpics.membership.persistence.PrimaryAddress;
import com.serpics.membership.persistence.Store;
import com.serpics.membership.persistence.User;
import com.serpics.membership.persistence.UsersReg;
import com.serpics.membership.repositories.StoreRepository;
import com.serpics.membership.repositories.UserRepository;

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

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void initIstance() {
        if (isInitialized())
            return;

        Currency currency = new Currency();
        currency.setIsoCode("EUR");
        currency.setDescriprion("Euro");
        currency = currencyRepository.saveAndFlush(currency);

        Store s = new Store();
        s.setName("default-store");
        s.setCurrency(currency);
        s = m.createStore(s);
        final User anonymous = new User();
        anonymous.setMemberType(MemberType.USER);
        anonymous.setLastname("Anonymous");
        memberFactory.saveAndFlush(anonymous);

        try {
            commerceEngine.connect("default-store");
            final User u = new User();
            u.setLastname("Superuser");
            u.setUserType(UserType.ADMINISTRATOR);

            final UsersReg ug = new UsersReg();
            ug.setUserId(u.getMemberId());
            ug.setLogonid("superuser");
            ug.setPassword("admin");
            ug.setStatus(UserRegStatus.ACTIVE);
            ug.setUser(u);

            m.registerUser(u, ug, new PrimaryAddress());


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
        final Currency currency= currencyRepository.findOne(currencyRepository.makeSpecification(example));
        Assert.notNull(currency);

        Store s = new Store();
        s.setName(storeName);
        s.setCurrency(currency);
        s = m.createStore(s);

    }

}
