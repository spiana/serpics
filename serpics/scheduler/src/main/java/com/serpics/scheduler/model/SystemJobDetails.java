package com.serpics.scheduler.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "0")
public class SystemJobDetails extends JobDetails {

	private static final long serialVersionUID = 2014571186164990918L;

}
