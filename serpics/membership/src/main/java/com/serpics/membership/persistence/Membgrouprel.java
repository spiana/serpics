package com.serpics.membership.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the membgrouprel database table.
 * 
 */
@Entity
@Table(name="membgrouprel")
public class Membgrouprel implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MembgrouprelPK id;

	@Column(nullable=false, length=1)
	private String status;

	@Column(nullable=false)
	private Timestamp updated;

    @Temporal( TemporalType.DATE)
	@Column(name="valid_from", nullable=false)
	private Date validFrom;

    @Temporal( TemporalType.DATE)
	@Column(name="valid_to", nullable=false)
	private Date validTo;

    public Membgrouprel() {
    }

	public MembgrouprelPK getId() {
		return this.id;
	}

	public void setId(MembgrouprelPK id) {
		this.id = id;
	}
	
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public Date getValidFrom() {
		return this.validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return this.validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

}