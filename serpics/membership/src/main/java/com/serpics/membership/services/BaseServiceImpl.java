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
import com.serpics.base.data.model.Store;
import com.serpics.base.data.repositories.CurrencyRepository;
import com.serpics.base.data.repositories.LocaleRepository;
import com.serpics.base.data.repositories.StoreRepository;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.core.service.AbstractService;
import com.serpics.membership.MemberType;
import com.serpics.membership.UserRegStatus;
import com.serpics.membership.UserType;
import com.serpics.membership.data.model.PrimaryAddress;
import com.serpics.membership.data.model.Role;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.data.repositories.PrimaryAddressRepository;
import com.serpics.membership.data.repositories.RoleRepository;
import com.serpics.membership.data.repositories.UserRepository;
import com.serpics.membership.data.repositories.UserregRepository;

@SuppressWarnings("rawtypes")
@Service("baseService")
public class BaseServiceImpl extends AbstractService implements BaseService {
    @Autowired
    UserRepository memberFactory;

    
    @Resource(name="userregRepository")
    UserregRepository userRepository;
    
    @Resource(name="primaryAddressRepository")
    PrimaryAddressRepository addressRepository;
    
    @Autowired
    StoreRepository storeFactory;

    @Resource
    CurrencyRepository currencyRepository;

    @Resource(name="memberService")
    MembershipService m;

    @Resource
    CommerceEngine commerceEngine;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    LocaleRepository localeRepository;

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
        localeRepository.saveAndFlush(locale);

        locale = new Locale();
        locale.setCountry("US");
        locale.setlanguage("en");
        locale.setName("English");
        localeRepository.saveAndFlush(locale);

        Store s = new Store();
        s.setName("default-store");
        s.setCurrency(currency);
        s = m.createStore(s);
        
        
        Store s1 = new Store();
        s1.setName("test-store");
        s1.setCurrency(currency);
        s1 = m.createStore(s1);
        
        final User anonymous = new User();
        anonymous.setMemberType(MemberType.USER);
        anonymous.setLastname("Anonymous");
        memberFactory.saveAndFlush(anonymous);

        createDefaultRoles();

        try {

            final List<Role> roles = roleRepository.findAll(
            		roleRepository.makeSpecification(new Role("administrator")));
            Assert.notEmpty(roles);
//            final SessionContext c = 
            commerceEngine.connect("default-store");

            UsersReg ug = new UsersReg();
            ug.setLastname("Superuser");
            ug.setUserType(UserType.SUPERSUSER);
            ug.setLogonid("superuser");
            ug.setPassword("admin");
            ug.setStatus(UserRegStatus.ACTIVE);
            //ug = m.registerUser(ug, new PrimaryAddress());
            ug= userRepository.saveAndFlush(ug);
            PrimaryAddress _a =new PrimaryAddress();
            _a.setMember(ug);
            ug.setPrimaryAddress(_a);
            _a =addressRepository.saveAndFlush(_a);
            

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

        roleRepository.saveAndFlush(role);
        roleRepository.saveAndFlush(role1);
    }

}
