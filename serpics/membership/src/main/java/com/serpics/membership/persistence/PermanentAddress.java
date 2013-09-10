package com.serpics.membership.persistence;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

import com.serpics.core.datatype.AddressType;

@XmlRootElement(name="address")
@Entity(name = "PermanentAddress")
@DiscriminatorValue(AddressType.PERMANENT)
public class PermanentAddress extends AbstractAddress {

	private static final long serialVersionUID = 1L;

	public PermanentAddress() {
		super();
	}

	public PermanentAddress(String nickname, String firstname, String lastname, String company, String email,
			String address1, String address2, String address3, String zipcode, String city, String region,
			String country, String vatcode) {
		super(nickname, firstname, lastname, company, email, address1, address2, address3, zipcode, city, region,
				country, vatcode);

	}

}
