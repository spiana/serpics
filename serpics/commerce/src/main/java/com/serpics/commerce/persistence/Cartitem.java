package com.serpics.commerce.persistence;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "1")
public class Cartitem extends AbstractOrderitem {


}
