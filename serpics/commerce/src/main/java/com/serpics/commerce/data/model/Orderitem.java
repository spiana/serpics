package com.serpics.commerce.data.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "0")
public class Orderitem extends AbstractOrderitem {

	private static final long serialVersionUID = -3290337831602573616L;
	
}
