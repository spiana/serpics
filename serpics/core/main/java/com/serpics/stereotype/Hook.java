package com.serpics.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.persistence.Inheritance;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Hook {
	public String value();
}
