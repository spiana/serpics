package com.serpics.initialsetup.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;
/**
 * Annotation type that permit to configure order of importer.
 * To Set Store and catalog in which to import data  
 * 
 * @author alessandro.marasco@tinvention.net
 *
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface SystemSetupTaskConfig {
	
	String store() default "default-store";
	
	String catalog() default "";
	
	int order();
	
	String description() default "";
	
}
