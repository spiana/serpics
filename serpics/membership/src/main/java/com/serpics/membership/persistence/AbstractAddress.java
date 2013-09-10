package com.serpics.membership.persistence;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.serpics.util.gson.GsonTransient;

/**
 * The persistent class for the addresses database table.
 * 
 */
@Entity
@Table(name = "addresses")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "flag", discriminatorType = DiscriminatorType.STRING)
public abstract class AbstractAddress extends com.serpics.core.persistence.jpa.Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "address_id", unique = true, nullable = false)
	protected Long addressId;

	@Column(nullable = false, length = 40)
	protected String nickname;

	@Column(nullable = true, length = 50)
	protected String firstname;

	@Column(nullable = true, length = 50)
	protected String lastname;

	@Column(length = 80)
	protected String company;

	@Column(nullable = true, length = 60)
	protected String email;

	@Column(length = 80)
	protected String address1;

	@Column(length = 80)
	protected String address2;

	@Column(length = 80)
	protected String address3;

	@Column(length = 20)
	protected String zipcode;

	@Column(length = 50)
	protected String city;

	@Column(length = 30)
	protected String region;

	@Column(length = 30)
	protected String country;

	@Column(length = 50)
	protected String vatcode;

	@Column(length = 25)
	protected String phone;

	@Column(length = 25)
	protected String mobile;

	@Column(length = 25)
	protected String fax;

	@Column(length = 254)
	protected String field1;

	@Column(length = 254)
	protected String field2;

	@Column(precision = 10, scale = 4)
	protected BigDecimal field3;

	protected BigInteger field4;

	@Column(nullable = false, length = 1, updatable = false, insertable = false)
	protected String flag;

	@Column(nullable = false)
	protected int isprimary;

	// bi-directional many-to-one association to Member
	@GsonTransient
	@JsonIgnore
	@ManyToOne(targetEntity = Member.class, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "member_id", nullable = false)
	protected Member member;

	@PrePersist
	@PreUpdate
	public void preUpdated() {
		setUpdated(new Timestamp(new Date().getTime()));
	}

	public AbstractAddress() {
		this.nickname = UUID.randomUUID().toString();
	}

	public AbstractAddress(String nickname, String firstname, String lastname, String company, String email,
			String address1, String address2, String address3, String zipcode, String city, String region,
			String country, String vatcode) {
		super();
		this.nickname = nickname != null ? nickname : UUID.randomUUID().toString();
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

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return this.address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getField1() {
		return this.field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return this.field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public BigDecimal getField3() {
		return this.field3;
	}

	public void setField3(BigDecimal field3) {
		this.field3 = field3;
	}

	public BigInteger getField4() {
		return this.field4;
	}

	public void setField4(BigInteger field4) {
		this.field4 = field4;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getIsprimary() {
		return this.isprimary;
	}

	public void setIsprimary(int isprimary) {
		this.isprimary = isprimary;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getVatcode() {
		return this.vatcode;
	}

	public void setVatcode(String vatcode) {
		this.vatcode = vatcode;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
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

}