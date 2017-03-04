/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.base.data.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
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
import javax.validation.constraints.Pattern;

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
    @NotNull(message = "{media.contentType.notnull}")
    private String contentType;

    @Pattern(regexp="[a-zA-Z0-9]+", message = "{media.name.pattern}")
    private String name;

    private String sourcePath;
    
    private double sequence;

    @Enumerated(EnumType.ORDINAL)
	private MediaSupportType type ;
    
    private String source;
  
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "description_string_id")
    private final MultilingualString description = new MultilingualString();

    public Media() {
    	type = MediaSupportType.REMOTE;
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

	public String getSourcePath() {
		return sourcePath;
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}


}