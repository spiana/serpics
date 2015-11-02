package com.serpics.membership.data.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.serpics.base.data.model.Region;
import com.serpics.util.gson.GsonTransient;

@Entity(name="Address")
@DiscriminatorValue("TEMPORARY")
@XmlRootElement(name="address")
public class Address extends AbstractAddress {

	private static final long serialVersionUID = -3506267698918264420L;

	public Address() {
        super();

    }

    public Address(final String nickname, final String firstname, final String lastname,
            final String company, final String email, final String address1, final String streetNumber, final String address2,
            final String address3, final String zipcode, final String city, final Region region,
            final String country, final String vatcode) {
        super(nickname, firstname, lastname, company, email, address1, streetNumber, address2,
                address3, zipcode, city, region,  vatcode);

    }


    // bi-directional many-to-one association to Member
    @GsonTransient
    @ManyToOne(fetch = FetchType.LAZY, optional = false )
    @JoinColumn(name = "member_id", nullable = false, updatable = false )
    private Member member;

    @XmlTransient
    public Member getMember() {
        return member;
    }

    public void setMember(final Member member) {
        this.member = member;
    }

}
