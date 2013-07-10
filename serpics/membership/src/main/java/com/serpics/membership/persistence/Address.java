package com.serpics.membership.persistence;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.serpics.core.datatype.AddressType;

@Entity(name="Address")
@DiscriminatorValue(AddressType.TEMPORARY)
public class Address extends AbstractAddress {

	public Address() {
		super();
		
	}

	public Address(String nickname, String firstname, String lastname,
			String company, String email, String address1, String address2,
			String address3, String zipcode, String city, String region,
			String country, String vatcode) {
		super(nickname, firstname, lastname, company, email, address1, address2,
				address3, zipcode, city, region, country, vatcode);
		
	}

	
}
