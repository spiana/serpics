package com.serpics.jaxrs;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

public class JaxbMapper  extends ObjectMapper{

	public JaxbMapper() {
		super();
//		AnnotationIntrospector introspector2 = new JaxbAnnotationIntrospector();
//		//AnnotationIntrospector introspector1 = new JacksonAnnotationIntrospector();
//		//AnnotationIntrospector pair = new AnnotationIntrospector.Pair(introspector1, introspector2);				
//		super.setAnnotationIntrospector(introspector2);
////		configure(SerializationConfig.Feature.USE_ANNOTATIONS, true);
////		configure(SerializationConfig.Feature.AUTO_DETECT_FIELDS , true);
////		configure(SerializationConfig.Feature.AUTO_DETECT_GETTERS, true);
////		setSerializationInclusion(Inclusion.NON_NULL);
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss z");
//		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
//		setDateFormat(dateFormat);
		
	}
}
