package com.serpics.base.data.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.serpics.base.MediaSupportType;
import com.serpics.core.data.jpa.AbstractEntity;


/**
 * The persistent class for the media database table.
 * 
 */
@Entity
@Table(name="media")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Media extends AbstractEntity{
	private static final long serialVersionUID = -952890230499530957L;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="media_id")
    private Long id;

    @Column(name="content_type")
    @NotNull
    private String contentType;

    private String name;

    private double sequence;

    @Enumerated
	private MediaSupportType type ;
    
    private String source;
    
//    @OneToMany(mappedBy="media")
//    Set<MediaSupport> supports ;
    
    @Column(name="store_id",nullable = false)
    private Long storeId;
   
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "description_string_id")
    private final MultilingualString description = new MultilingualString();

    public Media() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long mediaId) {
        this.id = mediaId;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }

   
    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public double getSequence() {
        return this.sequence;
    }

    public void setSequence(final double sequence) {
        this.sequence = sequence;
    }

  

    public MultilingualString getDescription() {
        return description;
    }

	public MediaSupportType getType() {
		return type;
	}

	public void setType(MediaSupportType type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}



}