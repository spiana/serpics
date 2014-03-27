package com.serpics.membership.services;

import com.serpics.core.service.EntityService;
import com.serpics.membership.persistence.UsersReg;

public interface UserRegService extends EntityService<UsersReg, Long> {

    public UsersReg findByLoginid(String loginid);



}
