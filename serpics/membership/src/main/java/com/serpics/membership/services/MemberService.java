package com.serpics.membership.services;

import java.io.Serializable;

import com.serpics.membership.data.model.Member;
import com.serpics.membership.data.model.PermanentAddress;
import com.serpics.membership.data.model.Role;

public interface MemberService<T extends Member, ID extends Serializable> {

    public T addAddress(final PermanentAddress address, final T member);

    public T addAddress(final PermanentAddress address, final ID memberId);

    public T addRole(final Role role, final T member);

}
