package com.serpics.membership.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.core.service.Membership;
import com.serpics.membership.persistence.PrimaryAddress;
import com.serpics.membership.persistence.Store;
import com.serpics.membership.persistence.User;
import com.serpics.membership.persistence.UsersReg;

public interface MembershipService extends Membership {

    public Store createStore(Store store);

    public User createUser(User user);

    public User createUser(User user, PrimaryAddress primaryAddress);

    public UsersReg registerUser(UsersReg reg, PrimaryAddress primaryAddress);

    public User updateUser(User user);

    public List<User> findAll();

    public Page<User> fetchAllUser(Pageable pageble);

    public List<User> findbyexample(User example);

    public List<UsersReg> findbyexample(UsersReg example);

}
