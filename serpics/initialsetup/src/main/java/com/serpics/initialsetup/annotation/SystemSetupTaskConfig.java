package com.serpics.initialsetup.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({TYPE})
public @interface SystemSetupTaskConfig {
	
	String store() default "default-store";
	
	String catalog() default "";
	
	int order();
	
	String description() default "";
	
}
