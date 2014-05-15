package com.serpics.membership.services;

import java.io.Serializable;

import com.serpics.core.service.EntityService;
import com.serpics.membership.persistence.Member;
import com.serpics.membership.persistence.PermanentAddress;
import com.serpics.membership.persistence.Role;

public interface MemberService<T extends Member, ID extends Serializable> extends EntityService<T, ID> {

    public T addAddress(final PermanentAddress address, final T member);

    public T addAddress(final PermanentAddress address, final ID memberId);

    public T addRole(final Role role, final T member);

}
