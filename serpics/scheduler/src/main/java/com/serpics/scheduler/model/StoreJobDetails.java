package com.serpics.scheduler.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "1")
public class StoreJobDetails extends JobDetails {

	private static final long serialVersionUID = -8448017230606491472L;

}
