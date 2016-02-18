package com.serpics.membership.data.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

import com.serpics.base.data.model.Region;

@XmlRootElement(name="address")
@Entity(name = "BillingAddress")
//@DiscriminatorValue("BILLING")
//@DiscriminatorOptions(force = true)
public class BillingAddress extends AbstractAddress {
	private static final long serialVersionUID = -5977534397660544584L;


	public BillingAddress() {
        super();
    }

    public BillingAddress(final String nickname, final String firstname, final String lastname, final String company, final String email,
            final String address1, final String streetNumber, final String address2, final String address3, final String zipcode, final String city, final Region region,
            final String country, final String vatcode) {
        super(nickname, firstname, lastname, company, email, address1, streetNumber, address2, address3, zipcode, city, region,
                vatcode);

    }

    // bi-directional many-to-one association to Member
    @OneToOne(mappedBy="billingAddress" ,fetch = FetchType.LAZY, optional = false , targetEntity=User.class)
    private User member;
  
    public User getUser() {
        return  member;
    }

    public void setUser(final User member) {
        this.member = member;
    }

}
