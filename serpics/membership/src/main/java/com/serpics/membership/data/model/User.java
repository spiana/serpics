package com.serpics.membership.data.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.serpics.base.data.model.Store;
import com.serpics.commerce.core.Customer;
import com.serpics.core.security.UserDetail;
import com.serpics.membership.MemberType;
import com.serpics.membership.UserType;

/**
 * The persistent class for the users database table.
 * 
 */

@XmlRootElement(name="user")
@Entity(name = "User")
@Table(name = "users")
@DiscriminatorValue("USER")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends Member implements UserDetail, Customer{
 	private static final long serialVersionUID = 545575485685613766L;

 	@Size(max=200, message="{user.firstname.size}")
	@Column(length = 200)
    private String firstname;

    @Column(length = 200, nullable = false)
    @NotNull(message="{user.lastname.notnull}")
    private String lastname;

    @Size(max = 25, message = "{user.phone.size}")
    @Column(length = 25)
    private String phone;

    @Size(max=200, message="{user.email.size}")
    @Column(length = 254)
    private String email;

    @OneToOne( fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL ,orphanRemoval=true)
    @JoinColumn(name="billing_address_id")
    protected BillingAddress billingAddress;
    
    private Integer field4;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    private UserType userType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_visit")
    private Date lastVisit;
  
    @ManyToMany(fetch = FetchType.LAZY )
    @JoinTable(name = "user2storerel", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "store_id"))
    protected Set<Store> stores = new HashSet<Store>(0);

    public User() {
        this.userType = UserType.ANONYMOUS;
        this.memberType = MemberType.USER;
    }

    public User(final UserType userType, final String firstname, final String lastname, final String phone,
            final String email) {
        super();

        this.userType = userType == null ? UserType.ANONYMOUS : userType;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.memberType = MemberType.USER;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Integer getField4() {
        return this.field4;
    }

    public void setField4(final Integer field4) {
        this.field4 = field4;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }

    public void setLastVisit(final Date lastVisit) {
        this.lastVisit = lastVisit;
    }

    public Date getLastVisit() {
        return lastVisit;
    }

    public UserType getUserType() {
        return this.userType;
    }

    public void setUserType(final UserType userType) {
        this.userType = userType;
    }

    public boolean hasRole(final String role, final Long storeId) {

        for (final MembersRole mrole : membersRoles) {
            if (mrole.getStore().getId().equals(storeId) && mrole.getRole().getName().equals(role))
                return true;
        }

        return false;
    }


    @Override
    public String getName() {
    	return this.email;
    }
    

    @XmlTransient
    public Set<Store> getStores() {
        return stores;
    }

    public void setStores(final Set<Store> stores) {
        this.stores = stores;
    }

	public BillingAddress getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(BillingAddress billingAddress) {
		this.billingAddress = billingAddress;
	}

}