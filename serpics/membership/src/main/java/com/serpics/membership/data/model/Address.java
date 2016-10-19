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
