package com.serpics.jax.rs;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

public class JaxbMapper  extends ObjectMapper{

	public JaxbMapper() {
		super();
		AnnotationIntrospector introspector = new JaxbAnnotationIntrospector();
		super.setAnnotationIntrospector(introspector);
		configure(SerializationConfig.Feature.USE_ANNOTATIONS, true);
		configure(SerializationConfig.Feature.AUTO_DETECT_FIELDS , true);
		configure(SerializationConfig.Feature.AUTO_DETECT_GETTERS, true);
		getSerializationConfig().setSerializationInclusion(Inclusion.NON_NULL);
	}


}
