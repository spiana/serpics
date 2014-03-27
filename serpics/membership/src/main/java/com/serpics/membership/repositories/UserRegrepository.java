package com.serpics.membership.repositories;

import com.serpics.core.data.Repository;
import com.serpics.membership.persistence.UsersReg;

public interface UserRegrepository extends Repository<UsersReg, Long> {

    public UsersReg findBylogonid(String logonid);


}
