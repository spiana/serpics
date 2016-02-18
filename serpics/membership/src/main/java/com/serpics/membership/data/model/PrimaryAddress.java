package com.serpics.membership.data.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.xml.bind.annotation.XmlRootElement;

import com.serpics.base.data.model.Region;

@XmlRootElement(name="address")
@Entity(name = "PrimaryAddress")
//@DiscriminatorValue("PRIMARY")
//@DiscriminatorOptions(force = true)
public class PrimaryAddress extends AbstractAddress {

    private static final long serialVersionUID = 1L;

    public PrimaryAddress() {
        super();
    }

    public PrimaryAddress(final String nickname, final String firstname, final String lastname, final String company, final String email,
            final String address1, final String streetNumber, final String address2, final String address3, final String zipcode, final String city, final Region region,
            final String country, final String vatcode) {
        super(nickname, firstname, lastname, company, email, address1, streetNumber, address2, address3, zipcode, city, region,
               vatcode);

    }

    // bi-directional many-to-one association to Member
    @OneToOne(mappedBy="primaryAddress", optional = false , fetch=FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Member member;

    public Member getMember() {
        return member;
    }

    public void setMember(final Member member) {
        this.member = member;
    }

}
