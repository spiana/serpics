package com.serpics.jaxrs;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

public class JaxbMapper  extends ObjectMapper{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4414515344211684147L;

	public JaxbMapper() {
		super();
		AnnotationIntrospector introspector2 = new JacksonAnnotationIntrospector();
//		AnnotationIntrospector introspector1 = new JacksonAnnotationIntrospector();
//		AnnotationIntrospector pair = new AnnotationIntrospector.Pair(introspector2, introspector1);				
		super.setAnnotationIntrospector(introspector2);
		configure(MapperFeature.USE_ANNOTATIONS, true);
		configure(MapperFeature.AUTO_DETECT_FIELDS , true);
		configure(MapperFeature.AUTO_DETECT_GETTERS, true);
//		setSerializationInclusion(Inclusion.NON_NULL);
		setSerializationInclusion(JsonInclude.Include.NON_NULL);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss z");
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		setDateFormat(dateFormat);
		
	}
}
