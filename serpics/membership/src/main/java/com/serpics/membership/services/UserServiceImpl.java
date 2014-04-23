package com.serpics.membership.services;

import static com.serpics.membership.repositories.UserSpecification.isUserInStore;
import static org.springframework.data.jpa.domain.Specifications.where;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.core.data.Repository;
import com.serpics.core.service.AbstractEntityService;
import com.serpics.membership.UserRegStatus;
import com.serpics.membership.UserType;
import com.serpics.membership.persistence.Membergroup;
import com.serpics.membership.persistence.MembersRole;
import com.serpics.membership.persistence.MembersRolePK;
import com.serpics.membership.persistence.PermanentAddress;
import com.serpics.membership.persistence.PrimaryAddress;
import com.serpics.membership.persistence.Role;
import com.serpics.membership.persistence.Store;
import com.serpics.membership.persistence.User;
import com.serpics.membership.persistence.UsersReg;
import com.serpics.membership.repositories.MemberGroupRepository;
import com.serpics.membership.repositories.MembersRoleRepository;
import com.serpics.membership.repositories.PermanentAddressRepository;
import com.serpics.membership.repositories.StoreRepository;
import com.serpics.membership.repositories.UserRegrepository;
import com.serpics.membership.repositories.UserRepository;

//@StoreService("userService" )
@Service("userService")
@Scope("store")
@Transactional(readOnly = true)
public class UserServiceImpl extends AbstractEntityService<User, Long> implements UserService {

    @Resource
    UserRepository userRepository;

    @Resource
    UserRegrepository userRegrepository;

    @Resource
    StoreRepository storeRepository;

    @Autowired
    MemberGroupRepository memberGroupRepository;

    @Resource(name = "permanentAddressRepository")
    PermanentAddressRepository addressRepository;

    @Autowired
    MembersRoleRepository membersRoleRepository;

    private User mergeUserRoles(final User user, final Set<MembersRole> roles) {
        final Store s = (Store) getCurrentContext().getStoreRealm();
        for (final MembersRole memberRole : roles) {
            if (memberRole.getId() == null) {
                if (memberRole.getStore() == null)
                    memberRole.setStore(s);

                memberRole.setMember(user);
                final MembersRolePK pk = new MembersRolePK();
                pk.setStoresStoreId(s.getStoreId());
                pk.setMemberId(user.getMemberId());
                pk.setRoleId(memberRole.getRole().getRoleId());
                memberRole.setId(pk);
            }
            user.getMembersRoles().add(memberRole);
        }

        return user;
    }

    @Override
    @Transactional
    public User create(User user) {

        if (user.getUserType().equals(UserType.ANONYMOUS))
            user.setUserType(UserType.GUEST);

        if (user.getPermanentAddresses() != null)
            for (final PermanentAddress address : user.getPermanentAddresses()) {
                address.setMember(user);
            }

        if (user.getPrimaryAddress() != null)
            user.getPrimaryAddress().setMember(user);

        final Set<MembersRole> roles = user.getMembersRoles();
        user.setMembersRoles(new HashSet<MembersRole>(0));

        user.setCreated(new Date());

        user = userRepository.saveAndFlush(user);

        if (!roles.isEmpty())
            user = mergeUserRoles(user, roles);

        // final UserStoreRelation r = new UserStoreRelation((Store) getCurrentContext().getStoreRealm(), user);
        // r.setUpdated(new Date());
        // user.getStoreRelation().add(r);
        final Store currentStore = (Store) getCurrentContext().getStoreRealm();
        // currentStore.getUsers().add(user);
        user.getStores().add(currentStore);

        return userRepository.saveAndFlush(user);

    }

    @Override
    @Transactional
    public User update(User user) {
        user = mergeUserRoles(user, user.getMembersRoles());
        for (final PermanentAddress address : user.getPermanentAddresses()) {
            if (address.getMember() == null)
                address.setMember(user);
        }

        return userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public UsersReg registerUser(final UsersReg reg, final PrimaryAddress primaryAddress) {

        final User u = create(reg);

        if (reg.getUserType().equals(UserType.ANONYMOUS) || reg.getUserType().equals(UserType.GUEST))
            reg.setUserType(UserType.REGISTERED);

        if (reg.getStatus() == null)
            reg.setStatus(UserRegStatus.ACTIVE);

        if (primaryAddress != null) {
            primaryAddress.setMember(u);
            reg.setPrimaryAddress(primaryAddress);
        }
        return userRegrepository.saveAndFlush(reg);
    }

    private Specification storeFilterSpec() {
        return isUserInStore((Store) getCurrentContext().getStoreRealm());
    }

    @Override
    public List<UsersReg> findByexample(final UsersReg example) {
        return userRegrepository.findAll(where(userRegrepository.makeSpecification(example)));
    }

    @Override
    @Transactional
    public User addAddress(final PermanentAddress address, final User user) {
        Assert.notNull(user);
        Assert.notNull(address);
        address.setMember(user);
        user.getPermanentAddresses().add(address);
        return update(user);

    }

    @Override
    @Transactional
    public User addAddress(final PermanentAddress address, final Long userId) {
        final User user = userRepository.findOne(userId);
        return addAddress(address, user);
    }

    @Override
    public User addRole(final Role role, final User user) {
        final MembersRole membersRole = new MembersRole(user, role, (Store) getCurrentContext().getStoreRealm());
        user.getMembersRoles().add(membersRole);
        return userRepository.save(user);
    }

    @Override
    public Repository<User, Long> getEntityRepository() {
        return userRepository;
    }

    @Override
    public Specification<User> getBaseSpec() {
        return storeFilterSpec();
    }

    @Override
    public Collection<Role> getUserRoles(final User user, final Store store) {

        return null;
    }

    @Override
    public User findAnonymous() {
        final List<User> users = userRepository.findAnonymous();
        Assert.notEmpty(users);
        return users.get(0);
    }

    @Override
    public Set<PermanentAddress> getUserAddress(final User user) {
        final User _u = userRegrepository.findOne(user.getUserId());
        _u.getPermanentAddresses().size();
        return _u.getPermanentAddresses();
    }

    @Override
    public Set<Membergroup> getUserGroups(final User user) {
        return memberGroupRepository.findMembergroupByUser(user, (Store) getCurrentContext().getStoreRealm());
    }

}
