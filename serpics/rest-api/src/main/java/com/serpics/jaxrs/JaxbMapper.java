package com.serpics.jaxrs;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

public class JaxbMapper  extends ObjectMapper{

	public JaxbMapper() {
		super();
		AnnotationIntrospector introspector2 = new JaxbAnnotationIntrospector();
		//AnnotationIntrospector introspector1 = new JacksonAnnotationIntrospector();
		//AnnotationIntrospector pair = new AnnotationIntrospector.Pair(introspector1, introspector2);				
		super.setAnnotationIntrospector(introspector2);
		configure(SerializationConfig.Feature.USE_ANNOTATIONS, true);
		configure(SerializationConfig.Feature.AUTO_DETECT_FIELDS , true);
		configure(SerializationConfig.Feature.AUTO_DETECT_GETTERS, true);
		getSerializationConfig().withSerializationInclusion(Inclusion.NON_NULL);
	}


}
