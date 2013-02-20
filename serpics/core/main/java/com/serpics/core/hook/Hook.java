package com.serpics.core.hook;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Hook {
	String store() default "default-store";

	String type() default "";
}
