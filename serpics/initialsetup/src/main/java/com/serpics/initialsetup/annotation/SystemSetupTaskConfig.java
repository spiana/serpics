package com.serpics.initialsetup.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface SystemSetupTaskConfig {
	
	String store() default "default-store";
	
	String catalog() default "";
	
	int order();
	
	String description() default "";
	
}
