package com.serpics.membership.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DiscriminatorOptions;

import com.serpics.membership.AddressType;

/**
 * The persistent class for the addresses database table.
 * 
 */
@Entity
@Table(name = "addresses")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "flag", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorOptions(force = true)
public abstract class AbstractAddress extends com.serpics.core.data.jpa.AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id", unique = true, nullable = false)
    protected Long addressId;

    @Column(nullable = true, length = 50)
    protected String firstname;

    @Column(nullable = true, length = 50)
    protected String lastname;

    @Size(max = 80)
    @Column(length = 80)
    protected String company;

    @Size(max = 60)
    @Column(nullable = true, length = 60)
    protected String email;

    @Size(max = 512)
    @Column(length = 512)
    protected String address1;

    @Size(max = 512)
    @Column(length = 512)
    protected String address2;

    @Size(max = 512)
    @Column(length = 512)
    protected String address3;

    @Size(max = 20)
    @Column(length = 20)
    protected String zipcode;

    @Size(max = 200)
    @Column(length = 200)
    protected String city;

    @Size(max = 100)
    @Column(length = 100)
    protected String region;

    @Size(max = 50)
    @Column(length = 50)
    protected String country;

    @Size(max = 30)
    @Column(length = 30)
    protected String vatcode;

    @Size(max = 25)
    @Column(length = 25)
    protected String phone;

    @Size(max = 25)
    @Column(length = 25)
    protected String mobile;

    @Size(max = 25)
    @Column(length = 25)
    protected String fax;

    @Size(max = 250)
    @Column(length = 250)
    protected String field1;

    @Size(max = 250)
    @Column(length = 254)
    protected String field2;

    @Column(precision = 10, scale = 4)
    protected Float field3;

    protected Long field4;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false, insertable = false)
    protected AddressType flag;

    public AbstractAddress() {

    }

    public AbstractAddress(final String firstname, final String lastname, final String company, final String email,
            final String address1, final String address2, final String address3, final String zipcode, final String city, final String region,
            final String country, final String vatcode) {
        super();

        this.firstname = firstname;
        this.lastname = lastname;
        this.company = company;
        this.email = email;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.zipcode = zipcode;
        this.city = city;
        this.region = region;
        this.country = country;
        this.vatcode = vatcode;
    }

    public Long getAddressId() {
        return this.addressId;
    }

    public void setAddressId(final Long addressId) {
        this.addressId = addressId;
    }

    public String getAddress1() {
        return this.address1;
    }

    public void setAddress1(final String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return this.address2;
    }

    public void setAddress2(final String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return this.address3;
    }

    public void setAddress3(final String address3) {
        this.address3 = address3;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(final String company) {
        this.company = company;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getField1() {
        return this.field1;
    }

    public void setField1(final String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return this.field2;
    }

    public void setField2(final String field2) {
        this.field2 = field2;
    }

    public Float getField3() {
        return this.field3;
    }

    public void setField3(final Float field3) {
        this.field3 = field3;
    }

    public Long getField4() {
        return this.field4;
    }

    public void setField4(final Long field4) {
        this.field4 = field4;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }

    public AddressType getFlag() {
        return this.flag;
    }

    public void setFlag(final AddressType flag) {
        this.flag = flag;
    }


    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }


    public String getRegion() {
        return this.region;
    }

    public void setRegion(final String region) {
        this.region = region;
    }

    public String getVatcode() {
        return this.vatcode;
    }

    public void setVatcode(final String vatcode) {
        this.vatcode = vatcode;
    }

    public String getZipcode() {
        return this.zipcode;
    }

    public void setZipcode(final String zipcode) {
        this.zipcode = zipcode;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(final String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(final String fax) {
        this.fax = fax;
    }

}