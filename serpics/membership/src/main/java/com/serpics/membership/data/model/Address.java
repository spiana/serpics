package com.serpics.membership.data.model;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

import com.serpics.base.data.model.District;
import com.serpics.base.data.model.Region;

@Entity(name="Address")
//@DiscriminatorValue("TEMPORARY")
@XmlRootElement(name="address")
public class Address extends AbstractAddress {

	private static final long serialVersionUID = -3506267698918264420L;

	public Address() {
        super();

    }

    public Address(final String nickname, final String firstname, final String lastname,
            final String company, final String email, final String address1, final String streetNumber, final String address2,
            final String address3, final String zipcode, final String city, final Region region, final District district,
            final String country, final String vatcode) {
        super(nickname, firstname, lastname, company, email, address1, streetNumber, address2,
                address3, zipcode, city, region, district,  vatcode);

    }
    

}
