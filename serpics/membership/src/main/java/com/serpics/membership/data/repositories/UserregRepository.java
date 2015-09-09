package com.serpics.membership.data.repositories;

import com.serpics.core.data.Repository;
import com.serpics.membership.data.model.UsersReg;

public interface UserregRepository extends Repository<UsersReg, Long> {

    public UsersReg findBylogonid(String logonid);


}
