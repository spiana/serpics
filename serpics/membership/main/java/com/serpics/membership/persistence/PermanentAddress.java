package com.serpics.membership.persistence;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.annotations.DiscriminatorOptions;

import com.serpics.core.datatype.AddressType;

@Entity(name="PermanentAddress")
@DiscriminatorValue(AddressType.PERMANENT)
public class PermanentAddress extends AbstractAddress {

	public PermanentAddress() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PermanentAddress(String nickname, String firstname, String lastname,
			String company, String email, String address1, String address2,
			String address3, String zipcode, String city, String region,
			String country, String vatcode) {
		super(nickname, firstname, lastname, company, email, address1, address2,
				address3, zipcode, city, region, country, vatcode);
		// TODO Auto-generated constructor stub
	}

	
}
