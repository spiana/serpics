package com.serpics.catalog.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.restlet.resource.Get;

import com.serpics.base.persistence.Locale;

@Entity
public class FeatureValues extends com.serpics.core.persistence.jpa.Entity {

		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="featurevalue_id")
		private Long featureValueId;

		@ManyToOne
		@JoinColumn(name = "feature_id" , nullable=false)
		private Feature feature;
		
		@ManyToOne
		@JoinColumn(name = "product_id" , nullable=false)
		private AbstractProduct product;
		
		@ManyToOne
		@JoinColumn(name="locale_id" , nullable=true)
		private Locale locale;
		
		@Temporal(TemporalType.DATE)
		private Date dateValue;
		private Long longValue;
		private Double decimalValue;
		private String stringValue;
		@Lob
		private String lobValue;
		public Long getFeatureValueId() {
			return featureValueId;
		}
		public void setFeatureValueId(Long featureValueId) {
			this.featureValueId = featureValueId;
		}
		public Feature getFeature() {
			return feature;
		}
		public void setFeature(Feature feature) {
			this.feature = feature;
		}
		public AbstractProduct getProduct() {
			return product;
		}
		public void setProduct(AbstractProduct product) {
			this.product = product;
		}
		public Locale getLocale() {
			return locale;
		}
		public void setLocale(Locale locale) {
			this.locale = locale;
		}
		public Date getDateValue() {
			return dateValue;
		}
		public void setDateValue(Date dateValue) {
			this.dateValue = dateValue;
		}
		public Long getLongValue() {
			return longValue;
		}
		public void setLongValue(Long longValue) {
			this.longValue = longValue;
		}
		public Double getDecimalValue() {
			return decimalValue;
		}
		public void setDecimalValue(Double decimalValue) {
			this.decimalValue = decimalValue;
		}
		public String getStringValue() {
			return stringValue;
		}
		public void setStringValue(String stringValue) {
			this.stringValue = stringValue;
		}
		public String getLobValue() {
			return lobValue;
		}
		public void setLobValue(String lobValue) {
			this.lobValue = lobValue;
		}
}
