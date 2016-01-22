package com.serpics.jaxrs.data.converter;


import com.serpics.core.facade.Populator;
import com.serpics.jaxrs.data.AddressDataRequest;
import com.serpics.membership.facade.data.AddressData;

public class AddressDataRequestConverter implements Populator<AddressDataRequest, AddressData>{

		
	@Override
	public void populate(AddressDataRequest source, AddressData target) {
		
//		private String nickname;
//		private String firstname;
//		private String lastname;
//		private String company;
//		private String email;
//		private String address1;
//		private String address2;
//		private String address3;
//		private String streetNumber;
//		private String zipcode;
//		private String city;
//		private RegionData region;
//		private CountryData country;
//		private String vatcode;
//		private String idNumber;
//		private String phone;
//		private String mobile;
//		private String fax;
		
		target.setNickname(source.getNickname());
		target.setFirstname(source.getFirstname());
		target.setLastname(source.getLastname());
		target.setCompany(source.getCompany());
		target.setEmail(source.getEmail());
		target.setAddress1(source.getAddress1());
		target.setAddress2(source.getAddress2());
		target.setAddress3(source.getAddress3());

		
	}
}