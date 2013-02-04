package com.serpics.base.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the attribute_lookup database table.
 * 
 */
@Entity
@Table(name="attribute_lookup" )
public class AttributeLookup implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AttributeLookupPK id;

	@Column(nullable=false)
	private double sequence;

	//bi-directional many-to-one association to BaseAttribute
    @ManyToOne
	@JoinColumn(name="base_attributes_id", nullable=false, insertable=false, updatable=false)
	private BaseAttribute baseAttribute;

    public AttributeLookup() {
    }

	public AttributeLookupPK getId() {
		return this.id;
	}

	public void setId(AttributeLookupPK id) {
		this.id = id;
	}
	
	public double getSequence() {
		return this.sequence;
	}

	public void setSequence(double sequence) {
		this.sequence = sequence;
	}

	public BaseAttribute getBaseAttribute() {
		return this.baseAttribute;
	}

	public void setBaseAttribute(BaseAttribute baseAttribute) {
		this.baseAttribute = baseAttribute;
	}
	
}