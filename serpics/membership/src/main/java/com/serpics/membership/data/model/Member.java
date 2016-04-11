package com.serpics.membership.data.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.serpics.core.data.jpa.AbstractEntity;
import com.serpics.membership.MemberType;

/**
 * The persistent class for the members database table.
 * 
 */
@XmlRootElement(name = "member")
@Entity
@Table(name = "members", uniqueConstraints = @UniqueConstraint(columnNames = { "uuid" }))
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "member_type", discriminatorType = DiscriminatorType.STRING)
@Access(AccessType.FIELD)
public class Member extends AbstractEntity implements Serializable {
  private static final long serialVersionUID = -8380078370041764459L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id", unique = true, nullable = false)
    protected Long id;

	@Size(max=1000, message="{member.field1.size}")
    @Column(length = 1000)
    protected String field1;

	@Size(max=254, message="{member.field2.size}")
    @Column(length = 254)
    protected String field2;

    @Column(precision = 10, scale = 4)
    protected Double field3;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_type", nullable = false)
    protected MemberType memberType;
    
    @Column(name="common_name" , length=200 , nullable= false)
    protected String commonName;

    @OneToOne( fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL , orphanRemoval=true)
    @JoinColumn(name="primary_address_id")
    protected PrimaryAddress primaryAddress;

    @OneToMany(mappedBy = "member", targetEntity = PermanentAddress.class, fetch = FetchType.LAZY, orphanRemoval = true)
    protected Set<PermanentAddress> permanentAddresses = new HashSet<PermanentAddress>(0);

    // bi-directional many-to-one association to MemberAttribute
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    protected Set<MemberAttribute> memberAttributes = new HashSet<MemberAttribute>(0);

    // bi-directional many-to-one association to MembersRole
    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    protected Set<MembersRole> membersRoles = new HashSet<MembersRole>(0);


    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    protected Set<Membergrouprel> memberGroupRel = new HashSet<Membergrouprel>(0);

    public Set<MemberAttribute> getMemberAttributes() {
        return memberAttributes;
    }

    public void setMemberAttributes(final Set<MemberAttribute> memberAttributes) {
        this.memberAttributes = memberAttributes;
    }

    public Member() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
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

    public Double getField3() {
        return this.field3;
    }

    public void setField3(final Double field3) {
        this.field3 = field3;
    }

    public MemberType getMemberType() {
        return this.memberType;
    }

    public void setMemberType(final MemberType memberType) {
        this.memberType = memberType;
    }

    public Set<PermanentAddress> getPermanentAddresses() {
        return permanentAddresses;
    }

  
    public PrimaryAddress getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(final PrimaryAddress primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public void addAdress(final PermanentAddress newAddress) {
        newAddress.setMember(this);
        this.getPermanentAddresses().add(newAddress);
    }

    public void setPermanentAddresses(final Set<PermanentAddress> permanentAddresses) {
        this.permanentAddresses = permanentAddresses;
    }

    public Set<Membergrouprel> getMemberGroupRel() {
        return memberGroupRel;
    }

    public void setMemberGroupRel(final Set<Membergrouprel> memberGroupRel) {
        this.memberGroupRel = memberGroupRel;
    }

    public Set<MembersRole> getMembersRoles() {
        return membersRoles;
    }

   

    public void setMembersRoles(final Set<MembersRole> membersRoles) {
        this.membersRoles = membersRoles;
    }

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
}