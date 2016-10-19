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
package com.serpics.catalog.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the ctentry_relation database table.
 * 
 */
@Embeddable
public class CtentryRelationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ctentry_id_child")
	private Long ctentryIdChild;
	
	@Column(name="ctentry_id_parent")
	private Long ctentryIdParent;

    public CtentryRelationPK() {
    	
    }
	public CtentryRelationPK(Long ctentryIdParent, Long ctentryIdChild) {
		super();
		this.ctentryIdParent = ctentryIdParent;
		this.ctentryIdChild = ctentryIdChild;
	}
	
	public Long getCtentryIdParent() {
		return this.ctentryIdParent;
	}
	public void setCtentryIdParent(Long ctentryIdParent) {
		this.ctentryIdParent = ctentryIdParent;
	}
	public Long getCtentryIdChild() {
		return this.ctentryIdChild;
	}
	public void setCtentryIdChild(Long ctentryIdChild) {
		this.ctentryIdChild = ctentryIdChild;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CtentryRelationPK)) {
			return false;
		}
		CtentryRelationPK castOther = (CtentryRelationPK)other;
		return 
			this.ctentryIdParent.equals(castOther.ctentryIdParent)
			&& this.ctentryIdChild.equals(castOther.ctentryIdChild);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.ctentryIdParent.hashCode();
		hash = hash * prime + this.ctentryIdChild.hashCode();
		
		return hash;
    }
}