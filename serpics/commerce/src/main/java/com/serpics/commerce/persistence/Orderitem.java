package com.serpics.commerce.persistence;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "0")
public class Orderitem extends AbstractOrderitem {

}
