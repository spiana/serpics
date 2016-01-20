package com.serpics.initialsetup.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * Declare which store, catalog and order to run Task.
 *  * 
 * @author alessandro.marasco@tinvention.net
 *
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface SystemSetupTaskConfig {
	
	/**
	 * 
	 * @return The realm name of store, Default is default-store
	 */
	String store() default "default-store";
	
	/**
	 * 
	 * @return The name of catalog. Value of default is empty and the system select the default catalog
	 */
	String catalog() default "";
	
	/**
	 * 
	 * @return Order to execute Task
	 */
	int order();
	
	/**
	 * 
	 * @return Small description for log text.
	 */
	String description() default "";
	
}
