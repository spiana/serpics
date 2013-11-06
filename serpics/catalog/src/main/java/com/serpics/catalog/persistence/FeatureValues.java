package com.serpics.catalog.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.serpics.base.persistence.Locale;

@Entity
public class FeatureValues extends com.serpics.core.persistence.jpa.Entity {

		@Id
		@Column(name="featurevalue_id")
		private Long featureValueId;

		@ManyToOne
		@JoinColumn(name = "feature_id")
		private Feature feature;
		
		@OneToMany
		@JoinColumn(name="locale_id" , nullable=true)
		private Locale locale;
		
		@Temporal(TemporalType.DATE)
		private Date dateValue;
		private Long longValue;
		private Double decimalValue;
		private String stringValue;
		@Lob
		private String lobValue;
}
