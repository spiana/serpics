package com.serpics.membership.services;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.core.data.Repository;
import com.serpics.membership.UserType;
import com.serpics.membership.data.model.Store;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.repositories.StoreRepository;
import com.serpics.membership.repositories.UserRegrepository;

@Service("usersRegService")
@Scope("store")
@Transactional(readOnly = true)
public class UserRegServiceImpl extends AbstractMemberService<UsersReg, Long> implements UserRegService {

    @Autowired
    private UserRegrepository userRegrepository;

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public Repository<UsersReg, Long> getEntityRepository() {
        return userRegrepository;
    }


    @Override
    @Transactional
    public UsersReg create(UsersReg user) {

        if (user.getUserType().equals(UserType.ANONYMOUS))
            user.setUserType(UserType.GUEST);
        user = adjustAddresses(user);
        user = adjustMemberRoles(user);
        user.setCreated(new Date());
        user = userRegrepository.saveAndFlush(user);
        final Store currentStore = (Store) getCurrentContext().getStoreRealm();
        final Store _s = storeRepository.findOne(currentStore.getMemberId());
        user.getStores().add(_s);
        user.getStores().add(currentStore);

        return userRegrepository.saveAndFlush(user);

    }

    @Override
    @Transactional
    public UsersReg update(final UsersReg user) {

        // user = adjustAddresses(user);
        // user = adjustMemberRoles(user);

        return userRegrepository.save(user); // super.update(user);
    }

    @Override
    public Specification<UsersReg> getBaseSpec() {
        return new Specification<UsersReg>() {
            @Override
            public Predicate toPredicate(final Root<UsersReg> root, final CriteriaQuery<?> query,
                    final CriteriaBuilder cb) {
                return cb.equal(root.join("stores").as(Store.class), (Store) getCurrentContext().getStoreRealm());
            }
        };
    }

    @Override
    public UsersReg findByLoginid(final String loginid) {
        return userRegrepository.findBylogonid(loginid);
    }

}
