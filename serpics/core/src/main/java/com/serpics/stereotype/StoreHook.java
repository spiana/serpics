package com.serpics.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface StoreHook {
	String value();

	String store() default "default-store";

	boolean override() default false;
	String scope() default "store";
}
