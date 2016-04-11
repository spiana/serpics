package com.serpics.catalog.data.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.serpics.base.data.model.BaseAttribute;
import com.serpics.base.data.model.MultiValueAttribute;
import com.serpics.base.data.model.MultilingualString;


/**
 * The persistent class for the ctentry_attributes database table.
 * 
 */
@Entity
@Table(name="variant_attributes" )
public class VariantAttribute extends AbstractCatalogEntry implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="attribute_id")
    private Long id;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="base_attribute_id" ,updatable=false)
    private BaseAttribute baseAttribute;
    
    private double sequence;

    //bi-directional many-to-one association to Ctentry
    @ManyToOne
    @JoinColumn(name="product_id")
    private ProductVariant product;

    @Embedded
    private MultiValueAttribute value;
    
    @OneToOne(cascade = { CascadeType.ALL }, orphanRemoval = true , fetch= FetchType.EAGER)
    @JoinColumn(name = "localize_string_id")
    private MultilingualString localize ;
    
    public VariantAttribute() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }


    public double getSequence() {
        return this.sequence;
    }

    public void setSequence(final double sequence) {
        this.sequence = sequence;
    }

    public BaseAttribute getBaseAttribute() {
        return baseAttribute;
    }

    public void setBaseAttribute(final BaseAttribute baseAttribute) {
        this.baseAttribute = baseAttribute;
    }

	public MultiValueAttribute getValue() {
		return value;
	}

	public void setValue(MultiValueAttribute value) {
		this.value = value;
	}

	public ProductVariant getProduct() {
		return product;
	}

	public void setProduct(ProductVariant product) {
		this.product = product;
	}

	public MultilingualString getLocalize() {
		return localize;
	}

	public void setLocalize(MultilingualString localize) {
		this.localize = localize;
	}
}