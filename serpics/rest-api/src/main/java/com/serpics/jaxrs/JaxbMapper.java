package com.serpics.jaxrs;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

public class JaxbMapper  extends ObjectMapper{

	@SuppressWarnings("deprecation")
	public JaxbMapper() {
		super();
		this.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
		AnnotationIntrospector introspector2 = new JaxbAnnotationIntrospector();
		super.setAnnotationIntrospector(introspector2);
		configure(SerializationConfig.Feature.USE_ANNOTATIONS, true);
		configure(SerializationConfig.Feature.AUTO_DETECT_FIELDS , true);
		configure(SerializationConfig.Feature.AUTO_DETECT_GETTERS, true);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss z");
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		setDateFormat(dateFormat);		
	}


}
