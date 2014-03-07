package com.serpics.membership.persistence;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.DiscriminatorOptions;

import com.serpics.util.gson.GsonTransient;

@XmlRootElement(name="address")
@Entity(name = "PrimaryAddress")
@DiscriminatorValue("PRIMARY")
@DiscriminatorOptions(force = true)
public class PrimaryAddress extends AbstractAddress {

    private static final long serialVersionUID = 1L;

    public PrimaryAddress() {
        super();
    }

    public PrimaryAddress(final String firstname, final String lastname, final String company, final String email,
            final String address1, final String address2, final String address3, final String zipcode, final String city, final String region,
            final String country, final String vatcode) {
        super(firstname, lastname, company, email, address1, address2, address3, zipcode, city, region,
                country, vatcode);

    }

    // bi-directional many-to-one association to Member
    @GsonTransient
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "member_id", nullable = false, updatable = false)
    private Member member;

    @XmlTransient
    public Member getMember() {
        return member;
    }

    public void setMember(final Member member) {
        this.member = member;
    }

}
