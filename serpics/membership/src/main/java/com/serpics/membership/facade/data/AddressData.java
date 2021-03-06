/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.membership.facade.data;

import com.serpics.base.facade.data.CountryData;
import com.serpics.base.facade.data.DistrictData;
import com.serpics.base.facade.data.RegionData;
import com.serpics.core.facade.AbstractData;


public class AddressData extends AbstractData{

	protected String nickname;
	protected String firstname;
	protected String lastname;
	protected String company;
	protected String email;
	protected String address1;
	protected String address2;
	protected String address3;
	protected String streetNumber;
	protected String zipcode;
	protected String city;
	protected RegionData region;
	protected CountryData country;
	protected DistrictData district;
	protected String vatcode;
	protected String idNumber;
	protected String phone;
	protected String mobile;
	protected String fax;
	protected String field1;
	protected String field2;
	protected Float field3;
	protected Long field4;
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(String streeNumber) {
		this.streetNumber = streeNumber;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public RegionData getRegion() {
		return region;
	}
	public void setRegion(RegionData region) {
		this.region = region;
	}
	public CountryData getCountry() {
		return country;
	}
	public void setCountry(CountryData country) {
		this.country = country;
	}
	public String getVatcode() {
		return vatcode;
	}
	public void setVatcode(String vatcode) {
		this.vatcode = vatcode;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getField1() {
		return field1;
	}
	public void setField1(String field1) {
		this.field1 = field1;
	}
	public String getField2() {
		return field2;
	}
	public void setField2(String field2) {
		this.field2 = field2;
	}
	public Float getField3() {
		return field3;
	}
	public void setField3(Float field3) {
		this.field3 = field3;
	}
	public Long getField4() {
		return field4;
	}
	public void setField4(Long field4) {
		this.field4 = field4;
	}
	public DistrictData getDistrict() {
		return district;
	}
	public void setDistrict(DistrictData district) {
		this.district = district;
	}

	
}
