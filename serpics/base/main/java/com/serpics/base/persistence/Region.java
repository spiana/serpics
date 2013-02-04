package com.serpics.base.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the regions database table.
 * 
 */
@Entity
@Table(name="regions" )
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="regions_id", unique=true, nullable=false)
	private Long regionsId;

	@Column(nullable=false, length=100)
	private String description;

	@Column(nullable=false, length=100)
	private String name;

	//bi-directional many-to-one association to Country
    @ManyToOne
	@JoinColumn(name="countries_id", nullable=false)
	private Country country;

	//bi-directional many-to-one association to RegionsDescr
	@OneToMany(mappedBy="region")
	private Set<RegionsDescr> regionsDescrs;

    public Region() {
    }

	public Long getRegionsId() {
		return this.regionsId;
	}

	public void setRegionsId(Long regionsId) {
		this.regionsId = regionsId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	public Set<RegionsDescr> getRegionsDescrs() {
		return this.regionsDescrs;
	}

	public void setRegionsDescrs(Set<RegionsDescr> regionsDescrs) {
		this.regionsDescrs = regionsDescrs;
	}
	
}