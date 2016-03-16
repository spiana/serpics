package com.serpics.catalog.data.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.serpics.base.data.model.MultilingualString;
import com.serpics.base.data.model.MultilingualText;

/**
 * The persistent class for the ctentry database table.
 * 
 */
@Entity
@Table(name = "ctentry" , uniqueConstraints= @UniqueConstraint(columnNames= {"catalog_id","ctentry_type", "code"}))
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "ctenytry_type", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Ctentry extends AbstractCatalogEntry implements Serializable {
    private static final long serialVersionUID = 1L;

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ctentry_id")
    protected Long id;

    @Column(name = "ctentry_type", nullable = false)
    protected Integer ctentryType;

    @NotNull
    @Size(max = 250)
    @Column(name = "code", nullable = false, length = 250)
    protected String code;

    protected String field1;

    protected Long field2;

    protected Double field3;

    @Column(name = "url", nullable = false, unique = true)
    protected String url;
    
    @ManyToOne
    @JoinColumn(name="primaryImage_id" , nullable= true)
    private CtentryMedia primaryImage;
    
    @OneToOne(cascade = { CascadeType.ALL }, orphanRemoval = true , fetch= FetchType.EAGER)
    @JoinColumn(name = "name_string_id")
    private MultilingualString name ;

    @OneToOne( cascade = { CascadeType.ALL }, orphanRemoval = true , fetch= FetchType.EAGER)
    @JoinColumn(name = "description_string_id")
    private MultilingualText description ;

    // bi-directional many-to-one association to CtentryAttribute
    @OneToMany(mappedBy = "ctentry", fetch = FetchType.LAZY)
    protected Set<CtentryAttribute> ctentryAttributes;

    // bi-directional many-to-one association to Media
   @OneToMany(mappedBy="ctentry" , fetch = FetchType.LAZY, cascade= CascadeType.REMOVE)
   protected Set<CtentryMedia> medias;

   @OneToOne(cascade = { CascadeType.ALL }, orphanRemoval = true , fetch= FetchType.EAGER)
   @JoinColumn(name = "meta_description")
   private MultilingualString metaDescription ;
   
   @OneToOne(cascade = { CascadeType.ALL }, orphanRemoval = true , fetch= FetchType.EAGER)
   @JoinColumn(name = "meta_keyword")
   private MultilingualString metaKeyword ;
   
    public Long getId() {
        return this.id;
    }

    public void setId(final Long ctentryId) {
        this.id = ctentryId;
    }

    public Integer getCtentryType() {
        return this.ctentryType;
    }

    public void setCtentryType(final Integer ctentryType) {
        this.ctentryType = ctentryType;
    }

    public String getField1() {
        return this.field1;
    }

    public void setField1(final String field1) {
        this.field1 = field1;
    }

    public Long getField2() {
        return this.field2;
    }

    public void setField2(final Long field2) {
        this.field2 = field2;
    }

    public Double getField3() {
        return this.field3;
    }

    public void setField3(final Double field3) {
        this.field3 = field3;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public Set<CtentryAttribute> getCtentryAttributes() {
        return this.ctentryAttributes;
    }

    public void setCtentryAttributes(final Set<CtentryAttribute> ctentryAttributes) {
        this.ctentryAttributes = ctentryAttributes;
    }

  
      public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

	public MultilingualString getName() {
		return name;
	}

	public void setName(MultilingualString name) {
		this.name = name;
	}

	public MultilingualText getDescription() {
		return description;
	}

	public void setDescription(MultilingualText description) {
		this.description = description;
	}

	public Set<CtentryMedia> getMedias() {
		return medias;
	}

	public void setMedias(Set<CtentryMedia> medias) {
		this.medias = medias;
	}

	public MultilingualString getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(MultilingualString metaDescription) {
		this.metaDescription = metaDescription;
	}

	public MultilingualString getMetaKeyword() {
		return metaKeyword;
	}

	public void setMetaKeyword(MultilingualString metaKeyword) {
		this.metaKeyword = metaKeyword;
	}

	public CtentryMedia getPrimaryImage() {
		return primaryImage;
	}

	public void setPrimaryImage(CtentryMedia primaryImage) {
		this.primaryImage = primaryImage;
	}

	
}