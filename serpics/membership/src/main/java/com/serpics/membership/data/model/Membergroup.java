package com.serpics.membership.data.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;




/**
 * The persistent class for the membergroups database table.
 * 
 */
@Entity
@Table(name="membergroups")
@XmlRootElement
public class Membergroup  extends com.serpics.core.data.jpa.AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="membergroups_id", unique=true, nullable=false)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String name;

    @Size(max = 254)
    @Column(length=254)
    private String description;

    //bi-directional many-to-one association to Store
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="store_id", nullable=false)
    private Store store;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "membergroup", cascade = CascadeType.ALL)
    private Set<Membergrouprel> groupRelation = new HashSet<Membergrouprel>(0);

    public Membergroup() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long membergroupsId) {
        this.id = membergroupsId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Store getStore() {
        return this.store;
    }

    public void setStore(final Store store) {
        this.store = store;
    }

    public Set<Membergrouprel> getGroupRelation() {
        return groupRelation;
    }

    public void setGroupRelation(final Set<Membergrouprel> groupRelation) {
        this.groupRelation = groupRelation;
    }

}