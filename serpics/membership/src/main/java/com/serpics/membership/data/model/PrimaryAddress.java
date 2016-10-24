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
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.xml.bind.annotation.XmlRootElement;

import com.serpics.base.data.model.District;
import com.serpics.base.data.model.Region;

@XmlRootElement(name="address")
@Entity(name = "PrimaryAddress")
//@DiscriminatorValue("PRIMARY")
//@DiscriminatorOptions(force = true)
public class PrimaryAddress extends AbstractAddress {

    private static final long serialVersionUID = 1L;

    public PrimaryAddress() {
        super();
    }

    public PrimaryAddress(final String nickname, final String firstname, final String lastname, final String company, final String email,
            final String address1, final String streetNumber, final String address2, final String address3, final String zipcode, final String city, final Region region,
            final String country, final District district, final String vatcode) {
        super(nickname, firstname, lastname, company, email, address1, streetNumber, address2, address3, zipcode, city, region, district,
               vatcode);

    }

    // bi-directional many-to-one association to Member
    @OneToOne(mappedBy="primaryAddress", optional = false , fetch=FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Member member;

    public Member getMember() {
        return member;
    }

    public void setMember(final Member member) {
        this.member = member;
    }

}
