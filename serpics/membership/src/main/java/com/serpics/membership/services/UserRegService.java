package com.serpics.membership.services;

import com.serpics.membership.data.model.UsersReg;

public interface UserRegService extends MemberService<UsersReg, Long> {

    public UsersReg findByLoginid(String loginid);



}
