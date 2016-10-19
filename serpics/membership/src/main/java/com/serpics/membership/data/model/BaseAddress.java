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
package com.serpics.membership.data.model;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import com.serpics.base.data.model.Country;
import com.serpics.base.data.model.District;
import com.serpics.base.data.model.Region;


@MappedSuperclass
public abstract class BaseAddress {

		@Size(max=250, message="{baseAddress.address1.size}")
	    @Column(length = 512)
	    protected String address1;
	  
		@Size(max=250, message="{baseAddress.address2.size}")
	    @Column(length = 512)
	    protected String address2;

		@Size(max=250, message="{baseAddress.address3.size}")
	    @Column(length = 512)
	    protected String address3;


		@Size(max=10, message="{baseAddress.streetNumber.size}")
	    @Column(nullable = true, length = 10)
	    protected String streetNumber;
	    
		@Size(max=20, message="{baseAddress.zipcode.size}")
	    @Column(length = 20)
	    protected String zipcode;

		@Size(max=200, message="{baseAddress.city.size}")
	    @Column(length = 200)
	    protected String city;

	    @ManyToOne
	    @JoinColumn(name = "district_id", nullable = true)
	    protected District district;
	    
	    @ManyToOne
	    @JoinColumn(name = "region_id", nullable = true)
	    protected Region region;

	    @ManyToOne
	    @JoinColumn(name = "country_id", nullable = true)
	    protected Country country;

	    @Size(max=25, message="{baseAddress.phone.size}")
	    @Column(length = 25)
	    protected String phone;

	    @Size(max=25, message="{baseAddress.mobile.size}")
	    @Column(length = 25)
	    protected String mobile;

	    @Size(max=25, message="{baseAddress.fax.size}")
	    @Column(length = 25)
	    protected String fax;

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

		public void setStreetNumber(String streetNumber) {
			this.streetNumber = streetNumber;
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

		public Region getRegion() {
			return region;
		}

		public void setRegion(Region region) {
			this.region = region;
		}

		public Country getCountry() {
			return country;
		}

		public void setCountry(Country country) {
			this.country = country;
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

		public District getDistrict() {
			return district;
		}

		public void setDistrict(District district) {
			this.district = district;
		}
}
